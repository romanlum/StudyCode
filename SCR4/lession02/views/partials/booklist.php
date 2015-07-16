<table class="table">
    <thead>
    <tr>
        <th>
            Title
        </th>
        <th>
            Author
        </th>
        <th>
            Price
        </th>
        <th>
            <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
        </th>
    </tr>
    </thead>
    <tbody>
    <?php
    foreach ($books as $book):
        $inCart = ShoppingCart::contains($book->getId());

        ?>
        <tr<?php $inCart == true ? print 'class="inCart"' :print ""; ?> >
            <td><strong>
                    <?php echo Util::escape($book->getTitle()); ?>
                </strong>
            </td>
            <td>
                <?php echo Util::escape($book->getAuthor()); ?>
            </td>
            <td>
                <?php echo Util::escape($book->getPrice()); ?>
            </td>
            <td class="add-remove">
                <?php if(!$inCart) : ?>
                    <form action="<?php echo Util::action('addToCart',
                        array('bookId' => $book->getId())); ?>" method="post">

                        <input class="btn btn-default btn-xs btn-info" type="submit" value="+"/>
                    </form>
                <?php else: ?>
                    <form action="<?php echo Util::action('removeFromCart',
                        array('bookId' => $book->getId())); ?>" method="post">

                        <input class="btn btn-default btn-xs btn-success" type="submit" value="-"/>
                    </form>
                <?php endif; ?>
            </td>
        </tr>
    <?php endforeach; ?>
    </tbody>
</table>