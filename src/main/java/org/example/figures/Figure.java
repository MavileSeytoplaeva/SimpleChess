package org.example.figures;

import org.example.components.FigureColor;
import org.example.components.FigureType;
import org.example.components.Position;

import java.util.*;

public abstract class Figure {
    private final FigureType figureType;
    private final FigureColor color;
    private Position position;
    private Position previousPosition;
    private List<Position> possiblePositions = new ArrayList<>();

    public Figure(FigureType figureType, FigureColor color, Position position) {
        this.figureType = figureType;
        this.color = color;
        this.position = position;
        this.previousPosition = position;
    }
    public abstract Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves);

    public abstract void generatePossiblePositions(Map<Position, Figure> board, Set<Position> availableMoves, Position position);

    public void addPossiblePositions(Map<Position, Figure> board, Position position, int horizontalIncrement, int verticalIncrement) {
        int x = position.horizontalPosition();
        int y = position.verticalPosition();

        while (isValidPosition(x + horizontalIncrement, y + verticalIncrement)) {
            x += horizontalIncrement;
            y += verticalIncrement;
            if (isObstacle(board, x, y)) {
                Figure obstacle = obstacle(board, x, y);
                if (obstacle.getColor() != getColor()) {
                    addPossiblePosition(new Position((char) x, y));
                }
                break;
            }
            addPossiblePosition(new Position((char) x, y));
        }
    }

    public void addPositionToAvailableMoves(Set<Position> availableMoves, int x, int y) {
        Position position = new Position((char) x, y);
        availableMoves.add(position);
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 97 && x <= 104 && y >= 1 && y <= 8;
    }

    public boolean isObstacle(Map<Position, Figure> board, int x, int y) {
        Position position = new Position((char) x, y);
        return board.get(position) != null;
    }

    public Figure obstacle(Map<Position, Figure> board, int x, int y) {
        Position position = new Position((char) x, y);
        if (board.get(position) != null) {
            Figure figure = board.get(position);
            return figure;
        } else {
            return null;
        }
    }

    public void checkIfValidOrObstacles(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isValidPosition(x, y) && isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }
    public void addPossiblePosition(Position position) {
        possiblePositions.add(position);
    }

    public List<Position> getPossiblePositions() {
        return possiblePositions;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public FigureType getFigureType() {
        return figureType;
    }

    public FigureColor getColor() {
        return color;
    }

    public Position getPreviousPosition() {
        return previousPosition;
    }

    public void setPreviousPosition(Position previousPosition) {
        this.previousPosition = previousPosition;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "figureType=" + figureType +
                ", color=" + color +
                ", position=" + position +
                ", previousPosition=" + previousPosition +
                '}';
    }
}
