package fiar.view;

import fiar.model.Position;
import fiar.model.Turn;

import javax.swing.*;
import java.awt.*;

public class PieceButton extends JButton {
    private Color color = Color.LIGHT_GRAY;
    private Position position;

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
}
