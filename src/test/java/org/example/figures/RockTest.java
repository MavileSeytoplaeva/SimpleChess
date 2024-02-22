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

class RockTest {

    ChessBoard chessBoard = new ChessBoard();

    @Test
    void TestAvailableMovesWhitePawn() {
        Set<Position> actualAvailableMoves = new HashSet<>();
        Rock rock = new Rock(FigureType.ROCK, FigureColor.WHITE, new Position('d', 5));
        actualAvailableMoves.addAll(rock.generateMoves(chessBoard.getBoard(), actualAvailableMoves));

        int expectedAvailableMovesSize = 11;

        Assertions.assertEquals(expectedAvailableMovesSize, actualAvailableMoves.size());
        actualAvailableMoves.clear();

        rock.setPosition(new Position('a', 7));
        actualAvailableMoves.addAll(rock.generateMoves(chessBoard.getBoard(), actualAvailableMoves));

        int expectedAvailableMovesSize1 = 6;

        Assertions.assertEquals(expectedAvailableMovesSize1, actualAvailableMoves.size());

        actualAvailableMoves.clear();

        rock.setPosition(new Position('c', 2));
        actualAvailableMoves.addAll(rock.generateMoves(chessBoard.getBoard(), actualAvailableMoves));

        int expectedAvailableMovesSize2 = 5;

        Assertions.assertEquals(expectedAvailableMovesSize2, actualAvailableMoves.size());
    }

}