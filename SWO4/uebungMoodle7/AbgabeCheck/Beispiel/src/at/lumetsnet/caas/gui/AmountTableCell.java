package at.lumetsnet.caas.gui;

import javafx.scene.control.TableCell;

/***
 * TableCell used for formatting an amount inside a TableView Formats the value
 * given in LOWEST currency unit to normal EUR representation
 * 
 * @author romanlum
 *
 * @param <S>
 */
public class AmountTableCell<S> extends TableCell<S, Number> {

	@Override
	protected void updateItem(Number entity, boolean empty) {

		if (entity != null) {
			// formats the value given in lowest currency unit
			// to EUR
			setText(((double) ((long) entity) / 100) + " EUR");
		} else {
			setText("");
		}
	}
}