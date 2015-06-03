package at.lumetsnet.caas.gui.dialogs;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public abstract class Dialog {

	protected Stage dialogStage = new Stage();
	
	protected final void createGui(Window owner, String title, String description) {
		BorderPane layout = new BorderPane();
		layout.getStyleClass().add("dialog-container");
		layout.setTop(createTopPane(title,description));
		layout.setCenter(createContentPane());
		layout.setBottom(createBottomPane());
		
		Scene dialogScene = new Scene(layout);
		dialogScene.getStylesheets().add(getClass().getResource("../css/dialog.css")
                .toExternalForm());
		
	    dialogStage.initOwner(owner);
	    dialogStage.setScene(dialogScene);
	    dialogStage.setTitle(title);
	    dialogStage.initModality(Modality.WINDOW_MODAL);
	    dialogStage.initStyle(StageStyle.UTILITY);
	    dialogStage.setResizable(false);
	}
	
	protected Node createTopPane(String title, String description) {
		HBox box = new HBox();
		box.getStyleClass().add("dialog-top-container");
	
		Label iconLabel = new Label();
		iconLabel.setId("dialog-icon");
		VBox descBox = new VBox();
		
		Label titleLabel = new Label();
		titleLabel.getStyleClass().add("dialog-title");
		titleLabel.setText(title);
				
		Label descLabel = new Label();
		descLabel.getStyleClass().add("dialog-description");
		descLabel.setText(description);
		
		
		descBox.getChildren().add(titleLabel);
		descBox.getChildren().add(descLabel);
		
		box.getChildren().add(iconLabel);
		box.getChildren().add(descBox);
		return box;
	}
	
	protected Pane createBottomPane() {
		HBox box = new HBox();
		box.getStyleClass().add("dialog-bottom-container");
		return box;
	
	}
		
	protected  abstract Node createContentPane();
	
}
