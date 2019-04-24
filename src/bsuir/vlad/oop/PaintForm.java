package bsuir.vlad.oop;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import bsuir.vlad.oop.figures.DrawItem;
import bsuir.vlad.oop.figures.DrawItemBuilder;
import bsuir.vlad.oop.figures.DrawType;

import java.util.LinkedList;
import java.util.List;

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

        btnRhombus           = new ToggleButton(DrawType.RHOMBUS.getDisplayName());
        btnRightTriangle     = new ToggleButton(DrawType.RIGHT_TRIANGLE.getDisplayName());
        btnLine              = new ToggleButton(DrawType.LINE.getDisplayName());
        btnOval              = new ToggleButton(DrawType.OVAL.getDisplayName());
        btnRect              = new ToggleButton(DrawType.RECTANGLE.getDisplayName());
        btnIsoTriangle       = new ToggleButton(DrawType.ISO_TRIANGLE.getDisplayName());

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

        final Group group = new Group();

        Rectangle rect = new Rectangle(1,1, 1060, 800);
        rect.setFill(Color.GRAY);
        group.getChildren().add(rect);

        List<Shape> historyShapes = new LinkedList<Shape>();

        group.setOnMousePressed(event -> {
            Toggle tg = TogGroup.getSelectedToggle();

            if (tg instanceof ToggleButton) {
                ToggleButton tgb = (ToggleButton) tg;
                DrawItem item = (DrawItem)tgb.getUserData();

                if (item == null) {
                    DrawType type = DrawType.getTypeByText(tgb.getText());
                    item = DrawItemBuilder.buildDrawItem(type);
                    tgb.setUserData(item);
                }

                if (item != null) {
                    item.startShape(event.getX(), event.getY());

                    Shape shape = item.getShape();
                    shape.setFill(Color.GRAY);
                    shape.setStroke(Color.RED);
                    shape.setStrokeWidth(5);

                    group.getChildren().add(shape);

                    if(historyShapes.size() > 0){
                        historyShapes.clear();
                    }
                }
            }
        });

        group.setOnMouseDragged(event-> {
            if ((event.getX() <= 5) || (event.getY() <= 5) ||
                    (event.getX() >= (rect.getWidth() - 5)) ||
                    (event.getY() >= (rect.getHeight() - 5))) {
                return;
            }
            Toggle tg = TogGroup.getSelectedToggle();

            if (tg instanceof ToggleButton) {
                ToggleButton tgb = (ToggleButton) tg;
                DrawItem item = (DrawItem)tgb.getUserData();

                if (item != null) {
                    item.dragShape(event.getX(), event.getY());
                }
            }
        });

        group.setOnMouseReleased(event-> {
            Toggle tg = TogGroup.getSelectedToggle();

            if (tg instanceof ToggleButton) {
                ToggleButton tgb = (ToggleButton) tg;
                DrawItem item = (DrawItem)tgb.getUserData();

                if (item != null) {
                    item.stopShape(event.getX(), event.getY());
                }
            }
        });


        undoButton.setOnAction(event -> {
            if(group.getChildren().size() > 1){
                Shape tmp = (Shape) group.getChildren().remove(group.getChildren().size() - 1);
                historyShapes.add(tmp);
            }
        });


        redoButton.setOnAction(event -> {
            if(historyShapes.size() > 0){
                Shape tmp = historyShapes.remove(historyShapes.size() - 1);
                group.getChildren().add(tmp);
            }
        });

        rootNode.setCenter(group);
        rootNode.setRight(vPanel);
        rootNode.setLeft(null);
        rootNode.setTop(null);
        rootNode.setBottom(null);

        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
