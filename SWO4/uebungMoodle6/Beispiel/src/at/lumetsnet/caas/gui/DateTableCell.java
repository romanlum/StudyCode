package at.lumetsnet.caas.gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.TableCell;

/***
 * TableCell used for displaying LocalDate values 
 * inside a TableView
 * @author romanlum
 *
 * @param <S>
 */
public class DateTableCell<S> extends TableCell<S, LocalDate> {

	@Override
	protected void updateItem(LocalDate entity, boolean empty) {

		if (entity != null) {
			setText(entity.format(DateTimeFormatter.ISO_LOCAL_DATE));

		} else {
			setText("");
		}
	}
}