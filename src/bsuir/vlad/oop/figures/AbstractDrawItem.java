package bsuir.vlad.oop.figures;

import javafx.scene.shape.Shape;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDrawItem implements DrawItem{
    private DrawType type;
    private Shape shape;

    private List<Shape> shapes = new LinkedList<Shape>();


    protected AbstractDrawItem(DrawType type) {
        this.type = type;
    }

    @Override
    public DrawType getType(){
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
        shapes.add(shape);
        setShape(null);
    }
}
