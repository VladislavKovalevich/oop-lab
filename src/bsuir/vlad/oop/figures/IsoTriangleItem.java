package bsuir.vlad.oop.figures;

import javafx.scene.shape.Polygon;

public class IsoTriangleItem extends AbstractDrawItem{

    private double startX;
    private double startY;

    //protected IsoTriangleItem() {
   //     super(DrawType.ISO_TRIANGLE);
   // }

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
            polygon.getPoints().set(0, Math.abs(startX - Math.abs(startX - x) / 2));
        }else{
            polygon.getPoints().set(0, Math.abs(startX + Math.abs(startX - x) / 2));
        }
        polygon.getPoints().set(1, startY);
        polygon.getPoints().set(2, x);
        polygon.getPoints().set(3, y);
        polygon.getPoints().set(4, startX);
        polygon.getPoints().set(5, y);
    }
}
