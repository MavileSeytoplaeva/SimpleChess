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
        Position position = getPosition();
        setPreviousPosition(position);
        generatePossiblePositions(board, availableMoves, position);
        return availableMoves;
    }

    @Override
    public void generatePossiblePositions(Map<Position, Figure> board, Set<Position> availableMoves, Position position) {
        setPossibleRookPositions(board, position);
        List<Position> possiblePositions = getPossiblePositions();
        possiblePositions.forEach(position1 -> {
            int x = position1.horizontalPosition();
            int y = position1.verticalPosition();

            addPositionToAvailableMoves(availableMoves, x, y);
        });
        availableMoves.remove(position);
        possiblePositions.clear();
    }

    private void setPossibleRookPositions(Map<Position, Figure> board, Position position) {
        addPossiblePositions(board, position, 1, 0); // Rook-like movement
        addPossiblePositions(board, position, -1, 0);
        addPossiblePositions(board, position, 0, 1);
        addPossiblePositions(board, position, 0, -1);
    }

}
