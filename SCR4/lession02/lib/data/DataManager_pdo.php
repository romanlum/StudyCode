<?php

/**
 * DataManager
 * PDO Version
 *
 *
 * @package
 * @subpackage
 * @author     John Doe <jd@fbi.gov>
 */
class DataManager {

    private static $__connection;

    /**
     * connect to the database
     *
     * note: alternatively put those in parameter list or as class variables
     *
     * @return connection resource
     */
    private static function getConnection() {
        if (!isset(self::$__connection)) {
            self::$__connection = new PDO('mysql:host=localhost;dbname=fh_scm4_bookshop;charset=utf8', 'root', '');
        }
        return self::$__connection;
    }

    /**
     * place query
     *
     * note: using prepared statements
     * see the filtering in bindValue()
     *
     * @return mixed
     */
    private static function query($connection, $query, $parameters = array()) {
        $statement = $connection->prepare($query);
        $i = 1;
        foreach ($parameters as $param) {
            if (is_int($param)) {
                $statement->bindValue($i, $param, PDO::PARAM_INT);
            }
            if (is_string($param)) {
                $statement->bindValue($i, $param, PDO::PARAM_STR);
            }
            $i++;
        }
        $statement->execute();
        return $statement;
    }

    /**
     * get the key of the last inserted item
     *
     * @return integer
     */
    private static function lastInsertId($connection) {
        return $connection->lastInsertId();
    }

    /**
     * retrieve an object from the database result set
     *
     * @param object $cursor result set
     * @return object
     */
    private static function fetchObject($cursor) {
        return $cursor->fetchObject();
    }

    /**
     * remove the result set
     *
     * @param object $cursor result set
     * @return null
     */
    private static function close($cursor) {
        $cursor->closeCursor();
    }

    /**
     * close the database connection
     *
     * note: not needed
     *
     * @param object $cursor resource of current database connection
     * @return null
     */
    private static function closeConnection($connection) {

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
      FROM categories;
      ");
        while ($cat = self::fetchObject($res)) {
            $categories[] = new Category($cat->id, $cat->name);
        }
        self::close($res);
        self::closeConnection($con);
        return $categories;
    }

    /**
     * get the books per category
     *
     * note: see how prepared statements replace "?" with array element values
     *
     * @param integer $categoryId  numeric id of the category
     * @return array of Book-items
     */
    public static function getBooksForCategory($categoryId) {
        $books = array();
        $con = self::getConnection();
        $res = self::query($con, "
      SELECT id, categoryId, title, author, price 
      FROM books 
      WHERE categoryId = ?;
      ", array($categoryId));
        while ($book = self::fetchObject($res)) {
            $books[] = new Book($book->id, $book->categoryId, $book->title, $book->author, $book->price);
        }
        self::close($res);
        self::closeConnection($con);
        return $books;
    }

    /**
     * get the books per search term
     *
     * note: search via LIKE
     *
     * @param string $term  search term: book title string match
     * @return array of Book-items
     */
    public static function getBooksForSearchCriteria($term) {
        $books = array();
        $con = self::getConnection();
        $res = self::query($con, "
      SELECT id, categoryId, title, author, price 
      FROM books 
      WHERE title LIKE ?;
      ", array("%" . $term . "%"));
        while ($book = self::fetchObject($res)) {
            $books[] = new Book($book->id, $book->categoryId, $book->title, $book->author, $book->price);
        }
        self::close($res);
        self::closeConnection($con);
        return $books;
    }

    /**
     * get the books per search term – paginated set only
     *
     * @param string $term  search term: book title string match
     * @param integer $offset  start at the nth item
     * @param integer $numPerPage  number of items per page
     * @return array of Book-items
     */
    public static function getBooksForSearchCriteriaWithPaging($term, $offset, $numPerPage) {
        $con = self::getConnection();
        //query total count
        $res = self::query($con, "
      SELECT COUNT(*) AS cnt 
      FROM books 
      WHERE title LIKE ?;
      ", array("%" . $term . "%"));
        $totalCount = self::fetchObject($res)->cnt;
        self::close($res);
        //query books to return
        $books = array();
        $res = self::query($con, "
      SELECT id, categoryId, title, author, price 
      FROM books 
      WHERE title 
      LIKE ? LIMIT ?, ?;
      ", array("%" . $term . "%", intval($offset), intval($numPerPage)));
        while ($book = self::fetchObject($res)) {
            $books[] = new Book($book->id, $book->categoryId, $book->title, $book->author, $book->price);
        }
        self::close($res);
        self::closeConnection($con);
        return new PagingResult($books, $offset, $totalCount);
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
      FROM users 
      WHERE id = ?;
      ", array($userId));
        if ($u = self::fetchObject($res)) {
            $user = new User($u->id, $u->userName, $u->passwordHash);
        }
        self::close($res);
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
        $res = self::query($con, "
      SELECT id, userName, passwordHash 
      FROM users 
      WHERE userName = ?;
      ", array($userName));
        if ($u = self::fetchObject($res)) {
            $user = new User($u->id, $u->userName, $u->passwordHash);
        }
        self::close($res);
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
        self::query($con, 'BEGIN;');
        self::query($con, "
      INSERT INTO orders (
        userId
        , creditCardNumber
        , creditCardHolder
      ) VALUES (
        ?
        , ?
        , ?
      );
      ", array($userId, $cardNumber, $nameOnCard));
        $orderId = self::lastInsertId($con);
        foreach ($bookIds as $bookId) {
            self::query($con, "
        INSERT INTO orderedbooks (
          orderId
          , bookId
        ) VALUES (
          ?
          , ?
        );", array($orderId, $bookId));
        }
        self::query($con, 'COMMIT;');
        self::closeConnection($con);
        return $orderId;
    }

}