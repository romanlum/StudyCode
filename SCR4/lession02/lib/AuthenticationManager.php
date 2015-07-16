<?php
/**
 * Created by PhpStorm.
 * User: Roman
 * Date: 04.07.2015
 * Time: 11:04
 */

class AuthenticationManager extends BaseObject {

    public static function authenticate($userName, $password) {

        $user = DataManager::getUserForUserName($userName);
        if(
            $user != null &&
            $user->getPasswordHash() == hash('sha1', $userName . "|" . $password)) {

            $_SESSION['user'] = $user->getId();
            return true;
        }
        self::signOut();
        return false;

    }

    public static function signOut() {
        unset($_SESSION['user']);
    }

    public static function isAuthenticated() {
        return isset($_SESSION['user']);
    }

    public static function getAuthenticatedUser() {
        return self::isAuthenticated() ? DataManager::getUserForId($_SESSION['user']) : null;
    }



}