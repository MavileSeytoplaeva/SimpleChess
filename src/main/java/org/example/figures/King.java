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
        Position position = getPosition();
        setPreviousPosition(position);
        generatePossiblePositions(board, availableMoves, position);
        return availableMoves;
    }
    @Override
    public void generatePossiblePositions(Map<Position, Figure> board, Set<Position> availableMoves, Position position) {
        setPossibleKingPositions(position);
        List<Position> possiblePositions = getPossiblePositions();
        possiblePositions
                .forEach(position1 -> {
                    for (int i = -1; i <= 1; i++) {
                        int x = position1.horizontalPosition();
                        int y = position1.verticalPosition();
                        x += i;
                        checkIfValidOrObstacles(board, availableMoves, x, y);
                    }
                });
        availableMoves.remove(position);
        possiblePositions.clear();
    }
    private void setPossibleKingPositions(Position position) {
        int x = position.horizontalPosition();
        int y = position.verticalPosition();
        for (int i = -1; i <= 1; i++) {
            Position possiblePosition = new Position((char) x, y + i);
            addPossiblePosition(possiblePosition);
        }
    }
}
