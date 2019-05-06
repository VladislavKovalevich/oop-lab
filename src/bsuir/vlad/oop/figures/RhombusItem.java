package bsuir.vlad.oop.figures;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class RhombusItem extends AbstractDrawItem{

    private static RhombusItem instance = new RhombusItem();

    public static RhombusItem getInstance() {
        return instance;
    }

    private double startX;
    private double startY;


    private RhombusItem() {
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

    @Override
    public String save(Shape shape) {
        Polygon polygon = (Polygon) shape;

        String ret = "" + DrawType.RHOMBUS.name() + "|" +
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

        if (!array[0].equals(DrawType.RHOMBUS.name())) {
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
