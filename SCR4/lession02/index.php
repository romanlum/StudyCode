<?PHP
require_once("inc/bootstrap.php");

$view = isset($_REQUEST['view']) && $_REQUEST['view'] ? $_REQUEST['view'] : 'welcome';
$postAction = isset($_REQUEST['action']) && $_REQUEST['action'] ? $_REQUEST['action'] : null;

if($postAction != null) {
    Controller::getInstance()->invokePostAction();
}

if(file_exists(__DIR__ .'/views/' .$view .'.php' )) {
    require('/views/'.$view  .'.php');
}
