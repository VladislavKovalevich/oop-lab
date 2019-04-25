package bsuir.vlad.oop.figures;

import javafx.scene.shape.Ellipse;

public class OvalItem extends AbstractDrawItem{

    private  double leftUpX;
    private  double leftUpY;

   // protected OvalItem() {
   //     super(DrawType.OVAL);
  //  }

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
}
