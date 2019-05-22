package fiar.view;

import fiar.controller.BoardController;
import fiar.model.Board;
import fiar.model.Position;
import fiar.model.Turn;

import javax.swing.*;
import java.awt.*;

public class BoardView {

    private JFrame frame;
    private JPanel boardPanel, turnPanel;
    private PieceButton[][] buttons;
    private TurnLabel turnLabel;

    private BoardController controller;

    private void initBoard() {
        boardPanel = new JPanel(new GridLayout(Board.Y_SIZE, Board.X_SIZE));
        boardPanel.setPreferredSize(new Dimension(Board.X_SIZE * 100, Board.Y_SIZE * 100));

        buttons = new PieceButton[Board.X_SIZE][Board.Y_SIZE];
        for (int j = 0; j < Board.Y_SIZE; j++) {
            for (int i = 0; i < Board.X_SIZE; i++) {
                buttons[i][j] = new PieceButton(new Position(i, j));
                buttons[i][j].addActionListener(controller);
                buttons[i][j].addMouseListener(controller);
                boardPanel.add(buttons[i][j]);
            }
        }

        boardPanel.setVisible(true);
    }

    private void initTurn() {
        turnPanel = new JPanel();
        turnLabel = new TurnLabel(Turn.RED);
        turnLabel.setLocation((int) (turnPanel.getSize().getWidth() / 2 - turnLabel.getSize().getWidth() / 2)
                , (int) (turnPanel.getSize().getHeight() / 2 - turnLabel.getSize().getWidth() / 2));
        turnPanel.add(turnLabel);
        turnPanel.setPreferredSize(new Dimension(700, 50));

        turnPanel.setVisible(true);
    }

    private void initFrame() {
        frame = new JFrame("Four in a Row");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(new Dimension(700, 650));
        frame.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().width / 2 - frame.getSize().getWidth() / 2)
                , (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 2 - frame.getSize().getHeight() / 2));

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(turnPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void setIcon() {
        ImageIcon icon = new ImageIcon("./assets/icon.png");
        frame.setIconImage(icon.getImage());

    }

    public BoardView(BoardController controller) {
        this.controller = controller;

        initTurn();
        initBoard();
        initFrame();
        setIcon();

    }

    public PieceButton getPieceButton(Position position) {
        return buttons[position.getX()][position.getY()];
    }

    public void updateTurnLabel(Turn turn) {
        turnLabel.updateTurn(turn);
    }

}
