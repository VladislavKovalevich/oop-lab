package bsuir.vlad.oop.figures;

import java.util.HashMap;
import java.util.Map;

public enum DrawType {
    LINE("Line"),
    RHOMBUS("Rhombus"),
    OVAL("Oval"),
    ISO_TRIANGLE("Iso-Triangle"),
    RECTANGLE("Rectangle"),
    RIGHT_TRIANGLE("Right Triangle");

    private String displayName;

    private static Map<String, DrawType> typeByText = new HashMap<>();

    static {
        typeByText.put(LINE.getDisplayName(), LINE);
        typeByText.put(RHOMBUS.getDisplayName(), RHOMBUS);
        typeByText.put(OVAL.getDisplayName(), OVAL);
        typeByText.put(ISO_TRIANGLE.getDisplayName(), ISO_TRIANGLE);
        typeByText.put(RECTANGLE.getDisplayName(), RECTANGLE);
        typeByText.put(RIGHT_TRIANGLE.getDisplayName(), RIGHT_TRIANGLE);
    }

    DrawType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static DrawType getTypeByText(String text) {
        return typeByText.get(text);
    }

}
