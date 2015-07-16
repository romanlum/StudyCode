<?php
/**
 * Created by PhpStorm.
 * User: Roman
 * Date: 13.06.2015
 * Time: 10:59
 */

class Book extends Entity {

    /**
     * @var
     */
    private $categoryId;
    private $title;
    private $author;
    private $price;

    public function __construct($id, $categoryId, $title, $author, $price)
    {
        parent::__construct($id);
        $this->categoryId = $categoryId;
        $this->title = $title;
        $this->author = $author;
        $this->price = doubleval($price);
    }



    /**
     * @return mixed
     */
    public function getCategoryId()
    {
        return $this->categoryId;
    }

    /**
     * @param mixed $categoryId
     */
    public function setCategoryId($categoryId)
    {
        $this->categoryId = $categoryId;
    }

    /**
     * @return mixed
     */
    public function getTitle()
    {
        return $this->title;
    }

    /**
     * @param mixed $title
     */
    public function setTitle($title)
    {
        $this->title = $title;
    }

    /**
     * @return mixed
     */
    public function getAuthor()
    {
        return $this->author;
    }

    /**
     * @param mixed $author
     */
    public function setAuthor($author)
    {
        $this->author = $author;
    }

    /**
     * @return mixed
     */
    public function getPrice()
    {
        return $this->price;
    }

    /**
     * @param mixed $price
     */
    public function setPrice($price)
    {
        $this->price = $price;
    }



}