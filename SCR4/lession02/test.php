<?php
/**
 * Created by PhpStorm.
 * User: Roman
 * Date: 16.07.2015
 * Time: 19:22
 */

session_start();

foreach($_SERVER as $entry) {
    echo nl2br("$entry \n");
}

$ar = array(9,8,7,6,5);

foreach($ar as $a => $key) {
    echo nl2br("$a = $key\n");
}

function connect() {
    global $db;
    $db = new mysqli("localhost","root",null,"fh_scm4_bookshop");

}

connect();

$query="SELECT * FROM books where id = ". $db->real_escape_string("1");

$result = $db->query($query);
while($data = $result->fetch_object()) {
    echo nl2br($data->id . "\n");
}

$stmt = $db->prepare("SELECT * FROM books where id = ?");
$id = 1;
$stmt->bind_param("i",$id);
$result = $stmt->execute();
var_dump($result);
$result = $stmt->get_result();
while($data = $result->fetch_object()) {
    echo nl2br($data->id . "\n");
}

