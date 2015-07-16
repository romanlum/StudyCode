<?php

/**
 * DataManager
 * Mysqli Version
 *
 *
 * @package
 * @subpackage
 * @author     John Doe <jd@fbi.gov>
 */
class DataManager {

    /**
     * connect to the database
     *
     * note: alternatively put those in parameter list or as class variables
     *
     * @return connection resource
     */
    private static function getConnection() {
        $con = new mysqli('localhost', 'root', '', 'fh_scm4_bookshop');
        if (mysqli_connect_errno()) {
            die('Unable to connect to database.');
        }
        return $con;
    }

    /**
     * place query
     *
     * @return mixed
     */
    private static function query($connection, $query) {
        $res = $connection->query($query);
        if (!$res) {
            die("Error in query \"" . $query . "\": " . $connection->error);
        }
        return $res;
    }

    /**
     * get the key of the last inserted item
     *
     * @return integer
     */
    private static function lastInsertId($connection) {
        return mysqli_insert_id($connection);
    }

    /**
     * retrieve an object from the database result set
     *
     * @param object $cursor result set
     * @return object
     */
    private static function fetchObject($cursor) {
        return $cursor->fetch_object();
    }

    /**
     * remove the result set
     *
     * @param object $cursor result set
     * @return null
     */
    private static function close($cursor) {
        $cursor->close();
    }

    /**
     * close the database connection
     *
     * @param object $cursor resource of current database connection
     * @return null
     */
    private static function closeConnection($connection) {
        $connection->close();
    }

    /**
     * get the categories
     *
     * @return array of Category-items
     */
    public static function getCategories() {
        $categories = array();

        $con = self::getConnection();
        $res = self::query($con, "
            SELECT id, name
            FROM Categories");

        while($cat = self::fetchObject($res)) {
            $categories[] = new Category($cat->id, $cat->name);
        }
        self::closeConnection($con);
        return $categories;


    }

    /**
     * get the books per category
     *
     * @param integer $categoryId  numeric id of the category
     * @return array of Book-items
     */
    public static function getBooksForCategory($categoryId) {
        $books = array();

        $con = self::getConnection();
        $res = self::query($con, "
            SELECT id, categoryId, title, author, price
            FROM Books
            WHERE categoryId = " . intval($categoryId));

        while($book = self::fetchObject($res)) {
            $books[] = new Book($book->id,$book->categoryId, $book->title,$book->author,$book->price);
        }
        self::closeConnection($con);
        return $books;
    }

    /**
     * get the User item by id
     *
     * @param integer $userId  uid of that user
     * @return User | false
     */
    public static function getUserForId($userId) {

        $user = null;

        $con = self::getConnection();
        $res = self::query($con, "
            SELECT id, userName, passwordHash
            FROM Users
            WHERE Id = " . intval($userId));

        if(($usr = self::fetchObject($res))) {
            $user = new User($usr->id,$usr->userName, $usr->passwordHash);
        }
        self::closeConnection($con);
        return $user;
    }

    /**
     * get the User item by name
     *
     * @param string $userName  name of that user - must be exact match
     * @return User | false
     */
    public static function getUserForUserName($userName) {
        $user = null;

        $con = self::getConnection();

        $userName= $con->real_escape_string($userName);

        $res = self::query($con, "
            SELECT id, userName, passwordHash
            FROM Users
            WHERE userName = '" . $userName. "'");

        if(($usr = self::fetchObject($res))) {
            $user = new User($usr->id,$usr->userName, $usr->passwordHash);
        }
        self::closeConnection($con);
        return $user;
    }

    /**
     * place to order with the shopping cart items
     *
     * note: wrapped in a transaction
     *
     * @param integer $userId   id of the ordering user
     * @param array $bookIds    integers of book ids
     * @param string $nameOnCard  cc name
     * @param string $cardNumber  cc number
     * @return integer
     */
    public static function createOrder($userId, $bookIds, $nameOnCard, $cardNumber) {
        $con = self::getConnection();
        self::query($con, "BEGIN;");

        $orderId = null;
        $userId = intval($userId);
        $nameOnCard = $con->real_escape_string($nameOnCard);
        $cardNumber = $con->real_escape_string($cardNumber);

        self::query($con, "
            INSERT INTO orders
            (userId, creditCardNumber,
            creditCardHolder)
            VALUES(
            $userId,'$cardNumber','$nameOnCard'
            )");
        $orderId = intval(self::lastInsertId($con));

        foreach($bookIds as $bookId) {
            self::query($con, "
            INSERT INTO orderedBooks
            (orderId,bookId)
            VALUES(
            $orderId,'$bookId'
            )");

        }
        self::query($con, "COMMIT;");
        return $orderId;
    }

}