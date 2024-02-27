package org.example;

import org.example.components.FigureColor;
import org.example.components.FigureType;
import org.example.components.Position;
import org.example.figures.*;

import java.util.*;

public class Computer2 extends Player{
    private final List<Figure> figuresForPawnsChange = new ArrayList<>();
    List<Figure> blackFigures = new ArrayList<>();
    public Computer2() {
        setFiguresForPawnsChange();
    }
    @Override
    public void addToFigures(Figure figure) {
        blackFigures.add(figure);
    }
    public List<Figure> getFigures() {
        return blackFigures;
    }
    @Override
    public int figuresSize() {
        return blackFigures.size();
    }
    @Override
    public void clearFigures() {
        blackFigures.clear();
    }

    @Override
    public void setFiguresForPawnsChange() {
        figuresForPawnsChange.add(new Knight(FigureType.KNIGHT, FigureColor.BLACK, new Position('0', 0)));
        figuresForPawnsChange.add(new Bishop(FigureType.BISHOP, FigureColor.BLACK, new Position('0', 0)));
        figuresForPawnsChange.add(new Rock(FigureType.ROCK, FigureColor.BLACK, new Position('0', 0)));
        figuresForPawnsChange.add(new Queen(FigureType.QUEEN, FigureColor.BLACK, new Position('0', 0)));
    }

    @Override
    public List<Figure> getFiguresForPawnsChange() {
        return figuresForPawnsChange;
    }

    @Override
    protected void getFiguresFromBoard(Map<Position, Figure> board){
        for (Map.Entry<Position, Figure> entry : board.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getColor() == FigureColor.BLACK) {
                addToFigures(entry.getValue());
            }
        }
    }
}

