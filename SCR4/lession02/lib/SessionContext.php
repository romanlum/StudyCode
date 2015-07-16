<?php
/**
 * Created by PhpStorm.
 * User: Roman
 * Date: 04.07.2015
 * Time: 10:06
 */

class SessionContext extends BaseObject {

    private static $exists = false;

    public static function create() {
        if(!self::$exists) {
            self::$exists = session_start();
        }
        return self::$exists;
    }
}