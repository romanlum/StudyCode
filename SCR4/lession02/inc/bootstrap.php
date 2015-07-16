<?php

error_reporting(E_ALL); ini_set('display_errors','On');

spl_autoload_register(function($class) {
    require_once 'lib/' . $class . '.php';
});

SessionContext::create();

$dataMode='pdo';
switch($dataMode) {
    case 'mysqli':
    case 'pdo':
        break;
    default:
        $dataMode = 'mock';
        break;
}

require_once("lib/data/DataManager_".$dataMode .".php");
//require_once("lib/Entity.php");
//require_once("lib/Category.php");
