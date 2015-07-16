<?php
/**
 * Created by PhpStorm.
 * User: Roman
 * Date: 13.06.2015
 * Time: 10:21
 */

class Category extends Entity {

    private $name;

    public function __construct($id, $name) {
        parent::__construct($id);
        $this->name = $name;
    }


    /**
     * @return mixed
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * @param mixed $name
     */
    public function setName($name)
    {
        $this->name = $name;
    }


}