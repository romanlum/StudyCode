<?php
/**
 * Created by PhpStorm.
 * User: Roman
 * Date: 04.07.2015
 * Time: 08:42
 */

class Util extends BaseObject {

    public static function escape($string) {
        return nl2br(htmlentities($string));
    }
    public static function action($action, $params = null) {
        $page = isset($_REQUEST['page']) && $_REQUEST['page'] ?
            $_REQUEST['page'] :
            $_SERVER['REQUEST_URI'];

        $return = 'index.php?action='.rawurlencode($action) . '&page=' . rawurlencode($page);
        if( is_array($params)) {
            foreach ($params as $name => $value) {
                $return .='&' .rawurlencode($name).'='.rawurlencode($value);
            }

        }

        return $return;
    }

    public static function redirect($page = null) {
        if($page == null) {
            $page = $_REQUEST['page'];
        }
        header("Location:".$page);
    }

}
