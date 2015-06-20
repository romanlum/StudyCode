package swe4.gui;

import java.util.logging.LogManager;

import swe4.gui.PreferencesDialog;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ScribbleFx extends Application {

	private Button leftButton, rightButton, upButton, downButton;
	private ColorPicker colorPicker;
	private ScribbleCanvas canvas;
	private ListView<String> messageList;
	
	private class ButtonEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			ScribbleFx.this.handleButtonEvent(e);
		}
	}
	
	private class KeyEventHandler implements EventHandler<KeyEvent> {
		public void handle(KeyEvent e) {
			appendMessage("Key "+ e.getCode() + "pressed");
			switch (e.getCode()) {
			case UP:
				canvas.addLineSegment(Direction.UP);
				e.consume();
				break;
			case LEFT:
				canvas.addLineSegment(Direction.LEFT);
				e.consume();
				break;
			case RIGHT:
				canvas.addLineSegment(Direction.RIGHT);
				e.consume();
				break;
			case DOWN:
				canvas.addLineSegment(Direction.DOWN);
				e.consume();
				break;
			default:
				break;
			}
		}
	}
	

	public void appendMessage(String message) {
		messages.add(message);
		messageList.scrollTo(messages.size());
		
	}

	
	public void handleButtonEvent(ActionEvent e) {
		if(e.getTarget() instanceof Button) {
			Button button = (Button) e.getTarget();
			appendMessage("Button "+ button.getId()+" pressed!");
			if(button == upButton) {
				canvas.addLineSegment(Direction.UP);
			}
			if(button == leftButton) {
				canvas.addLineSegment(Direction.LEFT);
			}
			if(button == downButton) {
				canvas.addLineSegment(Direction.DOWN);
			}
			if(button == rightButton) {
				canvas.addLineSegment(Direction.RIGHT);
			}
		}
		
	}


	private ObservableList<String> messages = FXCollections.observableArrayList();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Pane controlPane  = createControlPane();
			messageList = createMessageList();
			canvas = createCanvas();
			colorPicker.setValue(Color.AQUA);
			
			HBox topPane = new HBox(controlPane, messageList);
			topPane.setId("top-pane");
			
			VBox rootPane = new VBox(createMenuBar(primaryStage), topPane ,canvas);
			rootPane.setId("root-pane");
			rootPane.setOnKeyPressed(new KeyEventHandler());
			
			Scene scene = new Scene(rootPane, 500,500);
			scene.getStylesheets().add(getClass()
					.getResource("css/scribble-fx.css")
					.toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(400);
			primaryStage.setMinHeight(400);
			primaryStage.setTitle("ScribbleFX");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private Pane createControlPane() {
		leftButton = createIconButton("left-button", "css/button-left.png");
		rightButton = createIconButton("right-button", "css/button-right.png");
		upButton = createIconButton("up-button", "css/button-up.png");
		downButton = createIconButton("down-button", "css/button-down.png");
		
		GridPane buttonPane = new GridPane();
		buttonPane.setId("button-pane");
		buttonPane.add(leftButton, 0, 1);
		buttonPane.add(upButton, 1, 0);
		buttonPane.add(rightButton, 2, 1);
		buttonPane.add(downButton, 1, 2);
		
		//Variante 1:
		// EventHandler<ActionEvent> handler = new ButtonEventHandler();
		// leftButton.addEventHandler(ActionEvent.ACTION, handler);
		// rightButton.addEventHandler(ActionEvent.ACTION, handler);
		// upButton.addEventHandler(ActionEvent.ACTION, handler);
		// downButton.addEventHandler(ActionEvent.ACTION, handler);
		
		//Variante 2:
		//parent container registering
		//buttonPane.addEventHandler(ActionEvent.ACTION,new ButtonEventHandler());
		
		//Variante 3:
		//anonymous inner class
		//buttonPane.addEventHandler(ActionEvent.ACTION, 
		//		new EventHandler<ActionEvent>() {

		//			@Override
		//			public void handle(ActionEvent event) {
		//				ScribbleFx.this.handleButtonEvent(event);
		//				
		//			}
		
		//		}
		//);
		//variante 4:
		//LAMBDA
		buttonPane.addEventHandler(ActionEvent.ACTION, 
				(ActionEvent event) -> ScribbleFx.this.handleButtonEvent(event));
		
		Label colorLabel = new Label("Color ...");
		colorPicker = new ColorPicker();
		colorPicker.setOnAction(new EventHandler<ActionEvent> ()
				{

					@Override
					public void handle(ActionEvent event) {
						appendMessage("Color: "+colorPicker.getValue() + " selected");
						canvas.setLineColor(colorPicker.getValue());
					}
			
		});
		
		HBox colorPane = new HBox(colorLabel, colorPicker);
		colorPane.setId("color-pane");
		
		VBox controlPane = new VBox();
		controlPane.setId("control-pane");
		controlPane.getChildren().addAll(buttonPane, colorPane);
		
		return controlPane;
		
	}
	
	

	private Button createIconButton(String id, String iconFile) {
		Button button = new Button();
		button.setId(id);
		
		return button;
	}

	private Node createMenuBar(Stage stage) {
		PreferencesDialog prefDialog = new PreferencesDialog(stage);
		MenuItem prefItem = new MenuItem("Preferences...");
		prefItem.setOnAction(event -> prefDialog.show());
		
		Menu settingsMenu = new Menu("Settings");
		settingsMenu.getItems().add(prefItem);
		
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(settingsMenu);
		
		return menuBar;
	}

	private ListView<String> createMessageList() {
		messageList = new ListView<>();
		messageList.setItems(messages);
		messageList.setId("message-list");
		HBox.setHgrow(messageList, Priority.ALWAYS);
		return messageList;
	}
	
	private ScribbleCanvas createCanvas() {
		canvas = new ScribbleCanvas();
		canvas.setId("canvas");
		VBox.setVgrow(canvas, Priority.ALWAYS);
		VBox.setMargin(canvas, new Insets(0, 10, 10, 10));
		canvas.setOnMouseClicked( h -> {
			Point2D p = new Point2D(h.getX(), h.getY());
			appendMessage("Mouse Click at :" + p+".");
			canvas.addLineSegment(p);
		});
		
		return canvas;
	}

	public static void main(String[] args) {
		LogManager.getLogManager().reset();
		launch(args);

	}

}
