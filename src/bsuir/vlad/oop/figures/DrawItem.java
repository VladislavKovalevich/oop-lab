package bsuir.vlad.oop.figures;

import javafx.scene.shape.Shape;

public interface DrawItem {
    Shape getShape();

    void startShape(double x, double y);

    void dragShape(double x, double y);

    void stopShape(double x, double y);

}
