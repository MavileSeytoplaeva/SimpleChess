package org.example;

import org.example.components.FigureColor;
import org.example.components.Position;
import org.example.figures.*;

import java.util.*;

public class Computer1 extends Player {
    List<Figure> whiteFigures = new ArrayList<>();
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
}