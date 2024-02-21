package org.example;

import org.example.figures.*;

import java.util.*;

public class Computer2 {

    private List<Figure> computersBlackFigures = new ArrayList<>();
    private final Random random = new Random();
    private ChessBoard chessBoard = new ChessBoard();
    private Set<Position> availableMoves = new HashSet<>();
    private boolean gameOver = false;

    public Map<Position, Figure> takeTurn(Map<Position, Figure> board) {
        getBlackFigures(board);
        if (getComputersBlackFigures().isEmpty()) {
            throw new NoMoreFiguresException("No more figures");
        }
        Figure chosenFigure = chooseAFigure(board);
        System.out.println("getBlackFigures() = " + getComputersBlackFigures().size());
        setAvailableMoves(generateMoves(board, getAvailableMoves(), chosenFigure));
        while ((getAvailableMoves().isEmpty())) {// !isGameOver()
            if (getComputersBlackFigures().contains(FigureType.PAWN) && getAvailableMoves().isEmpty()) {
                throw new NoMoreMovesException("No more moves");
            }
            chosenFigure = chooseAFigure(board);
            setAvailableMoves(generateMoves(board, getAvailableMoves(), chosenFigure));
        }
        Position chosenMove = chooseMove();
        if (board.get(chosenMove) == null) {
            Position previousPosition = chosenFigure.getPosition();
            board.put(previousPosition, null);
            board.put(chosenMove, chosenFigure);
            chosenFigure.setPosition(chosenMove);
        } else {
            Position previousPosition = chosenFigure.getPosition();
            board.put(previousPosition, null);
            chosenFigure.setPosition(chosenMove);
            board.put(chosenMove, chosenFigure);
            Figure figure = board.get(chosenMove);
            System.out.println("figure.toString() = " + figure.toString() + "is hacked");
            if (figure.getFigureType() == FigureType.KING) {
                throw new KingIsHackedException("King is hacked");
            }
        }
        clearComputersBlackFigures();
        clearAvailableMoves();
        System.out.println(chosenFigure.getFigureType() + " " + chosenFigure.getColor() + " " + "previous position" + chosenFigure.getPreviousPosition() + " New position" + chosenFigure.getPosition());
        return board;
    }

    private Figure chooseAFigure(Map<Position, Figure> board) {
        if (getComputersBlackFigures().isEmpty()) {
            throw new NoMoreFiguresException("no more figures");
        }
        return getComputersBlackFigures().get(random.nextInt(getComputersBlackFigures().size()));
    }

    private Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves, Figure figure) {
        return figure.generateMoves(board, availableMoves);
    }

    private Position chooseMove() {
        return getAvailableMoves().stream()
                .findAny().get();
    }

    private void clearAvailableMoves() {
        availableMoves.clear();
    }

    private Set<Position> getAvailableMoves() {
        return availableMoves;
    }

    private void setAvailableMoves(Set<Position> availableMoves) {
        this.availableMoves = availableMoves;
    }

    private List<Figure> getBlackFigures(Map<Position, Figure> board) {
        for (Map.Entry<Position, Figure> entry : board.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getColor() == FigureColor.BLACK) {
                computersBlackFigures.add(entry.getValue());
            }
        }
        return getComputersBlackFigures();
    }

    public List<Figure> getComputersBlackFigures() {
        return computersBlackFigures;
    }

    public void clearComputersBlackFigures() {
        computersBlackFigures.clear();
    }
}

