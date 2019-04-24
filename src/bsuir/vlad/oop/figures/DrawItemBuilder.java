package bsuir.vlad.oop.figures;

public class DrawItemBuilder {

    public static DrawItem buildDrawItem(DrawType type) {
        switch (type) {
            case LINE:            return new LineItem();
            case RHOMBUS:         return new RhombusItem();
            case OVAL:            return new OvalItem();
            case RECTANGLE:       return new RectangleItem();
            case ISO_TRIANGLE:    return new IsoTriangleItem();
            case RIGHT_TRIANGLE:  return new RightTriangleItem();
        }

        return null;
    }
}
