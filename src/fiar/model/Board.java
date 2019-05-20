package fiar.model;

import fiar.view.BoardView;

public class Board {

    private Cell[][] cells;
    private Turn turn = Turn.RED;
    private int turnsPlayed = 0;

    private void buildBoard() {
        cells = new Cell[BoardView.XSIZE][BoardView.YSIZE];
        for (int i = 0; i < BoardView.XSIZE; i++) {
            for (int j = 0; j < BoardView.YSIZE; j++) {
                cells[i][j] = Cell.EMPTY;
            }
        }
    }

    public Board() {
        buildBoard();
    }

    public int getLowestByColumn(int column) {
        for (int i = BoardView.YSIZE - 1; i >= 0; i--) {
            if (cells[column][i] == Cell.EMPTY) {
                return i;
            }
        }
        return -1;
    }

    public boolean add(int column) {
        int lowest = getLowestByColumn(column);
        if (lowest != -1) {
            cells[column][lowest] = turnToCell(turn);
            turn = turn.opposite();
            turnsPlayed++;
            return true;
        }
        return false;
    }

    public boolean hasWon() {
        Cell lastTurn = turnToCell(lastPlayedTurn());
        int x = BoardView.XSIZE - 3, y = BoardView.YSIZE - 3;
        for (int i = 0; i < BoardView.XSIZE; i++)
            for (int j = 0; j < BoardView.YSIZE; j++)
                if ((j < y && cells[i][j] == lastTurn && cells[i][j + 1] == lastTurn && cells[i][j + 2] == lastTurn && cells[i][j + 3] == lastTurn)
                        || (i < x && cells[i][j] == lastTurn && cells[i + 1][j] == lastTurn && cells[i + 2][j] == lastTurn && cells[i + 3][j] == lastTurn)
                        || (j < y && i < x && cells[i][j] == lastTurn && cells[i + 1][j + 1] == lastTurn && cells[i + 2][j + 2] == lastTurn
                        && cells[i + 3][j + 3] == lastTurn)
                        || (j > 2 && i < x && cells[i][j] == lastTurn && cells[i + 1][j - 1] == lastTurn && cells[i + 2][j - 2] == lastTurn
                        && cells[i + 3][j - 3] == lastTurn)) {
                    return true;
                }
        return false;
    }

    public boolean hasDrawned() {
        return turnsPlayed == BoardView.XSIZE * BoardView.YSIZE;
    }

    public Turn lastPlayedTurn() {
        return turn.opposite();
    }

    private static Cell turnToCell(Turn turn) {
        return turn == Turn.RED ? Cell.RED : Cell.BLUE;
    }
}
