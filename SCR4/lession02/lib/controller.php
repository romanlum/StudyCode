<?php

/**
 * Controller
 *
 * class handles POST requests and redirects
 * the client after processing
 * - demo of singleton pattern
 */
class Controller extends BaseObject {
    // static strings used in views

    const ACTION = 'action';
    const REQUEST_METHOD = 'POST';
    const PAGE = 'page';
    const CC_NAME = 'nameOnCard';
    const CC_NUMBER = 'cardNumber';
    const ACTION_ADD = 'addToCart';
    const ACTION_REMOVE = 'removeFromCart';
    const ACTION_ORDER = 'placeOrder';
    const ACTION_LOGIN = 'login';
    const USR_NAME = 'userName';
    const USR_PASSWORD = 'password';
    const ACTION_LOGOUT = 'logout';

    private static $instance = false;

    /**
     *
     * @return Controller
     */
    public static function getInstance() {

        if (!self::$instance) {
            self::$instance = new Controller();
        }
        return self::$instance;
    }

    private function __construct() {

    }

    /**
     *
     * processes POST requests and redirects client depending on selected
     * action
     *
     * @return bool
     * @throws Exception
     */
    public function invokePostAction() {

        if($_SERVER['REQUEST_METHOD'] !=  self::REQUEST_METHOD) {
            throw new Exception('Controller can only handle ' . self::REQUEST_METHOD . '    requests');
            return null;
        }
        else if(!isset($_REQUEST[self::ACTION])) {
            throw new Exception(self::ACTION . " parameter not set");
            return null;
        }

        //all clear
        $action = $_REQUEST[self::ACTION];

        switch($action) {

            case self::ACTION_ADD:
                ShoppingCart::add((int) $_REQUEST['bookId']);
                Util::redirect();
                break;

            case self::ACTION_REMOVE:

                ShoppingCart::remove((int) $_REQUEST['bookId']);
                Util::redirect();
                break;

            case self::ACTION_LOGIN:
                if(!AuthenticationManager::authenticate($_REQUEST[self::USR_NAME], $_REQUEST[self::USR_PASSWORD])) {
                    $this->forwardRequest(['Invalid credentials provided']);
                } else {
                    Util::redirect();
                }
                break;
            case self::ACTION_LOGOUT:
                AuthenticationManager::signOut();
                Util::redirect();
                break;

            case self::ACTION_ORDER:
                $user = AuthenticationManager::getAuthenticatedUser();
                if($user == null) {
                    $this->forwardRequest(array('You need to login first'));
                    break;
                }

                if($this->processCheckout($_POST[self::CC_NAME], $_POST[self::CC_NUMBER])) {

                    break;
                } else {

                    return false;
                }
                break;

            default:
                break;
        }
        return true;
    }

    /**
     *
     * @param string $nameOnCard
     * @param integer $cardNumber
     * @return bool
     */
    protected function processCheckout($nameOnCard = null, $cardNumber = null) {
        $nameOnCard = trim($nameOnCard);
        if($nameOnCard == null ||strlen($nameOnCard) == 0) {
            $error[]='Invalid name on card.';
        }

        $cardNumber = trim($cardNumber);
        if($cardNumber == null ||strlen($cardNumber) !=  16 || !ctype_digit($cardNumber)) {
            $error[]='Invalid card number.';
        }

        if(count($error) > 0 ) {
            $this->forwardRequest($error);
            return false;
        }

        if(ShoppingCart::size() == 0){
            $this->forwardRequest(array('No items in cart.'));
            return false;
        }

        $user = AuthenticationManager::getAuthenticatedUser();
        $orderId = DataManager::createOrder($user->getId(),ShoppingCart::getAll(), $nameOnCard, $cardNumber);
        if(!$orderId) {
            $this->forwardRequest(array("Could not create order"));
            return false;
        }
        ShoppingCart::clear();
        Util::redirect('index.php?view=success&orderId='. rawurlencode($orderId));
        return true;


    }

    /**
     *
     * @param array $errors : optional assign it to
     * @param string $target : url for redirect of the request
     */
    protected function forwardRequest(array $errors = null, $target = null) {
        //check for given target and try to fall back to previous page if needed
        if ($target == null) {
            if (!isset($_REQUEST[self::PAGE])) {
                throw new Exception('Missing target for forward.');
            }
            $target = $_REQUEST[self::PAGE];
        }
        //forward request to target
        // optional - add errors to redirect and process them in view
        if (count($errors) > 0)
            $target .= '&errors=' . urlencode(serialize($errors));
        header('location: ' . $target);
        exit();
    }

}