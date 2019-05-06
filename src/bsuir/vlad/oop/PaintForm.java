package bsuir.vlad.oop;

import bsuir.vlad.oop.figures.DrawItem;
import bsuir.vlad.oop.figures.DrawItemFactory;
import bsuir.vlad.oop.figures.DrawType;

import javafx.application.Application;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class PaintForm extends Application {

    private final ToggleGroup TogGroup = new ToggleGroup();
    private final Group group = new Group();
    private final List<Shape> historyShapes = new LinkedList<Shape>();

    private Color lineColor = Color.BLACK;
    private Color fillColor = Color.WHITE;
    private Slider widthSlider = new Slider(1,10,3);


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * main method
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("OOP Paint");
        BorderPane rootNode = new BorderPane();

        Scene mainScene = new Scene(rootNode, 1200, 800);


        final Rectangle rect = new Rectangle(1,1, 1060, 800);
        rect.setFill(Color.WHITE);

        group.getChildren().add(rect);

        group.setOnMousePressed(event -> {
            if (event.getTarget() != rect) {
                 return;
            }

            Toggle tg = TogGroup.getSelectedToggle();

            if (tg instanceof ToggleButton) {
                ToggleButton tgb = (ToggleButton) tg;
                DrawItem item = (DrawItem)tgb.getUserData();

                if (item == null) {
                    DrawType type = DrawType.getTypeByText(tgb.getText());
                    item = DrawItemFactory.getInstance().getDrawItem(type);
                    tgb.setUserData(item);
                }

                if (item != null) {
                    item.startShape(event.getX(), event.getY());

                    Shape shape = item.getShape();
                    shape.setFill(fillColor);
                    shape.setStroke(lineColor);
                    shape.setStrokeWidth(widthSlider.getValue());

                    group.getChildren().add(shape);

                    if(historyShapes.size() > 0){
                        historyShapes.clear();
                    }
                }
            }
        });

        group.setOnMouseDragged(event-> {
            if (event.getTarget() != rect) {
                return;
            }

            if ((event.getX() <= 5) || (event.getY() <= 5) ||
                    (event.getX() >= (rect.getWidth() - 5)) ||
                    (event.getY() >= (rect.getHeight() - 5))) {
                return;
            }
            Toggle tg = TogGroup.getSelectedToggle();

            if (tg instanceof ToggleButton) {
                ToggleButton tgb = (ToggleButton) tg;
                DrawItem item = (DrawItem)tgb.getUserData();
                item.dragShape(event.getX(), event.getY());
            }
        });

        group.setOnMouseReleased(event-> {
            if (event.getTarget() != rect) {
                return;
            }

            Toggle tg = TogGroup.getSelectedToggle();

            if (tg instanceof ToggleButton) {
                ToggleButton tgb = (ToggleButton) tg;
                DrawItem item = (DrawItem)tgb.getUserData();
                item.stopShape(event.getX(), event.getY());
            }
        });

        rootNode.setCenter(group);
        rootNode.setRight(getMenuPanel());
        rootNode.setLeft(null);
        rootNode.setTop(null);
        rootNode.setBottom(null);

        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    /**
     * get Menu Pane
     *
     **/
    private VBox getMenuPanel() {
        VBox vPanel = new VBox();
        vPanel.setStyle("-fx-background-color: #abcdea");
        vPanel.getChildren().add(getActionsPane());
        vPanel.getChildren().add(getDrawItemsPane());
        vPanel.getChildren().add(getFileActionsPane());
        vPanel.getChildren().add(getFormatPane());

        return vPanel;
    }


    /**
     * удаление и возврат фигуры
    **/
    private TitledPane getActionsPane() {
        Button undoButton = new Button("Undo");
        Button redoButton = new Button("Redo");

        Button[] buttons = {undoButton, redoButton};
        for(Button button: buttons){
            button.setMinWidth(90);
        }

        VBox actions = new VBox(10);

        actions.getChildren().add(undoButton);
        actions.getChildren().add(redoButton);
        actions.setPrefWidth(140);
        actions.setStyle("-fx-background-color: #abcdea");

        TitledPane actionsTitledPane = new TitledPane("Actions", actions);
        actionsTitledPane.setExpanded(false);

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

        return actionsTitledPane;
    }


    /**
     * return Toggle Panel with Figures
     */
    private TitledPane getDrawItemsPane() {
        ToggleButton btnRhombus           = new ToggleButton(DrawType.RHOMBUS.getDisplayName());
        ToggleButton btnRightTriangle     = new ToggleButton(DrawType.RIGHT_TRIANGLE.getDisplayName());
        ToggleButton btnLine              = new ToggleButton(DrawType.LINE.getDisplayName());
        ToggleButton btnOval              = new ToggleButton(DrawType.OVAL.getDisplayName());
        ToggleButton btnRect              = new ToggleButton(DrawType.RECTANGLE.getDisplayName());
        ToggleButton btnIsoTriangle       = new ToggleButton(DrawType.ISO_TRIANGLE.getDisplayName());

        ToggleButton[] toggleButtons = {btnRhombus, btnRightTriangle, btnRect, btnLine, btnOval, btnIsoTriangle};

        for(ToggleButton toggle : toggleButtons){
            toggle.setMinWidth(90);
            toggle.setToggleGroup(TogGroup);
            toggle.setCursor(Cursor.HAND);
        }

        VBox Toggles = new VBox(10);
        Toggles.getChildren().addAll(btnLine, btnIsoTriangle, btnRect, btnRightTriangle, btnOval, btnRhombus);
        Toggles.setPrefWidth(140);
        Toggles.setStyle("-fx-background-color: #abcdea");

        TitledPane itemsTitledPane = new TitledPane("Draw Items", Toggles);
        itemsTitledPane.setExpanded(false);

        return  itemsTitledPane;
    }


    /**
     * Сохранение и загрузка
     */
    private TitledPane getFileActionsPane() {
        Button loadButton = new Button("Load");
        Button saveButton = new Button("Save");

        Button[] buttons = {loadButton, saveButton};
        for(Button button: buttons){
            button.setMinWidth(90);
        }

        VBox imageAction = new VBox(10);

        imageAction.getChildren().add(saveButton);
        imageAction.getChildren().add(loadButton);
        imageAction.setPrefWidth(140);
        imageAction.setStyle("-fx-background-color: #abcdea");

        TitledPane imageSaveOpenTitledPane = new TitledPane("File", imageAction);
        imageSaveOpenTitledPane.setExpanded(false);

        loadButton.setOnAction(event -> {
            try {
                File file = new FileChooser().showOpenDialog(null);
                LineNumberReader in = new LineNumberReader(new BufferedReader(new FileReader(file.getPath())));

                for (DrawType type : DrawType.values()) {
                    DrawItemFactory.getInstance().getDrawItem(type).clear();
                }

                group.getChildren().remove(1, group.getChildren().size());
                historyShapes.clear();

                for (String str = in.readLine() ; (str != null) && (str.trim().length() != 0) ; str = in.readLine()) {
                    Shape shape = DrawItemFactory.getInstance().loadItem(str);
                    if (shape != null) {
                        group.getChildren().add(shape);
                    }
                }

                in.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        saveButton.setOnAction(event -> {
            try {
                File file = new FileChooser().showSaveDialog(null);

                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file.getPath())));

                for (DrawType type : DrawType.values()) {
                    DrawItem item = DrawItemFactory.getInstance().getDrawItem(type);


                    for (Shape shape : item.getShapes()) {
                        boolean fl = true;
                        for (int i = 0; i < historyShapes.size(); i++){
                            if(shape == historyShapes.get(i)){
                               fl = false;
                                System.out.println("history =  " + historyShapes.get(i)+";\n shape = "+ shape + "\n");
                            }
                        }

                        if(fl == true){
                            String line = item.save(shape);
                            line += "|" + shape.getFill().toString() +
                                    "|" + shape.getStroke().toString() +
                                    "|" + shape.getStrokeWidth();

                            out.println(line);
                        }
                    }
                }

                out.flush();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        return imageSaveOpenTitledPane;
    }


    /**
     * Панель заливки и цвета линии
     */
    private TitledPane getFormatPane() {
        ColorPicker linePicker = new ColorPicker(Color.BLACK);
        ColorPicker fillPicker = new ColorPicker(Color.TRANSPARENT);

        widthSlider.setShowTickLabels(true);
        widthSlider.setShowTickMarks(true);

        linePicker.setMinWidth(90);
        fillPicker.setMinWidth(90);
        widthSlider.setMinWidth(90);

        VBox actions = new VBox(10);

        actions.getChildren().add(linePicker);
        actions.getChildren().add(fillPicker);
        actions.getChildren().add(widthSlider);
        actions.setPrefWidth(140);
        actions.setStyle("-fx-background-color: #abcdea");

        TitledPane formatTitledPane = new TitledPane("Formats", actions);
        formatTitledPane.setExpanded(false);

        linePicker.setOnAction(event -> {
            lineColor = ((ColorPicker)event.getSource()).getValue();
        });
        fillPicker.setOnAction(event -> {
            fillColor = ((ColorPicker)event.getSource()).getValue();
        });


        return formatTitledPane;
    }
}
