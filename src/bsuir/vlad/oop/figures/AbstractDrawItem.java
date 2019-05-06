package bsuir.vlad.oop.figures;

import javafx.scene.shape.Shape;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDrawItem implements DrawItem,movingFigures {
    private DrawType type;
    private Shape shape;

    private List<Shape> shapes = new LinkedList<Shape>();

    protected double layoutX;
    protected double layoutY;


    protected AbstractDrawItem(DrawType type) {
        this.type = type;
    }

    @Override
    public DrawType getType() {
        return type;
    }

    protected void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }

    @Override
    public void stopShape(double x, double y) {
        addShape(shape);
        setShape(null);
    }

    @Override
    public Shape movingFigure(Shape shape){

        shape.setOnMousePressed(event -> {
            Shape shp = ((Shape)event.getTarget());
            shp.toFront();
            layoutX = event.getX();
            layoutY = event.getY();
            shp.setStrokeWidth(shp.getStrokeWidth() + 5);
        });

        shape.setOnMouseDragged(event -> {
            Shape shp = ((Shape)event.getTarget());
            shp.setLayoutX(shp.getLayoutX() + (event.getX() - layoutX));
            shp.setLayoutY(shp.getLayoutY() + (event.getY() - layoutY));
        });

        shape.setOnMouseReleased(event -> {
            Shape shp = ((Shape)event.getTarget());
            System.out.println(shp.getLayoutX()+","+shp.getLayoutY());
            shp.setStrokeWidth(shp.getStrokeWidth() - 5);
        });

        return shape;
    }

    protected void addShape(Shape shape) {
        shape = movingFigure(shape);
        shapes.add(shape);
    }

    @Override
    public List<Shape> getShapes() {
        return shapes;
    }

    @Override
    public void clear() {
        shapes.clear();
    }

}
