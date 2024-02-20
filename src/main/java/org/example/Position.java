package org.example;

import java.util.Objects;

public class Position  {
    private int verticalPosition;
    private char horizontalPosition;

    public Position (char horizontalPosition, int verticalPosition) {
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
    }

    public int getVerticalPosition() {
        return verticalPosition;
    }
    public char getHorizontalPosition() {
        return horizontalPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return horizontalPosition == position.horizontalPosition && verticalPosition == position.verticalPosition;
    }
    @Override
    public int hashCode() {
        return Objects.hash(horizontalPosition, verticalPosition);
    }
    @Override
    public String toString() {
        return "Position{" +
                "verticalPosition=" + verticalPosition +
                ", horizontalPosition=" + horizontalPosition +
                "} \n";
    }
}
