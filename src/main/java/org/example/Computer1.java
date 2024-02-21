package org.example;

import org.example.figures.*;

import java.util.*;

public class Computer1 {
    private List<Figure> computersWhiteFigures = new ArrayList<>();
    private final Random random = new Random();
    private ChessBoard chessBoard = new ChessBoard();
    private Set<Position> availableMoves = new HashSet<>();
    private boolean gameOver = false;


    public Map<Position, Figure> takeTurn(Map<Position, Figure> board) {
        getWhiteFigures(board);
        if (getComputersWhiteFigures().isEmpty()) {
            throw new NoMoreFiguresException("No more figures");
        }
        Figure chosenFigure = chooseAFigure(board);
        System.out.println("getWhiteFigures() = " + getComputersWhiteFigures().size());
        setAvailableMoves(generateMoves(board, getAvailableMoves(), chosenFigure));
        while (getAvailableMoves().isEmpty()) { // && !isGameOver()
            chosenFigure = chooseAFigure(board);
            setAvailableMoves(generateMoves(board, getAvailableMoves(), chosenFigure));
            if (getComputersWhiteFigures().contains(FigureType.PAWN) && getAvailableMoves().isEmpty()) {
                throw new NoMoreMovesException("No more moves");
            }

        }
            Position chosenMove = chooseMove();
            if (board.get(chosenMove) == null) {
                Position previousPosition = chosenFigure.getPosition();
                board.put(previousPosition, null);
                board.put(chosenMove, chosenFigure);
                chosenFigure.setPosition(chosenMove);
            } else {
                Position previousPosition = chosenFigure.getPosition();
                board.put(previousPosition, null);
                chosenFigure.setPosition(chosenMove);
                board.put(chosenMove, chosenFigure);
                Figure figure = board.get(chosenMove);
                System.out.println("figure.toString() = " + figure.toString() + "is hacked");
                System.out.println(chosenFigure.getFigureType() + " " + chosenFigure.getColor() + " " + "previous position" + chosenFigure.getPreviousPosition() + " New position" + chosenFigure.getPosition());

//            clearAvailableMoves();
                if (figure.getFigureType() == FigureType.KING) {
                    throw new KingIsHackedException("King is hacked");
                }
            }
            clearComputersWhiteFigures();
            clearAvailableMoves();
            System.out.println(chosenFigure.getFigureType() + " " + chosenFigure.getColor() + " " + "previous position" + chosenFigure.getPreviousPosition() + " New position" + chosenFigure.getPosition());
            return board;
        }

        private Figure chooseAFigure (Map < Position, Figure > board){
            if (getComputersWhiteFigures().isEmpty()) {
                throw new NoMoreFiguresException("no more figures");
            }
            return getComputersWhiteFigures().get(random.nextInt(getComputersWhiteFigures().size()));
        }

        private Set<Position> generateMoves (Map < Position, Figure > board, Set < Position > availableMoves, Figure
        figure){
            return figure.generateMoves(board, availableMoves);
        }

        private Position chooseMove () {
            return getAvailableMoves().stream()
                    .findAny().get();
        }
        private void clearAvailableMoves () {
            availableMoves.clear();
        }
        private Set<Position> getAvailableMoves () {
            return availableMoves;
        }
        private void setAvailableMoves (Set < Position > availableMoves) {
            this.availableMoves = availableMoves;
        }

        private List<Figure> getWhiteFigures (Map < Position, Figure > board){
            for (Map.Entry<Position, Figure> entry : board.entrySet()) {
                if (entry.getValue() != null && entry.getValue().getColor() == FigureColor.WHITE) {
                    computersWhiteFigures.add(entry.getValue());
                }
            }
            return getComputersWhiteFigures();
        }
        public List<Figure> getComputersWhiteFigures () {
            return computersWhiteFigures;
        }
        public void clearComputersWhiteFigures () {
            computersWhiteFigures.clear();
        }
    }
//    private List<Figure> whiteFigures = new ArrayList<>();
//    private ChessBoard chessBoard = new ChessBoard();
//    private Random random = new Random();
//    private Set<Position> availableMoves = new HashSet<>();
//    private boolean gameOver = false;
//
//    public void clearAvailableMoves() {
//        availableMoves.clear();
//    }
//
//    public void setAvailableMoves(Set<Position> availableMoves) {
//        this.availableMoves = availableMoves;
//    }
//
////    private void fillWhiteFigures(Map<Position, Figure> board) {
////        char[] asisX = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
////        whiteFigures.add(new Rock(FigureType.ROCK, FigureColor.WHITE, new Position('a', 1)));
////        whiteFigures.add(new Knight(FigureType.KNIGHT, FigureColor.WHITE, new Position('b', 1)));
////        whiteFigures.add(new Bishop(FigureType.BISHOP, FigureColor.WHITE, new Position('c', 1)));
////        whiteFigures.add(new Queen(FigureType.QUEEN, FigureColor.WHITE, new Position('d', 1)));
////        whiteFigures.add(new King(FigureType.KING, FigureColor.WHITE, new Position('e', 1)));
////        whiteFigures.add(new Bishop(FigureType.BISHOP, FigureColor.WHITE, new Position('f', 1)));
////        whiteFigures.add(new Knight(FigureType.KNIGHT, FigureColor.WHITE, new Position('g', 1)));
////        whiteFigures.add(new Rock(FigureType.ROCK, FigureColor.WHITE, new Position('h', 1)));
////        for (int i = 0; i < asisX.length; i++) {
////            whiteFigures.add(new Pawn(FigureType.PAWN, FigureColor.WHITE, new Position(asisX[i], 2)));
////        }
////    }
//
//    public void putWhitePiecesOnBoard(Map<Position, Figure> board) {
//        fillWhiteFigures();
//        getWhiteFigures().forEach(figure -> {
//            board.put(figure.getPosition(), figure);
//        });
//    }
//
//    public Map<Position, Figure> takeTurn(Map<Position, Figure> board) {
//        Figure chosenFigure = chooseAFigure();
//        System.out.println("getWhiteFigures() = " + getWhiteFigures().size());
//        setAvailableMoves(generateMoves(board, getAvailableMoves(), chosenFigure));
//        while (getAvailableMoves().isEmpty()) {
//            chosenFigure = chooseAFigure();
//            setAvailableMoves(generateMoves(board, getAvailableMoves(), chosenFigure));
//            if (checkIfAnyFiguresHaveAvailableMoves(board)) {
//                throw new NoAvailableFiguresAndMoves("No available moves for white figures");
//            }
//        }
//        Position chosenMove = chooseMove();
//        if (board.get(chosenMove) == null) {
//            Position previousPosition = chosenFigure.getPreviousPosition();
//            board.put(previousPosition, null);
//            board.put(chosenMove, chosenFigure);
//            chosenFigure.setPosition(chosenMove);
//        } else {
//            if (board.get(chosenMove).getFigureType() == FigureType.KING) {
//                throw new KingIsHackedException("King is hacked. Game over");
//            }
////            hackFigure(board.get(chosenMove));
//            Figure figure = board.get(chosenMove);
//            figure.setPosition(null);
//            Position previousPosition = chosenFigure.getPreviousPosition();
//            board.put(previousPosition, null);
//            board.put(chosenMove, chooseAFigure());
//        }
//        System.out.println(chosenFigure.getFigureType() +" "+ chosenFigure.getColor() +" "+ "previous position" +chosenFigure.getPreviousPosition()+ " New position" + chosenFigure.getPosition());
//        clearAvailableMoves();
//        return board;
//    }
//
//    public void hackFigure(Figure figure) {
//        getAvailableMoves().remove(figure);
//    }
//    public void checkIfFigureIsHacked() {
//        whiteFigures.removeIf(figure -> figure.getPosition() == null);
//    }
//    private Figure chooseAFigure() {
//        checkIfFigureIsHacked();
//        if (getWhiteFigures().size() < 16) {
//            throw new KingIsHackedException("<15 white");
//        }
//        return getWhiteFigures().get(random.nextInt(16));
//    }
//
//    private Set<Position> generateMoves(Map<Position, Figure> board, Set<Position> availableMoves, Figure figure) {
//       return figure.generateMoves(board, availableMoves);
//    }
//
//    private Position chooseMove() {
//        return getAvailableMoves().stream()
//                .findFirst().get();
//    }
//    private boolean checkIfAnyFiguresHaveAvailableMoves(Map<Position, Figure> board) {
//        for (Figure piece : getWhiteFigures()) {
//            setAvailableMoves(generateMoves(board, getAvailableMoves(), piece));
//            if (!getAvailableMoves().isEmpty()) {
//                return isGameOver();
//            }
//        }
//        setGameOver(true);
//        return isGameOver();
//    }
//    private boolean isGameOver() {
//        return gameOver;
//    }
//
//    public void setGameOver(boolean gameOver) {
//        this.gameOver = gameOver;
//    }
//    private List<Figure> getWhiteFigures() {
//        return whiteFigures;
//    }
//
//    public Set<Position> getAvailableMoves() {
//        return availableMoves;
//    }
//}
