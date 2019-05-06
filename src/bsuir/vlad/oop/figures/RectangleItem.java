package bsuir.vlad.oop.figures;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class RectangleItem extends AbstractDrawItem{

    private static RectangleItem instance = new RectangleItem();

    public static RectangleItem getInstance() {
        return instance;
    }

    private double leftUpX;
    private double leftUpY;

    private RectangleItem() {
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

    @Override
    public String save(Shape shape) {
        Polygon polygon = (Polygon) shape;

        String ret = "" + DrawType.RECTANGLE.name() + "|" +
                (polygon.getPoints().get(0) + shape.getLayoutX()) + "|" +
                (polygon.getPoints().get(1) + shape.getLayoutY()) + "|" +
                (polygon.getPoints().get(2) + shape.getLayoutX()) + "|" +
                (polygon.getPoints().get(3) + shape.getLayoutY()) + "|" +
                (polygon.getPoints().get(4) + shape.getLayoutX()) + "|" +
                (polygon.getPoints().get(5) + shape.getLayoutY()) + "|" +
                (polygon.getPoints().get(6) + shape.getLayoutX()) + "|" +
                (polygon.getPoints().get(7) + shape.getLayoutY());

        return ret;
    }

    @Override
    public Shape load(String[] array) {
        if ((array == null) || (array.length != 12)) {
            return null;
        }

        if (!array[0].equals(DrawType.RECTANGLE.name())) {
            return null;
        }

        for(int i = 1; i < 9; i++){
            if(i % 2 == 0){
               if((Double.valueOf(array[i]) > 800) || (Double.valueOf(array[i]) < 0)){
                   return null;
               }
            }else{
                if((Double.valueOf(array[i]) > 1060) || (Double.valueOf(array[i]) < 0)){
                    return null;
                }
            }
        }

        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
                Double.valueOf(array[1]),
                Double.valueOf(array[2]),
                Double.valueOf(array[3]),
                Double.valueOf(array[4]),
                Double.valueOf(array[5]),
                Double.valueOf(array[6]),
                Double.valueOf(array[7]),
                Double.valueOf(array[8])
        });

        addShape(polygon);

        return polygon;
    }

}
