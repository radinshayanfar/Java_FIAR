package fiar.view;

import fiar.model.Turn;

import javax.swing.*;

public class TurnLabel extends JLabel {

    public TurnLabel(Turn turn) {
        super();
        updateTurn(turn);
        this.setFont (this.getFont ().deriveFont (20.0f));
        this.setVisible(true);
    }

    public void updateTurn(Turn turn) {
        this.setText(turn.getText() + "'s turn");
        this.setForeground(turn.getColor());
    }
}
