package org.example;

import org.example.components.FigureColor;
import org.example.components.Position;
import org.example.figures.*;

import java.util.*;

public class Computer2 extends Player{
    List<Figure> blackFigures = new ArrayList<>();
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
    protected void getFiguresFromBoard(Map<Position, Figure> board){
        for (Map.Entry<Position, Figure> entry : board.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getColor() == FigureColor.BLACK) {
                addToFigures(entry.getValue());
            }
        }
    }
}

