<?PHP

require_once("inc/bootstrap.php");


$cat = DataManager::getBooksForCategory(1);
var_dump($cat);