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
        setPossiblePawnPositions(board, position);
        int x = position.horizontalPosition();
        int y = position.verticalPosition();
        List<Position> possiblePositions = getPossiblePositions();
        possiblePositions.forEach(position1 -> {
            if (isValidPosition(position1.horizontalPosition(), position1.verticalPosition())) {
                addPositionToAvailableMoves(availableMoves, position1.horizontalPosition(), position1.verticalPosition());
            }
        });
        availableMoves.remove(position);
        possiblePositions.clear();
    }


    private void setPossiblePawnPositions(Map<Position, Figure> board, Position position) {
        int x = position.horizontalPosition();
        int y = position.verticalPosition();
        if (getColor() == FigureColor.WHITE) {
            if (y == 2) {
                for (int i = 1; i <= 2; i++) {
                    Position position1 = new Position((char) x, (y + i));
                    if (isObstacle(board, position1.horizontalPosition(), position1.verticalPosition())) {
                        break;
                    }
                    addPossiblePosition(position1);
                }
                for (int i = -1, j = 1; i <= 1; i += 2) {
                    Position position1 = new Position((char) (x + i), (y + j));
                    if (!isObstacle(board, position1.horizontalPosition(), position1.verticalPosition())) {
                        continue;
                    }
                    addPossiblePosition(position1);
                }
            } else {
                for (int i = -1, j = 1; i <= 1; i++) {
                    Position position1 = new Position((char) (x + i), (y + j));
                    int x1 = position1.horizontalPosition();
                    int y1 = position1.verticalPosition();
                    if (x1 == x) {
                        if (isObstacle(board, x1, y1)) {
                            continue;
                        } else {
                            addPossiblePosition(position1);
                        }
                    }
                    if (isObstacle(board, x1, y1)) {
                        if (obstacle(board, x1, y1).getColor() != getColor()) {
                            addPossiblePosition(position1);
                        }
                    }
                }

            }
        } else if (getColor() == FigureColor.BLACK) {
            if (y == 7) {
                for (int i = 1; i <= 2; i++) {
                    Position position1 = new Position((char) x, (y - i));
                    if (isObstacle(board, position1.horizontalPosition(), position1.verticalPosition())) {
                        break;
                    }
                    addPossiblePosition(position1);
                }
                for (int i = -1, j = 1; i <= 1; i += 2) {
                    Position position1 = new Position((char) (x + i), (y - j));
                    if (!isObstacle(board, position1.horizontalPosition(), position1.verticalPosition())) {
                        continue;
                    }
                    addPossiblePosition(position1);
                }
            } else {
                for (int i = -1, j = 1; i <= 1; i++) {
                    Position position1 = new Position((char) (x + i), (y - j));
                    int x1 = position1.horizontalPosition();
                    int y1 = position1.verticalPosition();
                    if (position1.horizontalPosition() == x) {
                        if (isObstacle(board, x1, y1)) {
                            continue;
                        } else {
                            addPossiblePosition(position1);
                        }
                    }
                    if (isObstacle(board, x1, y1)) {
                        if (obstacle(board, x1, y1).getColor() != getColor()) {
                            addPossiblePosition(position1);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void checkIfValidOrObstacles(Map<Position, Figure> board, Set<Position> availableMoves, int x, int y) {
        if (isValidPosition(x, y) && isObstacle(board, x, y)) {
            if (obstacle(board, x, y).getColor() != getColor()) {
                addPositionToAvailableMoves(availableMoves, x, y);
            }
        }
    }

}
