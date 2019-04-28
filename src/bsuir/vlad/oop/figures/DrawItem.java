package bsuir.vlad.oop.figures;

import javafx.scene.shape.Shape;

import java.util.List;

public interface DrawItem {

    DrawType getType();

    Shape getShape();

    void startShape(double x, double y);

    void dragShape(double x, double y);

    void stopShape(double x, double y);

    List<Shape> getShapes();

    String save(Shape shape);

    Shape load(String[] array);

    void clear();

}
