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

class BishopTest {
    ChessBoard chessBoard = new ChessBoard();

    @Test
    void TestAvailableMoves() {
        Set<Position> actualAvailableMoves = new HashSet<>();
        Bishop bishop = new Bishop(FigureType.BISHOP, FigureColor.WHITE, new Position('d', 5));
        actualAvailableMoves.addAll(bishop.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize = 8;

        Assertions.assertEquals(expectedAvailableMovesSize, actualAvailableMoves.size());
        actualAvailableMoves.clear();

        bishop.setPosition(new Position('a', 1));
        actualAvailableMoves.addAll(bishop.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize1 = 0;

        Assertions.assertEquals(expectedAvailableMovesSize1, actualAvailableMoves.size());
        actualAvailableMoves.clear();

        bishop.setPosition(new Position('a', 8));
        actualAvailableMoves.addAll(bishop.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize2 = 1;

        Assertions.assertEquals(expectedAvailableMovesSize2, actualAvailableMoves.size());
        actualAvailableMoves.clear();

        bishop.setPosition(new Position('a', 2));
        actualAvailableMoves.addAll(bishop.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize3 = 5;

        Assertions.assertEquals(expectedAvailableMovesSize3, actualAvailableMoves.size());
        actualAvailableMoves.clear();

        bishop.setPosition(new Position('a', 7));
        actualAvailableMoves.addAll(bishop.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize4= 5;

        Assertions.assertEquals(expectedAvailableMovesSize4, actualAvailableMoves.size());
        actualAvailableMoves.clear();

        bishop.setPosition(new Position('g', 7));
        actualAvailableMoves.addAll(bishop.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize5= 7;

        Assertions.assertEquals(expectedAvailableMovesSize5, actualAvailableMoves.size());
    }

}