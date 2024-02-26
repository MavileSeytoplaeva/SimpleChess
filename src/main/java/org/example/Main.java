package org.example;

// Упрощенный вариант шахмат
//Стандартное шахматное поле.
//Играют два компьютера
//Стандартная расстановка фигур
//Компьютеры ходят поочереди хаотично выбирая фигуру
//Если фигура может ходить то случайно выбирает любой ход из всех возможных
//Если не может ходить - выбирает другую фигуру
//Игра продолжается пока все игроки могут ходить
public class Main {
    public static void main(String[] args) {
        Computer1 computer1 = new Computer1();
        Computer2 computer2 = new Computer2();
        ChessBoard chessBoard = new ChessBoard();


        while (!computer1.isGameOver() && !computer2.isGameOver()) {
            chessBoard.setBoard(computer1.takeTurn(chessBoard.getBoard()));
            chessBoard.setBoard(computer2.takeTurn(chessBoard.getBoard()));
            if (computer1.isGameOver()) {
                System.out.println("Computer 2 with white figures wins");
            }
            if (computer2.isGameOver()) {
                System.out.println("Computer 1 with black figures wins");
            }
        }
    }
}
