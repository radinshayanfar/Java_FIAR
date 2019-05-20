package fiar;

import fiar.controller.BoardController;
import fiar.view.BoardView;

public class Main {
    public static void main(String[] args) {
        new BoardController(new BoardView());
    }
}
