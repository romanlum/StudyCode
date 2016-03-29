<?php
/**
 * Created by PhpStorm.
 * User: Roman
 * Date: 13.06.2015
 * Time: 11:07
 */

class User extends Entity {

    private $userName;
    private $passwordHash;

    public function __construct($id, $userName, $passwordHash)
    {
        parent::__construct($id);
        $this->userName = $userName;
        $this->passwordHash = $passwordHash;
    }


    /**
     * @return mixed
     */
    public function getUserName()
    {
        return $this->userName;
    }

    /**
     * @param mixed $userName
     */
    public function setUserName($userName)
    {
        $this->userName = $userName;
    }

    /**
     * @return mixed
     */
    public function getPasswordHash()
    {
        return $this->passwordHash;
    }

    /**
     * @param mixed $passwordHash
     */
    public function setPasswordHash($passwordHash)
    {
        $this->passwordHash = $passwordHash;
    }



}