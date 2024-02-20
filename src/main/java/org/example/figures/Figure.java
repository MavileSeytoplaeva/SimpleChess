package org.example.figures;

import org.example.FigureColor;
import org.example.FigureType;
import org.example.Position;

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
    }
    public abstract Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves);

    public boolean shouldBreak(Map<Position, Figure> board, int x, int y) {
        return !isValidPosition(x, y) || isObstacle(board, x, y);
    }

    public boolean isObstacle(Map<Position, Figure> board, int x, int y) {
        Position position = new Position((char) x, y);
        if (board.get(position) != null) {
            return true; // Фигура присутствует
        } else {
            return false; // Фигура отсутствует
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
            if (!shouldBreak(board, x, y)) {
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
            if (!shouldBreak(board, x, y)) {
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
            if (!shouldBreak(board, x, y)) {
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
            if (!shouldBreak(board, x, y)) {
                addPositionToAvailableMoves(availableMoves, x, y);
            } else {
                break;
            }
        }
    }

    public void moveStraight(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        while (true) {
            y++; // Перемещение прямо
            if (!shouldBreak(board, x, y)) {
                addPositionToAvailableMoves(availableMoves, x, y);
            } else {
                break;
            }
        }
    }

    public void moveBack(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        while (true) {
            y--; // Перемещение назад
            if (!shouldBreak(board, x, y)) {
                addPositionToAvailableMoves(availableMoves, x, y);
            } else {
                break;
            }
        }
    }

    public void moveLeft(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        while (true) {
            x--; // Перемещение влево
            if (!shouldBreak(board, x, y)) {
                addPositionToAvailableMoves(availableMoves, x, y);
            } else {
                break;
            }
        }
    }

    public void moveRight(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        while (true) {
            x++; // Перемещение направо
            if (!shouldBreak(board, x, y)) {
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
}
