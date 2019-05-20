package fiar.controller;

import fiar.view.BoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardController implements ActionListener {

    BoardView view;

    public BoardController(BoardView view) {
        view.setController(this);
        this.view = view;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("test");
    }
}
