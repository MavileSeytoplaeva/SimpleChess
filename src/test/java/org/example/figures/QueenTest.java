package org.example.figures;

import org.example.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class QueenTest {

    ChessBoard chessBoard = new ChessBoard();
    Computer1 computer1 = new Computer1();
    Computer2 computer2 = new Computer2();

    @Test
    void TestAvailableMovesWhitePawn() {
        Set<Position> actualAvailableMoves = new HashSet<>();
        computer1.putWhitePiecesOnBoard(chessBoard.getBoard());
        computer2.putBlackPiecesOnBoard(chessBoard.getBoard());
        Queen queen = new Queen(FigureType.QUEEN, FigureColor.WHITE, new Position('d', 5));
        actualAvailableMoves.addAll(queen.generateMoves(chessBoard.getBoard(), actualAvailableMoves));

        int expectedAvailableMovesSize = 16;

        Assert.assertEquals(expectedAvailableMovesSize, actualAvailableMoves.size());
    }

}