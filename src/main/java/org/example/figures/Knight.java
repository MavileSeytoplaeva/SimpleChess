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
    private void setPossibleKnightPositions(Position position) {
        int x = position.horizontalPosition();
        int y = position.verticalPosition();
        for (int i = -1; i <= 1; i += 2) {
            Position possiblePosition = new Position((char) (x + i), (y + 2));
            addPossiblePosition(possiblePosition);
            Position possiblePosition2 = new Position((char) (x + i), (y - 2));
            addPossiblePosition(possiblePosition2);
            Position possiblePosition3 = new Position((char) (x + 2), (y + i));
            addPossiblePosition(possiblePosition3);
            Position possiblePosition4 = new Position((char) (x - 2), (y + i));
            addPossiblePosition(possiblePosition4);
        }
    }
}