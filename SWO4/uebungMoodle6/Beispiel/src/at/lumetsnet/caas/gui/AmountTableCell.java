package at.lumetsnet.caas.gui;
 
import javafx.scene.control.TableCell;

public class AmountTableCell<S> extends TableCell<S, Number> {
	
	@Override
	protected void updateItem(Number entity, boolean empty) {

		if (entity != null) {
			setText(((double)((long)entity) / 100)+" EUR");
		} else {
			setText("");
		}
	}
}