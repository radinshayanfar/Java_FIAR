package fiar.model;

import java.awt.*;

public enum Turn {
    RED("Red", Color.red), BLUE("Blue", Color.blue);

    private String text;
    private Color color;

    Turn(String text, Color color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        return color;
    }
}
