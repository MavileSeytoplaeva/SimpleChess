package org.example.components;

public record Position (char horizontalPosition, int verticalPosition) {

//    private char horizontalPosition;
//    private int verticalPosition;
//
//    public Position(char horizontalPosition, char verticalPosition) {
//        this.horizontalPosition = horizontalPosition;
//        this.verticalPosition = verticalPosition;
//    }
//
//    public char getHorizontalPosition() {
//        return horizontalPosition;
//    }
//
//    public char getVerticalPosition() {
//        return verticalPosition;
//    }

    @Override
    public String toString() {
        return "Position{" +
                "verticalPosition=" + verticalPosition +
                ", horizontalPosition=" + horizontalPosition +
                "} \n";
    }
}
