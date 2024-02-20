package org.example;

import org.example.figures.Figure;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ChessBoard {
    private Map<Position, Figure> board = new HashMap<>();
    public ChessBoard() {
        char[] asisX = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        IntStream.rangeClosed(1, 8).forEach(number -> {
            for (char x : asisX) {
                Position position = new Position(x, number);
                board.put(position, null);
            }
        });
        if (board.size() == 64) {
            System.out.println("64 chessboard is ready");
        }
    }

    public void setBoard(Map<Position, Figure> board) {
        this.board = board;
    }

    public Map<Position, Figure> getBoard() {
        return board;
    }
}
