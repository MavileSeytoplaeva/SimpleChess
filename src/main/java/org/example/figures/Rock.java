package org.example.figures;

import org.example.components.FigureColor;
import org.example.components.FigureType;
import org.example.components.Position;

import java.util.*;

public class Rock extends Figure {

    public Rock(FigureType figureType, FigureColor color, Position position) {
        super(figureType, color, position);
    }

    @Override
    public Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves) {
        Position initialPosition = getPosition();
        char positionOnAsisX = initialPosition.horizontalPosition();
        int positionOnAsisY = initialPosition.verticalPosition();
        setPreviousPosition(initialPosition);
        moveStraight(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveRight(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveLeft(board, availableMoves, positionOnAsisX, positionOnAsisY);
        moveBack(board, availableMoves, positionOnAsisX, positionOnAsisY);
        return availableMoves;
    }
}
