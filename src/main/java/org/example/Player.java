package org.example;

import org.example.components.FigureType;
import org.example.components.Position;
import org.example.exceptions.KingIsHackedException;
import org.example.exceptions.NoMoreMovesException;
import org.example.figures.*;

import java.util.*;

public abstract class Player {
    private final List<Figure> figuresForPawnsChange = new ArrayList<>();
    private final Random random = new Random();
    private List<Figure> figures = new ArrayList<>();
    private Set<Position> availableMoves = new HashSet<>();
    private boolean gameOver = false;


    public Map<Position, Figure> takeTurn(Map<Position, Figure> board) {
        getFiguresFromBoard(board);
        System.out.println(figuresSize());
        Figure chosenFigure = chooseAFigure(board);
        setAvailableMoves(generateMoves(board, availableMoves, chosenFigure));
        while (getAvailableMoves().isEmpty()) {
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
            Figure figure = board.get(chosenMove);
            if (figure.getFigureType() == FigureType.KING) {
                setGameOver(true);
            }
            Position previousPosition = chosenFigure.getPosition();
            board.put(previousPosition, null);
            chosenFigure.setPosition(chosenMove);
            board.put(chosenMove, chosenFigure);
        }
        if (chosenFigure.getFigureType() == FigureType.PAWN
                && (chosenFigure.getPosition().verticalPosition() == 8 || chosenFigure.getPosition().verticalPosition() == 1)) {
           Figure additionalFigure = changePawnToAnotherFigure(chosenFigure);
            System.out.println(chosenFigure.getFigureType() +" "+ chosenFigure.getColor() + " pawn is now " + additionalFigure + "on position " + additionalFigure.getPosition());
           board.put(additionalFigure.getPosition(), additionalFigure);

        }
        clearFigures();
        clearAvailableMoves();
        System.out.println(chosenFigure.getFigureType() + " " + chosenFigure.getColor() + " " + chosenFigure.getPosition().horizontalPosition() + chosenFigure.getPosition().verticalPosition());
        return board;
    }

    public Figure changePawnToAnotherFigure(Figure chosenFigure) {
            Figure additionalFigure = getFiguresForPawnsChange().get(random.nextInt(getFiguresForPawnsChange().size()));
            additionalFigure.setPosition(chosenFigure.getPosition());
            return additionalFigure;
    }

    protected abstract void getFiguresFromBoard(Map<Position, Figure> board);

    private Figure chooseAFigure(Map<Position, Figure> board) {
        return getFigures().get(random.nextInt(figuresSize()));
    }

    protected Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves, Figure figure) {
        return figure.generateMoves(board, availableMoves);
    }

    private Position chooseMove() {
        return getAvailableMoves().stream()
                .findAny().get();
    }

    public abstract void addToFigures(Figure figure);

    public abstract List<Figure> getFigures();

    public abstract int figuresSize();

    public abstract void clearFigures();

    private void clearAvailableMoves() {
        availableMoves.clear();
    }

    private Set<Position> getAvailableMoves() {
        return availableMoves;
    }

    private void setAvailableMoves(Set<Position> availableMoves) {
        this.availableMoves = availableMoves;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public abstract void setFiguresForPawnsChange();

    public abstract List<Figure> getFiguresForPawnsChange();

}