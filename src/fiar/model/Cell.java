package fiar.model;

import fiar.view.PieceButton;

import java.awt.*;

public enum Cell {

    RED(Color.red), BLUE(Color.blue), EMPTY(PieceButton.DEFAULT_COLOR);

    private Color color;

    Cell(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
