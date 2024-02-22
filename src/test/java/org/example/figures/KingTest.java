package org.example.figures;

import org.example.*;
import org.example.ChessBoard;
import org.example.components.FigureColor;
import org.example.components.FigureType;
import org.example.components.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class KingTest {
    ChessBoard chessBoard = new ChessBoard();

    @Test
    void TestAvailableMoves() {
        Set<Position> actualAvailableMoves = new HashSet<>();
        King king = new King(FigureType.KING, FigureColor.WHITE, new Position('d', 6));

        actualAvailableMoves.addAll(king.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize = 8;

        Assertions.assertEquals(expectedAvailableMovesSize, actualAvailableMoves.size());
        actualAvailableMoves.clear();

        king.setPosition(new Position('d',3));
        actualAvailableMoves.addAll(king.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize2 = 5;

        Assertions.assertEquals(expectedAvailableMovesSize2, actualAvailableMoves.size());
        actualAvailableMoves.clear();

        king.setPosition(new Position('a',1));
        actualAvailableMoves.addAll(king.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize3 = 0;

        Assertions.assertEquals(expectedAvailableMovesSize3, actualAvailableMoves.size());
        actualAvailableMoves.clear();

        king.setPosition(new Position('a',8));
        actualAvailableMoves.addAll(king.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize4 = 3;

        Assertions.assertEquals(expectedAvailableMovesSize4, actualAvailableMoves.size());
    }
}