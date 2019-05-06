package bsuir.vlad.oop.figures;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class DrawItemFactory {

    private static DrawItemFactory instance = new DrawItemFactory();

    public static DrawItemFactory getInstance() {
        return instance;
    }

    private DrawItemFactory() {
    }

    public DrawItem getDrawItem(DrawType type) {
        switch (type) {
            case LINE:            return LineItem.getInstance();
            case RHOMBUS:         return RhombusItem.getInstance();
            case OVAL:            return OvalItem.getInstance();
            case RECTANGLE:       return RectangleItem.getInstance();
            case ISO_TRIANGLE:    return IsoTriangleItem.getInstance();
            case RIGHT_TRIANGLE:  return RightTriangleItem.getInstance();
        }

        return null;
    }

    public Shape loadItem(String itemStr) {
        if ((itemStr == null) || (itemStr.trim().length() == 0)) {
            return null;
        }

        if (itemStr.indexOf('|') == -1) {
            return null;
        }

        String typeStr = itemStr.substring(0, itemStr.indexOf('|'));
        if ((typeStr == null) || (typeStr.trim().length() == 0)) {
            return null;
        }

        DrawType type = DrawType.valueOf(typeStr);
        String[] array = itemStr.split("\\|");

        Shape shape = null;

        switch (type) {
            case LINE:
                shape = LineItem.getInstance().load(array);
                break;
            case RHOMBUS:
                shape = RhombusItem.getInstance().load(array);
                break;
            case OVAL:
                shape = OvalItem.getInstance().load(array);
                break;
            case RECTANGLE:
                shape = RectangleItem.getInstance().load(array);
                break;
            case ISO_TRIANGLE:
                shape = IsoTriangleItem.getInstance().load(array);
                break;
            case RIGHT_TRIANGLE:
                shape = RightTriangleItem.getInstance().load(array);
                break;
        }

        if (shape != null) {
            shape.setFill(Color.valueOf(array[array.length - 3]));
            shape.setStroke(Color.valueOf(array[array.length - 2]));
            shape.setStrokeWidth(Double.valueOf(array[array.length - 1]));
        }

        return shape;
    }

}
