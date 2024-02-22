package org.example.figures;

import org.example.*;
import org.example.ChessBoard;
import org.example.components.FigureColor;
import org.example.components.FigureType;
import org.example.components.Position;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class QueenTest {

    ChessBoard chessBoard = new ChessBoard();

    @Test
    void TestAvailableMovesWhitePawn() {
        Set<Position> actualAvailableMoves = new HashSet<>();
        Queen queen = new Queen(FigureType.QUEEN, FigureColor.WHITE, new Position('d', 5));
        actualAvailableMoves.addAll(queen.generateMoves(chessBoard.getBoard(), actualAvailableMoves));

        int expectedAvailableMovesSize = 19;

        Assertions.assertEquals(expectedAvailableMovesSize, actualAvailableMoves.size());
    }

}