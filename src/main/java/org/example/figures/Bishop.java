package org.example.figures;

import org.example.FigureColor;
import org.example.FigureType;
import org.example.Position;

import java.util.*;

public class Bishop extends Figure {
    public Bishop(FigureType figureType, FigureColor color, Position initialPosition) {
        super(figureType, color, initialPosition);
    }

    @Override
    public Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves) {
        Position initialPosition = getPosition();
        char positionOnAsisX = initialPosition.getHorizontalPosition();
        int positionOnAsisY = initialPosition.getVerticalPosition();
        setPreviousPosition(initialPosition);
        moveRightPositiveDiagonal(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveRightNegativeDiagonal(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveLeftPositiveDiagonal(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveLeftNegativeDiagonal(board, availableMoves, positionOnAsisX, positionOnAsisY);
        return availableMoves;
    }
}