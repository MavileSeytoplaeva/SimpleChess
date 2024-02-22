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

class PawnTest {

    ChessBoard chessBoard = new ChessBoard();

    @Test
    void TestAvailableMovesWhitePawn() {
        Set<Position> actualAvailableMoves = new HashSet<>();
        Pawn pawn = new Pawn(FigureType.PAWN, FigureColor.WHITE, new Position('e', 2));
        actualAvailableMoves.addAll(pawn.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize = 2;

        Assertions.assertEquals(expectedAvailableMovesSize, actualAvailableMoves.size());
        actualAvailableMoves.clear();

        pawn.setPosition(new Position('e', 1));
        actualAvailableMoves.addAll(pawn.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize2 = 0;

        Assertions.assertEquals(expectedAvailableMovesSize2, actualAvailableMoves.size());
        actualAvailableMoves.clear();

        pawn.setPosition(new Position('d', 5));
        Pawn pawn1 = new Pawn(FigureType.PAWN, FigureColor.BLACK, new Position('c', 6));
        Pawn pawn2 = new Pawn(FigureType.PAWN, FigureColor.BLACK, new Position('e', 6));

        chessBoard.addFigureToBoard(pawn.getPosition(), pawn);
        chessBoard.addFigureToBoard(pawn1.getPosition(), pawn1);
        chessBoard.addFigureToBoard(pawn2.getPosition(), pawn2);
        actualAvailableMoves.addAll(pawn.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize3 = 3;

        Assertions.assertEquals(expectedAvailableMovesSize3, actualAvailableMoves.size());
        actualAvailableMoves.clear();


        pawn.setPosition(new Position('h', 1));
        actualAvailableMoves.addAll(pawn.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize4 =0;

        Assertions.assertEquals(expectedAvailableMovesSize4, actualAvailableMoves.size());
    }
    @Test
    void TestAvailableMovesBlackPawn() {
        Set<Position> actualAvailableMoves = new HashSet<>();
        Pawn pawn = new Pawn(FigureType.PAWN, FigureColor.BLACK, new Position('e', 7));
        actualAvailableMoves.addAll(pawn.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize = 2;

        Assertions.assertEquals(expectedAvailableMovesSize, actualAvailableMoves.size());
        actualAvailableMoves.clear();

        pawn.setPosition(new Position('e', 8));
        actualAvailableMoves.addAll(pawn.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize2 = 0;

        Assertions.assertEquals(expectedAvailableMovesSize2, actualAvailableMoves.size());
        actualAvailableMoves.clear();

        pawn.setPosition(new Position('h', 8));
        actualAvailableMoves.addAll(pawn.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize4 = 0;

        Assertions.assertEquals(expectedAvailableMovesSize4, actualAvailableMoves.size());
    }

    @Test
    void test3PossibleWaysBlackPawn() {
        Set<Position> actualAvailableMoves = new HashSet<>();
        Pawn pawn = new Pawn(FigureType.PAWN, FigureColor.BLACK, new Position('d', 5));
        Pawn pawn1 = new Pawn(FigureType.PAWN, FigureColor.WHITE, new Position('c', 4));
        Pawn pawn2 = new Pawn(FigureType.PAWN, FigureColor.WHITE, new Position('e', 4));
        Pawn pawn3 = new Pawn(FigureType.PAWN, FigureColor.WHITE, new Position('d', 4));

        chessBoard.addFigureToBoard(pawn.getPosition(), pawn);
        chessBoard.addFigureToBoard(pawn1.getPosition(), pawn1);
        chessBoard.addFigureToBoard(pawn2.getPosition(), pawn2);
        chessBoard.addFigureToBoard(pawn3.getPosition(), pawn3);
        actualAvailableMoves.addAll(pawn.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize3 = 2;

        Assertions.assertEquals(expectedAvailableMovesSize3, actualAvailableMoves.size());
        actualAvailableMoves.clear();
    }

    @Test
    void test3PossibleWaysWhitePawn() {
        Set<Position> actualAvailableMoves = new HashSet<>();
        Pawn pawn = new Pawn(FigureType.PAWN, FigureColor.WHITE, new Position('d', 5));
        Pawn pawn1 = new Pawn(FigureType.PAWN, FigureColor.WHITE, new Position('c', 6));
        Pawn pawn2 = new Pawn(FigureType.PAWN, FigureColor.WHITE, new Position('e', 6));
        Pawn pawn3 = new Pawn(FigureType.PAWN, FigureColor.WHITE, new Position('d', 6));

        chessBoard.addFigureToBoard(pawn.getPosition(), pawn);
        chessBoard.addFigureToBoard(pawn1.getPosition(), pawn1);
        chessBoard.addFigureToBoard(pawn2.getPosition(), pawn2);
        chessBoard.addFigureToBoard(pawn3.getPosition(), pawn3);
        actualAvailableMoves.addAll(pawn.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize3 = 0;

        Assertions.assertEquals(expectedAvailableMovesSize3, actualAvailableMoves.size());
        actualAvailableMoves.clear();
    }

    @Test
    void test3PossibleWaysWhitePawnHacksBlackPawn() {
        Set<Position> actualAvailableMoves = new HashSet<>();
        Pawn pawn = new Pawn(FigureType.PAWN, FigureColor.WHITE, new Position('d', 5));
        Pawn pawn1 = new Pawn(FigureType.PAWN, FigureColor.BLACK, new Position('c', 6));
        Pawn pawn2 = new Pawn(FigureType.PAWN, FigureColor.BLACK, new Position('e', 6));
        Pawn pawn3 = new Pawn(FigureType.PAWN, FigureColor.BLACK, new Position('d', 6));

        chessBoard.addFigureToBoard(pawn.getPosition(), pawn);
        chessBoard.addFigureToBoard(pawn1.getPosition(), pawn1);
        chessBoard.addFigureToBoard(pawn2.getPosition(), pawn2);
        chessBoard.addFigureToBoard(pawn3.getPosition(), pawn3);
        actualAvailableMoves.addAll(pawn.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize3 = 2;

        Assertions.assertEquals(expectedAvailableMovesSize3, actualAvailableMoves.size());
        actualAvailableMoves.clear();
    }


}