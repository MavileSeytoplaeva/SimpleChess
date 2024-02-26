package org.example.components;

public record Position (char horizontalPosition, int verticalPosition) {

    @Override
    public String toString() {
        return "Position{" +
                "verticalPosition=" + verticalPosition +
                ", horizontalPosition=" + horizontalPosition +
                "} \n";
    }
}
