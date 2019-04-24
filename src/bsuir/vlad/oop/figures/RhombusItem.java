package bsuir.vlad.oop.figures;

import javafx.scene.shape.Polygon;

public class RhombusItem extends AbstractDrawItem{

    private double startX;
    private double startY;


    protected RhombusItem() {
        super(DrawType.RHOMBUS);
    }

    @Override
    public void startShape(double x, double y) {
        Polygon romb = new Polygon();
        romb.getPoints().addAll(new Double[]{x,y,x,y,x,y,x,y});

        startX = x;
        startY = y;

        setShape(romb);
    }

    @Override
    public void dragShape(double x, double y) {
        Polygon romb = (Polygon) getShape();

        double lenX = Math.abs((startX - x)/2);
        double lenY = Math.abs((startY - y)/2);

        romb.getPoints().set(0,startX);
        romb.getPoints().set(4,x);

        romb.getPoints().set(3,startY);
        romb.getPoints().set(7,y);

        if(startX > x){
            romb.getPoints().set(2,startX - lenX);
            romb.getPoints().set(6,startX - lenX);
        }else{
            romb.getPoints().set(2,startX + lenX);
            romb.getPoints().set(6,startX + lenX);
        }

        if(startY < y){
            romb.getPoints().set(1,startY + lenY);
            romb.getPoints().set(5,startY + lenY);
        }else{
            romb.getPoints().set(1,startY - lenY);
            romb.getPoints().set(5,startY - lenY);
        }
    }

}
