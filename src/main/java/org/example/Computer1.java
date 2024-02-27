package org.example;

import org.example.components.FigureColor;
import org.example.components.FigureType;
import org.example.components.Position;
import org.example.figures.*;

import java.util.*;

public class Computer1 extends Player {
    private final List<Figure> figuresForPawnsChange = new ArrayList<>();
    List<Figure> whiteFigures = new ArrayList<>();

    public Computer1() {
        setFiguresForPawnsChange();
    }

    @Override
    public void addToFigures(Figure figure) {
        whiteFigures.add(figure);
    }
    @Override
    public List<Figure> getFigures() {
        return whiteFigures;
    }
    @Override
    public int figuresSize() {
        return whiteFigures.size();
    }
    @Override
    public void clearFigures() {
        whiteFigures.clear();
    }
    @Override
    protected void getFiguresFromBoard(Map<Position, Figure> board){
        for (Map.Entry<Position, Figure> entry : board.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getColor() == FigureColor.WHITE) {
                addToFigures(entry.getValue());
            }
        }
    }
    @Override
    public void setFiguresForPawnsChange() {
        figuresForPawnsChange.add(new Knight(FigureType.KNIGHT, FigureColor.WHITE, new Position('0', 0)));
        figuresForPawnsChange.add(new Bishop(FigureType.BISHOP, FigureColor.WHITE, new Position('0', 0)));
        figuresForPawnsChange.add(new Rock(FigureType.ROCK, FigureColor.WHITE, new Position('0', 0)));
        figuresForPawnsChange.add(new Queen(FigureType.QUEEN, FigureColor.WHITE, new Position('0', 0)));
    }
    @Override
    public List<Figure> getFiguresForPawnsChange() {
        return figuresForPawnsChange;
    }
}