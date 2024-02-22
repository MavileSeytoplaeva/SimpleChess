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

    public void generatePossiblePositions(Map<Position, Figure> board, Set<Position> availableMoves, Position position) {
        setPossiblePositions(board, position);
        List<Position> possiblePositions = getPossiblePositions();
        possiblePositions.forEach(position1 -> {
            int x = position1.horizontalPosition();
            int y = position1.verticalPosition();

            addPositionToAvailableMoves(availableMoves, x, y);
        });
        availableMoves.remove(position);
        possiblePositions.clear();
    }
    public void setPossiblePositions(Map<Position, Figure> board, Position position) {
        if (getFigureType() == FigureType.QUEEN) {
            addPossibleRookPositions(board, position); // Queen has rook-like movements
            addPossibleBishopPositions(board, position); // Queen also has bishop-like movements
        } else if (getFigureType() == FigureType.ROCK) {
            addPossibleRookPositions(board, position); // Rook has only rook-like movements
        } else if (getFigureType() == FigureType.BISHOP) {
            addPossibleBishopPositions(board, position); // Bishop has only bishop-like movements
        } else if (getFigureType() == FigureType.PAWN) {
            setPossiblePawnPositions(position);
        } else if (getFigureType() == FigureType.KNIGHT) {
            setPossibleKnightPositions(position);
        }
    }
    public void setPossibleKnightPositions(Position position) {
        int x = position.horizontalPosition();
        int y = position.verticalPosition();
        for (int i = -1; i <= 1; i += 2) {
            Position possiblePosition = new Position((char) (x + i), (y + 2));
            possiblePositions.add(possiblePosition);
            Position possiblePosition2 = new Position((char) (x + i), (y - 2));
            possiblePositions.add(possiblePosition2);
            Position possiblePosition3 = new Position((char) (x + 2), (y + i));
            possiblePositions.add(possiblePosition3);
            Position possiblePosition4 = new Position((char) (x - 2), (y + i));
            possiblePositions.add(possiblePosition4);
        }
    }

    private void addPossibleRookPositions(Map<Position, Figure> board, Position position) {
        addPossiblePositions(board, position, 1, 0); // Rook-like movement
        addPossiblePositions(board, position, -1, 0);
        addPossiblePositions(board, position, 0, 1);
        addPossiblePositions(board, position, 0, -1);
    }

    private void addPossibleBishopPositions(Map<Position, Figure> board, Position position) {
        addPossiblePositions(board, position, 1, 1); // Bishop-like movement
        addPossiblePositions(board, position, -1, -1);
        addPossiblePositions(board, position, 1, -1);
        addPossiblePositions(board, position, -1, 1);
    }

    public void setPossiblePawnPositions(Position position) {
        int x = position.horizontalPosition();
        int y = position.verticalPosition();
        if (getColor() == FigureColor.WHITE) {
            if (y == 2) {
                for (int i = 1; i <= 2; i++) {
                    Position position1 = new Position((char) x, (y + i));
                    possiblePositions.add(position1);
                }
            } else {
                for (int i = -1, j = 1; i <= 1; i++) {
                    Position position1 = new Position((char) (x + i), (y + j));
                    possiblePositions.add(position1);
                }
            }
        } else if (getColor() == FigureColor.BLACK) {
            if (y == 7) {
                for (int i = 1; i <= 2; i++) {
                    Position position1 = new Position((char) x, (y - i));
                    possiblePositions.add(position1);
                }
            } else {
                for (int i = -1, j = 1; i <= 1; i++) {
                    Position position1 = new Position((char) (x + i), (y - j));
                    possiblePositions.add(position1);
                }
            }
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
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }
    public void setPossibleKingPositions(Position position) {
        int x = position.horizontalPosition();
        int y = position.verticalPosition();
        for (int i = -1; i <= 1; i++) {
            Position possiblePosition = new Position((char) x, y + i);
            possiblePositions.add(possiblePosition);
        }
    }
    private void addPossiblePositions(Map<Position, Figure> board, Position position, int horizontalIncrement, int verticalIncrement) {

        int x = position.horizontalPosition();
        int y = position.verticalPosition();

        while (isValidPosition(x + horizontalIncrement, y + verticalIncrement)) {
            x += horizontalIncrement;
            y += verticalIncrement;

            if (isObstacle(board, x, y)) {
                Figure obstacle = obstacle(board, x, y);
                if (obstacle.getColor() != getColor()) {
                    possiblePositions.add(new Position((char) x, y));
                }
                break;
            }

            possiblePositions.add(new Position((char) x, y));
        }
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
