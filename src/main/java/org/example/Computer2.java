package org.example;

import org.example.figures.*;

import java.util.*;

public class Computer2{

    private final List<Figure> blackFigures = new ArrayList<>();
    private final Random random = new Random();
    private Set<Position> availableMoves = new HashSet<>();
    private boolean gameOver = false;

    public Map<Position, Figure> takeTurn(Map<Position, Figure> board) {
        Figure chosenFigure = chooseAFigure();
        setAvailableMoves(generateMoves(board, getAvailableMoves(), chosenFigure));
        while (getAvailableMoves().isEmpty() && !isGameOver()) {
            chosenFigure = chooseAFigure();
            setAvailableMoves(generateMoves(board, getAvailableMoves(), chosenFigure));
            if (checkIfAnyFiguresHaveAvailableMoves(board)) {
                throw new RuntimeException("No available moves for black figures");
            }
        }
        Position chosenMove = chooseMove();
        if (board.get(chosenMove) == null) {
            Position previousPosition = chosenFigure.getPosition();
            board.put(chosenMove, chooseAFigure());
            board.put(previousPosition, null);
            chosenFigure.setPosition(chosenMove);
        }
        clearAvailableMoves();
        System.out.println(chosenFigure.getFigureType() + " " + chosenFigure.getColor() + " " + "previous position" + chosenFigure.getPreviousPosition() + " New position" + chosenFigure.getPosition());
        return board;
    }
    private void fillBlackFigures() {
        char[] asisX = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        blackFigures.add(new Rock(FigureType.ROCK, FigureColor.BLACK, new Position('a', 8)));
        blackFigures.add(new Knight(FigureType.KNIGHT, FigureColor.BLACK, new Position('b', 8)));
        blackFigures.add(new Bishop(FigureType.BISHOP, FigureColor.BLACK, new Position('c', 8)));
        blackFigures.add(new Queen(FigureType.QUEEN, FigureColor.BLACK, new Position('d', 8)));
        blackFigures.add(new King(FigureType.KING, FigureColor.BLACK, new Position('e', 8)));
        blackFigures.add(new Bishop(FigureType.BISHOP, FigureColor.BLACK, new Position('f', 8)));
        blackFigures.add(new Knight(FigureType.KNIGHT, FigureColor.BLACK, new Position('g', 8)));
        blackFigures.add(new Rock(FigureType.ROCK, FigureColor.BLACK, new Position('h', 8)));
        for (int i = 0; i < asisX.length; i++) {
            blackFigures.add(new Pawn(FigureType.PAWN, FigureColor.BLACK, new Position(asisX[i], 7)));
        }
    }
    public void putBlackPiecesOnBoard(Map<Position, Figure> board) {
        fillBlackFigures();
        getBlackFigures().forEach(figure -> {
            board.put(figure.getPosition(), figure);
        });
    }

    private boolean checkIfAnyFiguresHaveAvailableMoves(Map<Position, Figure> board) {
        for (Figure piece : getBlackFigures()) {
            setAvailableMoves(generateMoves(board, getAvailableMoves(), piece));
            if (!availableMoves.isEmpty()) {
                return isGameOver();
            }
        }
        setGameOver(true);
        return isGameOver();
    }

    private Figure chooseAFigure() {
        return getBlackFigures().get(random.nextInt(16));
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

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    private void setAvailableMoves(Set<Position> availableMoves) {
        this.availableMoves = availableMoves;
    }

    private List<Figure> getBlackFigures() {
        return blackFigures;
    }
}

