package org.example.figures;

import org.example.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class PawnTest {

    ChessBoard chessBoard = new ChessBoard();
    Computer1 computer1 = new Computer1();
    Computer2 computer2 = new Computer2();

    @Test
    void TestAvailableMovesWhitePawn() {
        computer1.putWhitePiecesOnBoard(chessBoard.getBoard());
        computer2.putBlackPiecesOnBoard(chessBoard.getBoard());
        Set<Position> actualAvailableMoves = new HashSet<>();
        Pawn pawn = new Pawn(FigureType.PAWN, FigureColor.WHITE, new Position('e', 2));
        actualAvailableMoves.addAll(pawn.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize = 2;

        Assert.assertEquals(expectedAvailableMovesSize, actualAvailableMoves.size());
    }
    @Test
    void TestAvailableMovesBlackPawn() {
        computer1.putWhitePiecesOnBoard(chessBoard.getBoard());
        computer2.putBlackPiecesOnBoard(chessBoard.getBoard());
        Set<Position> actualAvailableMoves = new HashSet<>();
        Pawn pawn = new Pawn(FigureType.PAWN, FigureColor.BLACK, new Position('e', 7));
        actualAvailableMoves.addAll(pawn.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize = 2;

        Assert.assertEquals(expectedAvailableMovesSize, actualAvailableMoves.size());
    }


}