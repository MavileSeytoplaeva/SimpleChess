package org.example;

import org.example.components.FigureType;
import org.example.components.Position;
import org.example.exceptions.KingIsHackedException;
import org.example.exceptions.NoMoreMovesException;
import org.example.figures.*;

import java.util.*;

public abstract class Player {
    private final Random random = new Random();
    private List<Figure> figures = new ArrayList<>();
    private Set<Position> availableMoves = new HashSet<>();


    public Map<Position, Figure> takeTurn(Map<Position, Figure> board) {
        getFiguresFromBoard(board);
        System.out.println(figuresSize());
        Figure chosenFigure = chooseAFigure(board);
        setAvailableMoves(generateMoves(board, availableMoves, chosenFigure));
        while (getAvailableMoves().isEmpty()) {
            if (figuresSize() <= 8) {
                checkIfOnlyPawnsLeft();
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
            if (figure.getFigureType() == FigureType.KING) {
                throw new KingIsHackedException("King is hacked");
            }
        }
        clearFigures();
        clearAvailableMoves();
        System.out.println(chosenFigure.getFigureType() + " " + chosenFigure.getColor() + " " + "previous position" + chosenFigure.getPreviousPosition() + " New position" + chosenFigure.getPosition());
        return board;
    }

    protected abstract void getFiguresFromBoard(Map<Position, Figure> board);

    private Figure chooseAFigure(Map<Position, Figure> board) {
        return getFigures().get(random.nextInt(figuresSize()));
    }

    protected Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves, Figure figure){
        return figure.generateMoves(board, availableMoves);
    }

    private Position chooseMove() {
        return getAvailableMoves().stream()
                .findAny().get();
    }

    private void checkIfOnlyPawnsLeft() {
        List<Figure> pawns = new ArrayList<>();
        getFigures().forEach(figure -> {
            if (figure.getFigureType() == FigureType.PAWN) {
                pawns.add(figure);
            }
        });
        if (figuresSize() == pawns.size()) {
            throw new NoMoreMovesException("Only pawns left - no more moves");
        }
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
}