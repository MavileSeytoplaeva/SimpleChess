package org.example;

import org.example.components.FigureColor;
import org.example.components.FigureType;
import org.example.components.Position;
import org.example.exceptions.KingIsHackedException;
import org.example.exceptions.NoMoreMovesException;
import org.example.figures.*;

import java.util.*;

public class Computer2 {
    private final Random random = new Random();
    private List<Figure> computersBlackFigures = new ArrayList<>();
    private Set<Position> availableMoves = new HashSet<>();

    public Map<Position, Figure> takeTurn(Map<Position, Figure> board) {
        getBlackFigures(board);
        System.out.println(getComputersBlackFigures().size());
        Figure chosenFigure = chooseAFigure(board);
        setAvailableMoves(generateMoves(board, getAvailableMoves(), chosenFigure));
        while ((getAvailableMoves().isEmpty())) {
            if (getComputersBlackFigures().size() <= 8) {
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
        clearComputersBlackFigures();
        clearAvailableMoves();
        System.out.println(chosenFigure.getFigureType() + " " + chosenFigure.getColor() + " " + "previous position" + chosenFigure.getPreviousPosition() + " New position" + chosenFigure.getPosition());
        return board;
    }

    private List<Figure> getBlackFigures(Map<Position, Figure> board) {
        for (Map.Entry<Position, Figure> entry : board.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getColor() == FigureColor.BLACK) {
                computersBlackFigures.add(entry.getValue());
            }
        }
        return getComputersBlackFigures();
    }

    private Figure chooseAFigure(Map<Position, Figure> board) {
        return getComputersBlackFigures().get(random.nextInt(getComputersBlackFigures().size()));
    }

    private Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves, Figure figure) {
        return figure.generateMoves(board, availableMoves);
    }

    private Position chooseMove() {
        return getAvailableMoves().stream()
                .findAny().get();
    }

    private void checkIfOnlyPawnsLeft() {
        List<Figure> pawns = new ArrayList<>();
        getComputersBlackFigures().forEach(figure -> {
            if (figure.getFigureType() == FigureType.PAWN) {
                pawns.add(figure);
            }
        });
        if (getComputersBlackFigures().size() == pawns.size()) {
            throw new NoMoreMovesException("Only pawns left - no more moves");
        }
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

    public List<Figure> getComputersBlackFigures() {
        return computersBlackFigures;
    }

    public void clearComputersBlackFigures() {
        computersBlackFigures.clear();
    }
}

