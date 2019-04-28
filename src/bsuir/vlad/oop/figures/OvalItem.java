package bsuir.vlad.oop.figures;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class OvalItem extends AbstractDrawItem{

    private static OvalItem instance = new OvalItem();

    public static OvalItem getInstance() {
        return instance;
    }

    private  double leftUpX;
    private  double leftUpY;

    private OvalItem() {
        super(DrawType.OVAL);
    }

    @Override
    public void startShape(double x, double y) {
        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(x);
        ellipse.setCenterY(y);
        ellipse.setRadiusX(1);
        ellipse.setRadiusY(1);

        leftUpX = x;
        leftUpY = y;

        setShape(ellipse);
    }

    @Override
    public void dragShape(double x, double y) {
        Ellipse ellipse = (Ellipse) getShape();

        double lenX = Math.abs((leftUpX - x)/2);
        double lenY = Math.abs((leftUpY - y)/2);

        if(leftUpX < x){
            ellipse.setCenterX(leftUpX + lenX);
        }else{
            ellipse.setCenterX(x + lenX);
        }

        if(leftUpY < y){
            ellipse.setCenterY(leftUpY + lenY);
        }else{
            ellipse.setCenterY(y + lenY);
        }

        ellipse.setRadiusX(Math.abs(lenX));
        ellipse.setRadiusY(Math.abs(lenY));
    }

    @Override
    public String save(Shape shape) {
        Ellipse ellipse = (Ellipse) shape;

        String ret = "" + DrawType.OVAL.name() + "|" +
                ellipse.getCenterX() + "|" +
                ellipse.getCenterY() + "|" +
                ellipse.getRadiusX() + "|" +
                ellipse.getRadiusY();

        return ret;
    }

    @Override
    public Shape load(String[] array) {
        if ((array == null) || (array.length != 5)) {
            return null;
        }

        if (!array[0].equals(DrawType.OVAL.name())) {
            return null;
        }

        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(Double.valueOf(array[1]));
        ellipse.setCenterY(Double.valueOf(array[2]));
        ellipse.setRadiusX(Double.valueOf(array[3]));
        ellipse.setRadiusY(Double.valueOf(array[4]));

        addShape(ellipse);

        return ellipse;
    }

}
