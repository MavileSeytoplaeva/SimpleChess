package org.example.figures;

import org.example.*;
import org.example.ChessBoard;
import org.example.components.FigureColor;
import org.example.components.FigureType;
import org.example.components.Position;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class RockTest {

    ChessBoard chessBoard = new ChessBoard();
    Computer1 computer1 = new Computer1();
    Computer2 computer2 = new Computer2();

    @Test
    void TestAvailableMovesWhitePawn() {
        Set<Position> actualAvailableMoves = new HashSet<>();
//        computer1.putWhitePiecesOnBoard(chessBoard.getBoard());
//        computer2.putBlackPiecesOnBoard(chessBoard.getBoard());
        Rock rock = new Rock(FigureType.ROCK, FigureColor.WHITE, new Position('d', 5));
        actualAvailableMoves.addAll(rock.generateMoves(chessBoard.getBoard(), actualAvailableMoves));

        int expectedAvailableMovesSize = 11;

        Assert.assertEquals(expectedAvailableMovesSize, actualAvailableMoves.size());
    }

}