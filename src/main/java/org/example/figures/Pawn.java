package org.example.figures;

import org.example.components.FigureColor;
import org.example.components.FigureType;
import org.example.components.Position;

import java.util.*;

public class Pawn extends Figure {
    public Pawn(FigureType figureType, FigureColor color, Position position) {
        super(figureType, color, position);
    }

    @Override
    public Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves) {
        Position initialPosition = getPosition();
        char positionOnAsisX = initialPosition.horizontalPosition();
        int positionOnAsisY = initialPosition.verticalPosition();
        setPreviousPosition(initialPosition);
        if (board.get(initialPosition).getColor() == FigureColor.WHITE) {
            if (getPosition().verticalPosition() == 2) {
                firstWhitePawnMove(board, availableMoves, positionOnAsisX, positionOnAsisY);
                whitePawnHackMoves(board, availableMoves, positionOnAsisX, positionOnAsisY);
            } else {
                moveWhiteStraightPawn(board, availableMoves, positionOnAsisX, positionOnAsisY);
                whitePawnHackMoves(board, availableMoves, positionOnAsisX, positionOnAsisY);
            }
        } else {
            if (getPosition().verticalPosition() == 7) {
                firstBlackPawnMove(board, availableMoves, positionOnAsisX, positionOnAsisY);
                blackPawnHackMoves(board, availableMoves, positionOnAsisX, positionOnAsisY);
            } else {
                moveBlackStraightPawn(board, availableMoves, positionOnAsisX, positionOnAsisY);
                blackPawnHackMoves(board, availableMoves, positionOnAsisX, positionOnAsisY);
            }
        }
        return availableMoves;
    }

    @Override
    public void addPositionToAvailableMoves(Set<Position> availableMoves, int x, int y) {
        Position position = new Position((char) x, y);
        availableMoves.add(position);
    }

    private void firstBlackPawnMove(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        for (int i = 0; i <= 1; i++) {
            y--;
            if (!shouldBreak(board, x, y)) {
                addPositionToAvailableMoves(availableMoves, x, y);
            } else {
                break;
            }
        }
    }

    private void firstWhitePawnMove(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        for (int i = 0; i <= 1; i++) {
            y++;
            if (!shouldBreak(board, x, y)) {
                addPositionToAvailableMoves(availableMoves, x, y);
            } else {
                break;
            }
        }
    }

    private void blackPawnHackMoves(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y--;
        x--;
        if (isValidPosition(x, y) && isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
        x += 2;
        if (isValidPosition(x, y) && isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    private void whitePawnHackMoves(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y++;
        x--;
        if (isValidPosition(x, y) && isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }
        x += 2;
        if (isValidPosition(x, y) && isObstacle(board, x, y) && obstacle(board, x, y).getColor() != getColor()) {
            addPositionToAvailableMoves(availableMoves, x, y);
        }

    }

    private void moveBlackStraightPawn(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y--;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {// Проверяю, не столкнулся ли конь с другой фигурой или краем поля
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }

    private void moveWhiteStraightPawn(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        y++;
        if (isValidPosition(x, y) && !isObstacle(board, x, y)) {// Проверяю, не столкнулся ли конь с другой фигурой или краем поля
            addPositionToAvailableMoves(availableMoves, x, y);
        }
    }
}
