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

    public Figure(FigureType figureType, FigureColor color, Position position) {
        this.figureType = figureType;
        this.color = color;
        this.position = position;
        this.previousPosition = position;
    }
    public abstract Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves);

    public boolean shouldBreak(Map<Position, Figure> board, int x, int y) {
        return !isValidPosition(x, y) || isObstacle(board, x, y);
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

    public boolean isValidPosition(int x, int y) {
        return x >= 97 && x <= 104 && y >= 1 && y <= 8;
    }
    public void addPositionToAvailableMoves(Set<Position> availableMoves, int x, int y) {
        Position position = new Position((char) x, y);
        availableMoves.add(position);
    }

    public void moveRightPositiveDiagonal(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        while (true) {
            y++; //  Перемещение по правой положительной диагонали
            x++;
            if (isValidPosition(x, y)) {
                if (isObstacle(board, x, y)) {
                    if (obstacle(board, x, y).getColor() != getColor()) {
                        addPositionToAvailableMoves(availableMoves, x, y);
                        break;
                    }
                    break;
                }
                addPositionToAvailableMoves(availableMoves, x, y);
            } else {
                break;
            }
        }
    }

    public void moveRightNegativeDiagonal(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        while (true) {
            y--; // Перемещение по правой отрицательной диагонали
            x++;
            if (isValidPosition(x, y)) {
                if (isObstacle(board, x, y)) {
                    if (obstacle(board, x, y).getColor() != getColor()) {
                        addPositionToAvailableMoves(availableMoves, x, y);
                        break;
                    }
                    break;
                }
                addPositionToAvailableMoves(availableMoves, x, y);
            } else {
                break;
            }
        }
    }

    public void moveLeftPositiveDiagonal(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        while (true) {
            y++; // Перемещение по левой положительной диагонали
            x--;
            if (isValidPosition(x, y)) {
                if (isObstacle(board, x, y)) {
                    if (obstacle(board, x, y).getColor() != getColor()) {
                        addPositionToAvailableMoves(availableMoves, x, y);
                        break;
                    }
                    break;
                }
                addPositionToAvailableMoves(availableMoves, x, y);
            } else {
                break;
            }
        }
    }

    public void moveLeftNegativeDiagonal(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        while (true) {
            y--; // Перемещение по левой отрицательной диагонали
            x--;
            if (isValidPosition(x, y)) {
                if (isObstacle(board, x, y)) {
                    if (obstacle(board, x, y).getColor() != getColor()) {
                        addPositionToAvailableMoves(availableMoves, x, y);
                        break;
                    }
                    break;
                }
                addPositionToAvailableMoves(availableMoves, x, y);
            } else {
                break;
            }
        }
    }

    public void moveStraight(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        while (true) {
            y++; // Перемещение прямо
            if (isValidPosition(x, y)) {
                if (isObstacle(board, x, y)) {
                    if (obstacle(board, x, y).getColor() != getColor()) {
                        addPositionToAvailableMoves(availableMoves, x, y);
                        break;
                    }
                    break;
                }
                addPositionToAvailableMoves(availableMoves, x, y);
            } else {
                break;
            }
        }
    }

    public void moveBack(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        while (true) {
            y--; // Перемещение назад
            if (isValidPosition(x, y)) {
                if (isObstacle(board, x, y)) {
                    if (obstacle(board, x, y).getColor() != getColor()) {
                        addPositionToAvailableMoves(availableMoves, x, y);
                        break;
                    }
                    break;
                }
                addPositionToAvailableMoves(availableMoves, x, y);
            } else {
                break;
            }
        }
    }

    public void moveLeft(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        while (true) {
            x--; // Перемещение влево
            if (isValidPosition(x, y)) {
                if (isObstacle(board, x, y)) {
                    if (obstacle(board, x, y).getColor() != getColor()) {
                        addPositionToAvailableMoves(availableMoves, x, y);
                        break;
                    }
                    break;
                }
                addPositionToAvailableMoves(availableMoves, x, y);
            } else {
                break;
            }
        }
    }

    public void moveRight(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        while (true) {
            x++; // Перемещение направо
            if (isValidPosition(x, y)) {
                if (isObstacle(board, x, y)) {
                    break;
                }
                addPositionToAvailableMoves(availableMoves, x, y);
            } else {
                break;
            }
        }
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
