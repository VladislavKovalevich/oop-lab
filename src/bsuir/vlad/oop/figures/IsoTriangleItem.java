package bsuir.vlad.oop.figures;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class IsoTriangleItem extends AbstractDrawItem{

    private static IsoTriangleItem instance = new IsoTriangleItem();

    public static IsoTriangleItem getInstance() {
        return instance;
    }

    private double startX;
    private double startY;

    private IsoTriangleItem() {
        super(DrawType.ISO_TRIANGLE);
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

    @Override
    public String save(Shape shape) {
        Polygon polygon = (Polygon) shape;

        String ret = "" + DrawType.ISO_TRIANGLE.name() + "|" +
                polygon.getPoints().get(0) + "|" +
                polygon.getPoints().get(1) + "|" +
                polygon.getPoints().get(2) + "|" +
                polygon.getPoints().get(3) + "|" +
                polygon.getPoints().get(4) + "|" +
                polygon.getPoints().get(5);

        return ret;
    }

    @Override
    public Shape load(String[] array) {
        if ((array == null) || (array.length != 7)) {
            return null;
        }

        if (!array[0].equals(DrawType.ISO_TRIANGLE.name())) {
            return null;
        }

        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
                Double.valueOf(array[1]),
                Double.valueOf(array[2]),
                Double.valueOf(array[3]),
                Double.valueOf(array[4]),
                Double.valueOf(array[5]),
                Double.valueOf(array[6])
        });

        addShape(polygon);

        return polygon;
    }

}
