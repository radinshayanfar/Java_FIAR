package fiar.model;

public class Board {

    public static final int XSIZE = 7, YSIZE = 6;

    private Cell[][] cells;
    private Turn turn = Turn.RED;
    private int turnsPlayed = 0;

    private void buildBoard() {
        cells = new Cell[XSIZE][YSIZE];
        for (int i = 0; i < XSIZE; i++) {
            for (int j = 0; j < YSIZE; j++) {
                cells[i][j] = Cell.EMPTY;
            }
        }
    }

    public Board() {
        buildBoard();
    }

    public int getLowestByColumn(int column) {
        for (int i = YSIZE - 1; i >= 0; i--) {
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
            turnsPlayed++;
            return true;
        }
        return false;
    }

    public void changeTurn() {
        turn = turn.opposite();
    }

    public boolean hasWon() {
        Cell lastTurn = turnToCell(lastPlayedTurn());
        int x = XSIZE - 3, y = YSIZE - 3;
        for (int i = 0; i < XSIZE; i++)
            for (int j = 0; j < YSIZE; j++)
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

    public boolean hasDrawn() {
        return turnsPlayed == XSIZE * YSIZE;
    }

    public Turn lastPlayedTurn() {
        return turn.opposite();
    }

    private static Cell turnToCell(Turn turn) {
        return turn == Turn.RED ? Cell.RED : Cell.BLUE;
    }

    public Turn getTurn() {
        return turn;
    }
}
