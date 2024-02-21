package org.example;

import org.example.components.FigureColor;
import org.example.components.FigureType;
import org.example.components.Position;
import org.example.exceptions.KingIsHackedException;
import org.example.exceptions.NoMoreMovesException;
import org.example.figures.*;

import java.util.*;

public class Computer1 {
    private final Random random = new Random();
    private List<Figure> computersWhiteFigures = new ArrayList<>();
    private Set<Position> availableMoves = new HashSet<>();

    public Map<Position, Figure> takeTurn(Map<Position, Figure> board) {
        getWhiteFigures(board);
        System.out.println(getComputersWhiteFigures().size());
        Figure chosenFigure = chooseAFigure(board);
        setAvailableMoves(generateMoves(board, getAvailableMoves(), chosenFigure));
        while (getAvailableMoves().isEmpty()) { // && !isGameOver()
            if (getComputersWhiteFigures().size() <= 8) {
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
        clearComputersWhiteFigures();
        clearAvailableMoves();
        System.out.println(chosenFigure.getFigureType() + " " + chosenFigure.getColor() + " " + "previous position" + chosenFigure.getPreviousPosition() + " New position" + chosenFigure.getPosition());
        return board;
    }

    private Figure chooseAFigure(Map<Position, Figure> board) {
        return getComputersWhiteFigures().get(random.nextInt(getComputersWhiteFigures().size()));
    }

    private Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves, Figure
            figure) {
        return figure.generateMoves(board, availableMoves);
    }

    private Position chooseMove() {
        return getAvailableMoves().stream()
                .findAny().get();
    }

    private void checkIfOnlyPawnsLeft() {
        List<Figure> pawns = new ArrayList<>();
        getComputersWhiteFigures().forEach(figure -> {
            if (figure.getFigureType() == FigureType.PAWN) {
                pawns.add(figure);
            }
        });
        if (getComputersWhiteFigures().size() == pawns.size()) {
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

    private List<Figure> getWhiteFigures(Map<Position, Figure> board) {
        for (Map.Entry<Position, Figure> entry : board.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getColor() == FigureColor.WHITE) {
                computersWhiteFigures.add(entry.getValue());
            }
        }
        return getComputersWhiteFigures();
    }

    public List<Figure> getComputersWhiteFigures() {
        return computersWhiteFigures;
    }

    public void clearComputersWhiteFigures() {
        computersWhiteFigures.clear();
    }
}