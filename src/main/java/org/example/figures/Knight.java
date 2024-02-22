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
        Position position = getPosition();
        setPreviousPosition(position);
        generatePossiblePositions(board, availableMoves, position);
        return availableMoves;
    }

    @Override
    public void generatePossiblePositions(Map<Position, Figure> board, Set<Position> availableMoves, Position position) {
        setPossibleKnightPositions(position);
        List<Position> possiblePositions = getPossiblePositions();
        for (Position position1 : possiblePositions) {
            checkIfValidOrObstacles(board, availableMoves, position1.horizontalPosition(), position1.verticalPosition());
        }
        availableMoves.remove(position);
        possiblePositions.clear();
    }
}