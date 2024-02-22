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
        Position position = getPosition();
        setPreviousPosition(position);
        generatePossiblePositions(board, availableMoves, position);
        return availableMoves;
    }
    @Override
    public void generatePossiblePositions(Map<Position, Figure> board, Set<Position> availableMoves, Position position) {
        setPossiblePawnPositions(position);
        int x = position.horizontalPosition();
        int y = position.verticalPosition();
        List<Position> possiblePositions = getPossiblePositions();
        for (Position position1 : possiblePositions) {
            if ((position1.verticalPosition() == y + 1 && position1.horizontalPosition() == x)
                    || (position1.verticalPosition() == y - 1 && position1.horizontalPosition() == x)) {
                if (isObstacle(board, position1.horizontalPosition(), position1.verticalPosition())) {
                    availableMoves.remove(position1);
                } else {
                    availableMoves.add(position1);
                }
            } else {
                checkIfValidOrObstacles(board, availableMoves, position1.horizontalPosition(), position1.verticalPosition());
            }
        }
        availableMoves.remove(position);
        possiblePositions.clear();
    }

}
