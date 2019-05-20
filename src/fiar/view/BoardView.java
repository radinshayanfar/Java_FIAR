package fiar.view;

import fiar.controller.BoardController;
import fiar.model.Position;
import fiar.model.Turn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardView {
    public static final int XSIZE = 7, YSIZE = 6;

    private JFrame frame;
    private JPanel boardPanel, turnPanel;
    private PieceButton[][] buttons;
    private TurnLabel turnLabel;

    private BoardController controller;

    private void initBoard() {
        boardPanel = new JPanel(new GridLayout(YSIZE, XSIZE));
        boardPanel.setPreferredSize(new Dimension(XSIZE * 100, YSIZE * 100));
//        boardPanel.setLocation(0, 0);

        buttons = new PieceButton[XSIZE][YSIZE];
        for (int i = 0; i < XSIZE; i++) {
            for (int j = 0; j < YSIZE; j++) {
                buttons[i][j] = new PieceButton(new Position(i, j));
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("TEST");
                    }
                });
                boardPanel.add(buttons[i][j], j, i);
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

    public BoardView() {
        initTurn();
        initBoard();
        initFrame();
    }

    public void setController(BoardController controller) {
        this.controller = controller;
    }
}
