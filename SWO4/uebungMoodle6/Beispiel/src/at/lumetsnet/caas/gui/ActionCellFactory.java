package at.lumetsnet.caas.gui;

import java.util.function.Consumer;
import java.util.function.Function;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class ActionCellFactory<S,T> implements Callback<TableColumn<S,T>, TableCell<S,T>> {
		
	private Consumer<T> editAction;
	private Consumer<T> deleteAction;
	
		
	public ActionCellFactory(Consumer<T> editAction,
			Consumer<T> deleteAction) {
		this.editAction = editAction;
		this.deleteAction = deleteAction;
	}


	private class ActionTableCell<S,T> extends TableCell<S,T> {
		private HBox box = new HBox();
		private Button editButton = new Button("Bearbeiten");
		private Button deleteButton = new Button("Löschen");
		
		private Consumer<T> editAction;
		private Consumer<T> deleteAction;
		
		public ActionTableCell(Consumer<T> editAction,
				Consumer<T> deleteAction) {
			this.editAction = editAction;
			this.deleteAction = deleteAction;
			editButton.getStyleClass().addAll("cell-button", "edit-cell-button");
			deleteButton.getStyleClass()
					.addAll("cell-button", "delete-cell-button");
			box.getChildren().addAll(editButton, deleteButton);
		}
		
		@Override
		protected void updateItem(T entity, boolean empty) {
			
			if (entity != null) {
				editButton.setOnAction(x ->{
					if(editAction !=null) 
						editAction.accept(entity);
					
				});
				deleteButton.setOnAction(x -> {
					if(deleteAction != null)
						deleteAction.accept(entity);
				});
				setGraphic(box);
			} else {
				setGraphic(null);
			}
		}
	}
	
	
	@Override
	public TableCell<S, T> call(TableColumn<S, T> arg0) {
		return new ActionTableCell<>(editAction,deleteAction);
	}

}
