package fiar.controller;

import fiar.model.Board;
import fiar.model.Position;
import fiar.view.BoardView;
import fiar.view.PieceButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardController implements ActionListener, MouseListener {

    private BoardView view;
    private Board board;

    private PieceButton lastMouseHoverButton = null;

    public BoardController() {
        this.board = new Board();
        this.view = new BoardView(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof PieceButton) {
            PieceButton clickedButton = (PieceButton) e.getSource();
            int lowestY = board.getLowestByColumn(clickedButton.getPosition().getX());
            if (lowestY != -1) {
                PieceButton lowest = view.getPieceButton(new Position(clickedButton.getPosition().getX(), lowestY));
                board.add(clickedButton.getPosition().getX());
                lowest.submit();
                lowest.setBackground(board.getTurn().getColor());
                board.changeTurn();
                view.updateTurnLabel(board.getTurn());

                if (board.hasWon()) {
                    JOptionPane.showMessageDialog(null, board.lastPlayedTurn().getText() + " player won!");
                    System.exit(0);
                }

                if (board.hasDrawn()) {
                    JOptionPane.showMessageDialog(null, "Game Drawn!");
                    System.exit(0);
                }

            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        mouseEntered(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof PieceButton) {
            PieceButton button = (PieceButton) e.getSource();
            int lowestY = board.getLowestByColumn(button.getPosition().getX());
            if (lowestY != -1) {
                PieceButton lowest = view.getPieceButton(new Position(button.getPosition().getX(), lowestY));
                lastMouseHoverButton = lowest;
                lowest.setBackground(board.getTurn().getColor());
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof PieceButton) {
            if (lastMouseHoverButton != null && !lastMouseHoverButton.isSubmitted()) {
                lastMouseHoverButton.setBackground(PieceButton.DEFAULT_COLOR);
            }
            lastMouseHoverButton = null;
        }
    }
}
