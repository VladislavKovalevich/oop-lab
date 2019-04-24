package bsuir.vlad.oop.figures;

import javafx.scene.shape.Line;

public class LineItem extends AbstractDrawItem {

    protected LineItem() {
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
}
