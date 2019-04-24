package bsuir.vlad.oop;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaintForm extends Application {
    private ToggleButton btnLine;
    private ToggleButton btnRect;
    private ToggleButton btnIsoTriangle;
    private ToggleButton btnRightTriangle;
    private ToggleButton btnOval;
    private ToggleButton btnRhombus;

    private Button saveButton;
    private Button openButton;
    private Button redoButton;
    private Button undoButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("OOP Paint");
        BorderPane rootNode = new BorderPane();

        Scene mainScene = new Scene(rootNode, 1200, 800);
        btnRhombus            = new ToggleButton("rhombus");
        btnRightTriangle = new ToggleButton("rightTriangle");
        btnLine              = new ToggleButton("line");
        btnOval              = new ToggleButton("Oval");
        btnRect              = new ToggleButton("Rectangle");
        btnIsoTriangle          = new ToggleButton("IsoTriangle");

        openButton = new Button("Open");
        saveButton = new Button("Save");
        undoButton = new Button("Undo");
        redoButton = new Button("Redo");

        ToggleButton[] toggleButtons = {btnRhombus, btnRightTriangle, btnRect, btnLine, btnOval, btnIsoTriangle};
        Button[] buttons = {openButton, saveButton, undoButton, redoButton};
        ToggleGroup TogGroup = new ToggleGroup();

        for(Button button: buttons){
            button.setMinWidth(90);
        }

        for(ToggleButton toggle : toggleButtons){
            toggle.setMinWidth(90);
            toggle.setToggleGroup(TogGroup);
            toggle.setCursor(Cursor.HAND);
        }

        VBox Toggles = new VBox(10);
        Toggles.getChildren().addAll(btnLine, btnIsoTriangle, btnRect, btnRightTriangle, btnOval, btnRhombus);
        Toggles.setPrefWidth(140);
        Toggles.setStyle("-fx-background-color: #abcdea");

        VBox actions = new VBox(10);

        actions.getChildren().add(undoButton);
        actions.getChildren().add(redoButton);
        actions.setPrefWidth(140);
        actions.setStyle("-fx-background-color: #abcdea");

        VBox imageAction = new VBox(10);

        imageAction.getChildren().add(saveButton);
        imageAction.getChildren().add(openButton);
        imageAction.setPrefWidth(140);
        imageAction.setStyle("-fx-background-color: #abcdea");

        TitledPane actionsTitledPane = new TitledPane("Actions", actions);
        actionsTitledPane.setExpanded(false);

        TitledPane itemsTitledPane = new TitledPane("Items", Toggles);
        itemsTitledPane.setExpanded(false);

        TitledPane imageSaveOpenTitledPane = new TitledPane("Image", imageAction);
        imageSaveOpenTitledPane.setExpanded(false);

        VBox vPanel = new VBox();
        vPanel.setStyle("-fx-background-color: #abcdea");
        vPanel.getChildren().add(actionsTitledPane);
        vPanel.getChildren().add(itemsTitledPane);
        vPanel.getChildren().add(imageSaveOpenTitledPane);

        rootNode.setRight(vPanel);
        rootNode.setLeft(null);
        rootNode.setTop(null);
        rootNode.setBottom(null);

        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
