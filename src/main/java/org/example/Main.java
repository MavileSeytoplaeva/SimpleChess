package org.example;

import java.util.ArrayList;
import java.util.List;

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
        List<Player> players = new ArrayList<>();
        players.add(computer1);
        players.add(computer2);


        while (!computer1.isGameOver() && !computer2.isGameOver()) {
            for (Player player : players) {
                chessBoard.setBoard(player.takeTurn(chessBoard.getBoard()));
                if (computer1.isGameOver()) {
                    System.out.println("Computer 2 with white figures wins");
                    break;
                }
                if (computer2.isGameOver()) {
                    System.out.println("Computer 1 with black figures wins");
                    break;
                }
            }
        }
    }
}
