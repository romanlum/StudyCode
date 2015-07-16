<?php
/**
 * Created by PhpStorm.
 * User: Roman
 * Date: 13.06.2015
 * Time: 10:16
 */

/**
 * Interface IData
 */
interface IData {
    public function getID();
}

/**
 * Class Entity
 */
class Entity extends BaseObject implements IData {

    private $id;

       
    function __construct($id) {
        $this->id = $id;
    }


    public function getID() {
        return $this->id;
    }
}

