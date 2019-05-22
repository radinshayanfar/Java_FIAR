package fiar.model;

public class Board {

    public static final int X_SIZE = 7, Y_SIZE = 6;

    private Cell[][] cells;
    private Turn turn = Turn.RED;
    private int turnsPlayed = 0;

    private void buildBoard() {
        cells = new Cell[X_SIZE][Y_SIZE];
        for (int i = 0; i < X_SIZE; i++) {
            for (int j = 0; j < Y_SIZE; j++) {
                cells[i][j] = Cell.EMPTY;
            }
        }
    }

    public Board() {
        buildBoard();
    }

    public int getLowestByColumn(int column) {
        for (int i = Y_SIZE - 1; i >= 0; i--) {
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
        int x = X_SIZE - 3, y = Y_SIZE - 3;
        for (int i = 0; i < X_SIZE; i++)
            for (int j = 0; j < Y_SIZE; j++)
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
        return turnsPlayed == X_SIZE * Y_SIZE;
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
