package org.example.figures;

import org.example.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class KingTest {
    ChessBoard chessBoard = new ChessBoard();
    Computer1 computer1 = new Computer1();
    Computer2 computer2 = new Computer2();

    @Test
    void TestAvailableMoves() {
        computer1.putWhitePiecesOnBoard(chessBoard.getBoard());
        computer2.putBlackPiecesOnBoard(chessBoard.getBoard());
        Set<Position> actualAvailableMoves = new HashSet<>();
        King king = new King(FigureType.KING, FigureColor.WHITE, new Position('d', 5));
        actualAvailableMoves.addAll(king.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize = 8;

        Assert.assertEquals(expectedAvailableMovesSize, actualAvailableMoves.size());
    }
}