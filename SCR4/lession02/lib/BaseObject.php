<?php
/**
 * Created by PhpStorm.
 * User: Roman
 * Date: 13.06.2015
 * Time: 10:09
 */

class BaseObject {

    /**
     * @param string $name
     * @param array $arguments
     * @throws Exception
     */
    public function __call($name,  $arguments ){
        throw new Exception('method '. $name . 'is not declared');
    }

    /**
     * @param $name
     * @param $value
     * @throws Exception
     */
    public function __set($name, $value) {
        throw new Exception('attribute ' . $name . ' is not declared');
    }

    /**
     * @param $name
     * @throws Exception
     */
    public function __get($name) {
        throw new Exception('attribute ' . $name . ' is not declared');
    }

    /**
     * @param $name
     * @param $arguments
     * @throws Exception
     */
    public static function __callStatic($name,  $arguments ){
        throw new Exception('static method '. $name . 'is not declared');
    }
}