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

class KnightTest {

    ChessBoard chessBoard = new ChessBoard();
    Computer1 computer1 = new Computer1();
    Computer2 computer2 = new Computer2();

    @Test

    void TestAvailableMoves() {
//        computer1.putWhitePiecesOnBoard(chessBoard.getBoard());
//        computer2.putBlackPiecesOnBoard(chessBoard.getBoard());
        Set<Position> actualAvailableMoves = new HashSet<>();
        Knight knight = new Knight(FigureType.KNIGHT, FigureColor.WHITE, new Position('d', 5));
        actualAvailableMoves.addAll(knight.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize = 8;

        Assertions.assertEquals(expectedAvailableMovesSize, actualAvailableMoves.size());
        actualAvailableMoves.clear();

        knight.setPosition(new Position('g', 5));
        actualAvailableMoves.addAll(knight.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize2 = 6;

        Assertions.assertEquals(expectedAvailableMovesSize2, actualAvailableMoves.size());
        actualAvailableMoves.clear();

        knight.setPosition(new Position('d', 4));
        actualAvailableMoves.addAll(knight.generateMoves(chessBoard.getBoard(), actualAvailableMoves));
        int expectedAvailableMovesSize3 = 6;

        Assertions.assertEquals(expectedAvailableMovesSize3, actualAvailableMoves.size());
    }

}