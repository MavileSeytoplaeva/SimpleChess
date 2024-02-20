package org.example;

import org.example.figures.*;

import java.util.*;

public class Computer1{
    private List<Figure> whiteFigures = new ArrayList<>();
    private Random random = new Random();
    private Set<Position> availableMoves = new HashSet<>();
    private boolean gameOver = false;

    public void clearAvailableMoves() {
        availableMoves.clear();
    }

    public void setAvailableMoves(Set<Position> availableMoves) {
        this.availableMoves = availableMoves;
    }

    private void fillWhiteFigures() {
        char[] asisX = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        whiteFigures.add(new Rock(FigureType.ROCK, FigureColor.WHITE, new Position('a', 1)));
        whiteFigures.add(new Knight(FigureType.KNIGHT, FigureColor.WHITE, new Position('b', 1)));
        whiteFigures.add(new Bishop(FigureType.BISHOP, FigureColor.WHITE, new Position('c', 1)));
        whiteFigures.add(new Queen(FigureType.QUEEN, FigureColor.WHITE, new Position('d', 1)));
        whiteFigures.add(new King(FigureType.KING, FigureColor.WHITE, new Position('e', 1)));
        whiteFigures.add(new Bishop(FigureType.BISHOP, FigureColor.WHITE, new Position('f', 1)));
        whiteFigures.add(new Knight(FigureType.KNIGHT, FigureColor.WHITE, new Position('g', 1)));
        whiteFigures.add(new Rock(FigureType.ROCK, FigureColor.WHITE, new Position('h', 1)));
        for (int i = 0; i < asisX.length; i++) {
            whiteFigures.add(new Pawn(FigureType.PAWN, FigureColor.WHITE, new Position(asisX[i], 2)));
        }
    }

    public void putWhitePiecesOnBoard(Map<Position, Figure> board) {
        fillWhiteFigures();
        getWhiteFigures().forEach(figure -> {
            board.put(figure.getPosition(), figure);
        });
    }

    public Map<Position, Figure> takeTurn(Map<Position, Figure> board) {
        Figure chosenFigure = chooseAFigure();
        setAvailableMoves(generateMoves(board, getAvailableMoves(), chosenFigure));
        while (getAvailableMoves().isEmpty()) {
            chosenFigure = chooseAFigure();
            setAvailableMoves(generateMoves(board, getAvailableMoves(), chosenFigure));
            if (checkIfAnyFiguresHaveAvailableMoves(board)) {
                throw new RuntimeException("No available moves for white figures");
            }
        }
        Position chosenMove = chooseMove();
        if (board.get(chosenMove) == null) {
            Position previousPosition = chosenFigure.getPreviousPosition();
            board.put(previousPosition, null);
            board.put(chosenMove, chooseAFigure());
            chosenFigure.setPosition(chosenMove);
        }
        System.out.println(chosenFigure.getFigureType() +" "+ chosenFigure.getColor() +" "+ "previous position" +chosenFigure.getPreviousPosition()+ " New position" + chosenFigure.getPosition());
        clearAvailableMoves();
        return board;
    }

    private Figure chooseAFigure() {
        return getWhiteFigures().get(random.nextInt(16));
    }

    private Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves, Figure figure) {
       return figure.generateMoves(board, availableMoves);
    }

    private Position chooseMove() {
        return getAvailableMoves().stream()
                .findFirst().get();
    }
    private boolean checkIfAnyFiguresHaveAvailableMoves(Map<Position, Figure> board) {
        for (Figure piece : getWhiteFigures()) {
            setAvailableMoves(generateMoves(board, getAvailableMoves(), piece));
            if (!getAvailableMoves().isEmpty()) {
                return isGameOver();
            }
        }
        setGameOver(true);
        return isGameOver();
    }
    private boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    private List<Figure> getWhiteFigures() {
        return whiteFigures;
    }

    public Set<Position> getAvailableMoves() {
        return availableMoves;
    }
}
