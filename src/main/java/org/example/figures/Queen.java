package org.example.figures;

import org.example.components.FigureColor;
import org.example.components.FigureType;
import org.example.components.Position;

import java.util.Map;
import java.util.Set;

public class Queen extends Figure {
    public Queen(FigureType figureType, FigureColor color, Position position) {
        super(figureType, color, position);
    }


    @Override
    public Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves) {
        Position position = getPosition();
        setPreviousPosition(position);
        generatePossiblePositions(board, availableMoves, position);
        return availableMoves;
    }
}
