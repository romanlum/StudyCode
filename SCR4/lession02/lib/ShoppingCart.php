<?php
/**
 * Created by PhpStorm.
 * User: Roman
 * Date: 04.07.2015
 * Time: 10:00
 */

class ShoppingCart extends BaseObject {

    public static function add($bookId) {
        $cart = self::getCart();
        $cart[$bookId] = $bookId;
        self::storeCart($cart);
    }

    public static function remove($bookId) {
        $cart = self::getCart();
        unset($cart[$bookId]);
        self::storeCart($cart);

    }

    public static function clear() {
        unset($_SESSION['cart']);
    }


    public static function getAll() {
        return self::getCart();
    }

    private static function getCart() {
        return isset($_SESSION['cart']) && is_array($_SESSION['cart']) ? $_SESSION['cart'] : array();
    }

    public static function storeCart(array $cart) {
        $_SESSION['cart'] = $cart;
    }

    public static function contains($bookId) {

        return array_key_exists($bookId, self::getCart());
    }

    public static function size()
    {
        return sizeof(self::getCart());
    }


}