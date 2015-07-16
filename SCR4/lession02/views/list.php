<?php
$categories = DataManager::getCategories();
$categoryId = isset($_REQUEST['categoryId']) ?
    (int)$_REQUEST['categoryId'] : null;
$books = $categoryId ? DataManager::getBooksForCategory($categoryId) : null;


require('views/partials/header.php');
?>
    <div class="page-header"><h2>List of books by category</h2></div>

    <ul class="nav nav-tabs">
        <?php foreach ($categories AS $cat) : ?>
            <li<?php
            $categoryId == $cat->getId() ? print ' class="active"' : null;
            ?>
                >
                <a href="<?php echo $_SERVER["PHP_SELF"] . '?view=list&categoryId=' . $cat->getId() ?>"><?php echo Util::escape($cat->getName()); ?></a>
            </li>

        <?php endforeach; ?>
    </ul>

<?php if(isset($books)) :?>
    <?php if(sizeof($books) > 0) {
        require("views/partials/booklist.php");
    }   else  { ?>
        <div class="alert alert-warning">No books in this category</div>
    <?php } ?>
<?php else : ?>
    <div class="alert alert-info" role="alert">Please select a category.</div>

<?php endif; ?>
<?php
require('views/partials/footer.php');
