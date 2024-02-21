package org.example.figures;

import org.example.components.FigureColor;
import org.example.components.FigureType;
import org.example.components.Position;

import java.util.*;

public class Knight extends Figure {

    public Knight(FigureType figureType, FigureColor color, Position position) {
        super(figureType, color, position);
    }

    @Override
    public Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves) {
        Position initialPosition = getPosition();
        char positionOnAsisX = initialPosition.horizontalPosition();
        int positionOnAsisY = initialPosition.verticalPosition();
        setPreviousPosition(initialPosition);
        moveStraightAndRight(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveStraightAndLeft(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveBackAndRight(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveBackAndLeft(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveRightAndStraight(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveRightAndBack(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveLeftAndStraight(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveLeftAndBack(board, availableMoves, positionOnAsisX, positionOnAsisY);
        return availableMoves;
    }

    private void moveStraightAndRight(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y += 2; // Перемещение прямо 2 шага и направо
        x++;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    private void moveStraightAndLeft(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y += 2; // Перемещение прямо 2 шага и налево
        x--;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    private void moveBackAndRight(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y -= 2; // Перемещение прямо 2 назад и направо
        x++;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    private void moveBackAndLeft(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y -= 2; // Перемещение прямо 2 назад и налево
        x--;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    private void moveRightAndStraight(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y++; // Перемещение направо 2 шага и прямо
        x += 2;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    private void moveRightAndBack(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y--; // Перемещение направо 2 шага и назад
        x += 2;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    private void moveLeftAndStraight(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y++; // Перемещение налево 2 шага и вперёд
        x -= 2;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    private void moveLeftAndBack(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y--; // Перемещение налево 2 шага и назад
        x -= 2;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {
            addPositionToAvailableMoves(availableMoves, x, y);
        } else if (isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }
}