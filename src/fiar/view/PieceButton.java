package fiar.view;

import fiar.model.Position;

import javax.swing.*;
import java.awt.*;

public class PieceButton extends JButton {
    public static final Color DEFAULT_COLOR = Color.LIGHT_GRAY;

    private Color color = DEFAULT_COLOR;
    private Position position;
    private boolean submitted = false;

    public PieceButton(Position position) {
        super();
        this.position = position;
        this.setBackground(color);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void submit() {
        this.submitted = true;
    }
}
