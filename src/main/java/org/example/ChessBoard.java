package org.example;

import org.example.components.FigureColor;
import org.example.components.FigureType;
import org.example.components.Position;
import org.example.figures.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ChessBoard {
    private Map<Position, Figure> board = new HashMap<>();

    private List<Figure> figures = new ArrayList<>();

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
        putWhitePiecesOnBoard(board);
        putBlackPiecesOnBoard(board);
    }

    public void putBlackPiecesOnBoard(Map<Position, Figure> board) {
        fillBlackFigures();
        getFigures().forEach(figure -> {
            board.put(figure.getPosition(), figure);
        });
    }

    public void putWhitePiecesOnBoard(Map<Position, Figure> board) {
        fillWhiteFigures();
        getFigures().forEach(figure -> {
            board.put(figure.getPosition(), figure);
        });
    }

    private void fillWhiteFigures() {
        char[] asisX = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        figures.add(new Rock(FigureType.ROCK, FigureColor.WHITE, new Position('a', 1)));
        figures.add(new Knight(FigureType.KNIGHT, FigureColor.WHITE, new Position('b', 1)));
        figures.add(new Bishop(FigureType.BISHOP, FigureColor.WHITE, new Position('c', 1)));
        figures.add(new Queen(FigureType.QUEEN, FigureColor.WHITE, new Position('d', 1)));
        figures.add(new King(FigureType.KING, FigureColor.WHITE, new Position('e', 1)));
        figures.add(new Bishop(FigureType.BISHOP, FigureColor.WHITE, new Position('f', 1)));
        figures.add(new Knight(FigureType.KNIGHT, FigureColor.WHITE, new Position('g', 1)));
        figures.add(new Rock(FigureType.ROCK, FigureColor.WHITE, new Position('h', 1)));
        for (int i = 0; i < asisX.length; i++) {
            figures.add(new Pawn(FigureType.PAWN, FigureColor.WHITE, new Position(asisX[i], 2)));
        }
    }

    private void fillBlackFigures() {
        char[] asisX = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        figures.add(new Rock(FigureType.ROCK, FigureColor.BLACK, new Position('a', 8)));
        figures.add(new Knight(FigureType.KNIGHT, FigureColor.BLACK, new Position('b', 8)));
        figures.add(new Bishop(FigureType.BISHOP, FigureColor.BLACK, new Position('c', 8)));
        figures.add(new Queen(FigureType.QUEEN, FigureColor.BLACK, new Position('d', 8)));
        figures.add(new King(FigureType.KING, FigureColor.BLACK, new Position('e', 8)));
        figures.add(new Bishop(FigureType.BISHOP, FigureColor.BLACK, new Position('f', 8)));
        figures.add(new Knight(FigureType.KNIGHT, FigureColor.BLACK, new Position('g', 8)));
        figures.add(new Rock(FigureType.ROCK, FigureColor.BLACK, new Position('h', 8)));
        for (int i = 0; i < asisX.length; i++) {
            figures.add(new Pawn(FigureType.PAWN, FigureColor.BLACK, new Position(asisX[i], 7)));
        }
    }

    public void addFigureToBoard(Position position, Figure figure) {
        this.board.put(position, figure);
    }

    public void setBoard(Map<Position, Figure> board) {
        this.board = board;
    }

    public Map<Position, Figure> getBoard() {
        return board;
    }

    public List<Figure> getFigures() {
        return figures;
    }
}
