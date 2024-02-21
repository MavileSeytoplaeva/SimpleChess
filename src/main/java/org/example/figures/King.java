package org.example.figures;

import org.example.components.FigureColor;
import org.example.components.FigureType;
import org.example.components.Position;

import java.util.*;

public class King extends Figure {
    public King(FigureType figureType, FigureColor color, Position position) {
        super(figureType, color, position);
    }

    @Override
    public Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves) {
        Position initialPosition = getPosition();
        char positionOnAsisX = initialPosition.horizontalPosition();
        int positionOnAsisY = initialPosition.verticalPosition();
        setPreviousPosition(initialPosition);
        moveStraight(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveBack(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveRight(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveLeft(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveLeftNegativeDiagonal(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveRightNegativeDiagonal(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveLeftPositiveDiagonal(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveRightPositiveDiagonal(board, availableMoves, positionOnAsisX, positionOnAsisY);
        return availableMoves;
    }

    @Override
    public void moveLeftNegativeDiagonal(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y--;
        x--;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    @Override
    public void moveLeftPositiveDiagonal(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y++;
        x--;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    @Override
    public void moveRightNegativeDiagonal(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y--;
        x++;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    @Override
    public void moveRightPositiveDiagonal(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y++;
        x++;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    @Override
    public void moveStraight(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y++;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    @Override
    public void moveBack(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y--;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    @Override
    public void moveLeft(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        x--;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    @Override
    public void moveRight(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        x++;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }
}
