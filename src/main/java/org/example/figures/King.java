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
                    checkIfValidOrObstacles(board, availableMoves, position1.horizontalPosition(), position1.verticalPosition());
                });
        availableMoves.remove(position);
        possiblePositions.clear();
    }

    private void setPossibleKingPositions(Position position) {
        List<Position> newPositions = new ArrayList<>();
        int x = position.horizontalPosition();
        int y = position.verticalPosition();
        for (int i = -1; i <= 1; i++) {
            Position possiblePosition = new Position((char) x, y + i);
            addPossiblePosition(possiblePosition);
        }
        Iterator<Position> iterator = getPossiblePositions().iterator();
        while (iterator.hasNext()) {
            Position position1 = iterator.next();
            for (int i = -1; i <= 1; i++) {
                int x1 = position1.horizontalPosition();
                int y1 = position1.verticalPosition();
                newPositions.add(new Position((char) (x1 + i), y1));
            }
        }
        getPossiblePositions().addAll(newPositions);
    }
}
