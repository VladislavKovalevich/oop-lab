package bsuir.vlad.oop.figures;

import javafx.scene.shape.Polygon;

public class RectangleItem extends AbstractDrawItem{

    private double leftUpX;
    private double leftUpY;

    protected RectangleItem() {
        super(DrawType.RECTANGLE);
    }

    @Override
    public void startShape(double x, double y) {
        Polygon rectangle = new Polygon();
        rectangle.getPoints().addAll(new Double[]{x,y,x,y,x,y,x,y});

        leftUpX = x;
        leftUpY = y;

        setShape(rectangle);
    }

    @Override
    public void dragShape(double x, double y) {
        Polygon rectangle = (Polygon)getShape();

        rectangle.getPoints().set(0,leftUpX);
        rectangle.getPoints().set(1,leftUpY);
        rectangle.getPoints().set(2,leftUpX);

        rectangle.getPoints().set(4,x);
        rectangle.getPoints().set(5,y);
        rectangle.getPoints().set(7, leftUpY);

        if(x <= leftUpX){
            rectangle.getPoints().set(6, leftUpX - Math.abs(leftUpX - x));
        }else{
            rectangle.getPoints().set(6, leftUpX + Math.abs(leftUpX - x));
        }

        if(y <= leftUpY){
            rectangle.getPoints().set(3,leftUpY - Math.abs(leftUpY - y));
        }else{
            rectangle.getPoints().set(3,leftUpY + Math.abs(leftUpY - y));
        }
    }
}
