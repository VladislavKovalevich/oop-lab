package bsuir.vlad.oop.figures;

public class DrawItemBuilder {

    public static DrawItem buildDrawItem(DrawType type) {
        switch (type) {
            case LINE:        return new LineItem();
        }

        return null;
    }
}
