package org.example.figures;

import org.example.components.FigureColor;
import org.example.components.FigureType;
import org.example.components.Position;

import java.util.*;

public class Bishop extends Figure {
    public Bishop(FigureType figureType, FigureColor color, Position position) {
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
        setPossibleBishopPositions(board, position);
        List<Position> possiblePositions = getPossiblePositions();
        possiblePositions.forEach(position1 -> {
            int x = position1.horizontalPosition();
            int y = position1.verticalPosition();
            addPositionToAvailableMoves(availableMoves, x, y);
        });
        availableMoves.remove(position);
        possiblePositions.clear();
    }

    private void setPossibleBishopPositions(Map<Position, Figure> board, Position position) {
        addPossiblePositions(board, position, 1, 1); // Bishop-like movement
        addPossiblePositions(board, position, -1, -1);
        addPossiblePositions(board, position, 1, -1);
        addPossiblePositions(board, position, -1, 1);
    }
}
