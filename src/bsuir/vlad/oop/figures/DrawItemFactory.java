package bsuir.vlad.oop.figures;

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

        switch (type) {
            case LINE:            return LineItem.getInstance().load(array);
            case RHOMBUS:         return RhombusItem.getInstance().load(array);
            case OVAL:            return OvalItem.getInstance().load(array);
            case RECTANGLE:       return RectangleItem.getInstance().load(array);
            case ISO_TRIANGLE:    return IsoTriangleItem.getInstance().load(array);
            case RIGHT_TRIANGLE:  return RightTriangleItem.getInstance().load(array);
        }

        return null;
    }

}
