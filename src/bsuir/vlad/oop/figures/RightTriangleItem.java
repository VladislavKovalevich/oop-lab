package bsuir.vlad.oop.figures;

import javafx.scene.shape.Polygon;

public class RightTriangleItem extends AbstractDrawItem{

    private double startX;
    private double startY;

    protected RightTriangleItem() {
        super(DrawType.RIGHT_TRIANGLE);
    }

    @Override
    public void startShape(double x, double y) {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[] {x, y, x, y, x, y});

        startX = x;
        startY = y;

        setShape(polygon);
    }

    @Override
    public void dragShape(double x, double y) {
        Polygon polygon = (Polygon) getShape();

        if(startX > x){
            polygon.getPoints().set(0, startX);
        }else{
            polygon.getPoints().set(0, startX);
        }

        polygon.getPoints().set(1, startY);
        polygon.getPoints().set(2, x);
        polygon.getPoints().set(3, y);
        polygon.getPoints().set(4, startX);
        polygon.getPoints().set(5, y);
    }
}
