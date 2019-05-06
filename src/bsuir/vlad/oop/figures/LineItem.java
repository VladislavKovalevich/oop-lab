package bsuir.vlad.oop.figures;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class LineItem extends AbstractDrawItem {

    private static LineItem instance = new LineItem();

    public static LineItem getInstance() {
        return instance;
    }

    private LineItem() {
        super(DrawType.LINE);
    }

    @Override
    public void startShape(double x, double y) {
        Line line = new Line();

        line.setStartX(x);
        line.setStartY(y);
        line.setEndX(x);
        line.setEndY(y);

        setShape(line);
    }

    @Override
    public void dragShape(double x, double y) {
        Line line = (Line)getShape();

        line.setEndX(x);
        line.setEndY(y);
    }

    @Override
    public String save(Shape shape) {
        Line line = (Line)shape;

        String ret = "" + DrawType.LINE.name() + "|" +
                (line.getStartX() + shape.getLayoutX()) + "|" +
                (line.getStartY() + shape.getLayoutY()) + "|" +
                (line.getEndX() + shape.getLayoutX()) + "|" +
                (line.getEndY() + shape.getLayoutY());

        return ret;
    }

    @Override
    public Shape load(String[] array) {
        if ((array == null) || (array.length != 8)) {
            return null;
        }

        if (!array[0].equals(DrawType.LINE.name())) {
            return null;
        }

        Line line = new Line();
        line.setStartX(Double.valueOf(array[1]));
        line.setStartY(Double.valueOf(array[2]));
        line.setEndX(Double.valueOf(array[3]));
        line.setEndY(Double.valueOf(array[4]));

        addShape(line);

        return line;
    }

  /*  @Override
    public void movingFigure(Shape shape) {
        Line line = (Line)shape;

        line.setOnMousePressed(event1 -> {
            Shape shp = ((Shape)event1.getTarget());
            shp.toFront();
            layoutX = event1.getX();
            layoutY = event1.getY();
            shp.setStrokeWidth(shp.getStrokeWidth() + 5);
        });

        line.setOnMouseDragged(event1 -> {
            Shape shp = ((Shape)event1.getTarget());
            shp.setLayoutX(shp.getLayoutX() + (event1.getX() - layoutX));
            System.out.println("moving line");
            shp.setLayoutY(shp.getLayoutY() + (event1.getY() - layoutY));
        });

        line.setOnMouseReleased(event1 -> {
            Shape shp = ((Shape)event1.getTarget());
            shp.setStrokeWidth(shp.getStrokeWidth() - 5);
        });

        setShape(line);
    }*/
}
