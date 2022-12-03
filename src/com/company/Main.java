package com.company;

import java.lang.reflect.Array;
import java.util.*;

public class Main{
    private static final int BOARD_SIZE = 4;
    private static int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    private static boolean playing = true;
    private static boolean spawnNewNumber = true;

    public static void main(String[] args){
        prepereBoard();

        addNewNumber(board);

        Scanner scanner = new Scanner(System.in);

        while (playing){
            printBoard(board);

            System.out.print("Wykonaj ruch (w = ^ | s = v | a = < | d = > | r = restart | q = wyjdz): ");

            String move = scanner.next();

            System.out.println();

            makeMove(board, move);

            if (findBiggestValue(board) == 2048){
                System.out.println("Wygrales!\n");
            }

            if (spawnNewNumber){
                if (!addNewNumber(board)){
                    playing = false;
                }
            }

            else{
                spawnNewNumber = true;
            }
        }

        System.out.println("Twoj wynik: " + findBiggestValue(board));

        scanner.close();
    }

    private static void prepereBoard(){
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                board[i][j] = 0;
            }
        }

        addNewNumber(board);
    }

    private static boolean areBoardsEqual(int[][] board1, int[][] board2){
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                if(board1[i][j] != board2[i][j]){
                    return false;
                }
            }
        }

        return true;
    }

    private static void moveToTop(int[][] board){
        int[][] boardAfterMove = new int[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                boardAfterMove[i][j] = board[i][j];
            }
        }

        for (int j = 0; j < BOARD_SIZE; j++){
            for (int i = 1; i < BOARD_SIZE; i++){
                for (int k = i; k > 0; k--){
                    if (boardAfterMove[k][j] != 0){
                        if (boardAfterMove[k - 1][j] == 0){
                            boardAfterMove[k - 1][j] = boardAfterMove[k][j];
                            boardAfterMove[k][j] = 0;
                        }

                        else if (boardAfterMove[k - 1][j] == boardAfterMove[k][j]){
                            boardAfterMove[k - 1][j] += boardAfterMove[k][j];
                            boardAfterMove[k][j] = 0;
                        }
                    }
                }
            }
        }

        if (areBoardsEqual(board, boardAfterMove)){
            spawnNewNumber = false;
        }

        else{
            for (int i = 0; i < BOARD_SIZE; i++){
                for (int j = 0; j < BOARD_SIZE; j++){
                    board[i][j] = boardAfterMove[i][j];
                }
            }
        }
    }

    private static void moveToBottom(int[][] board){
        int[][] boardAfterMove = new int[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                boardAfterMove[i][j] = board[i][j];
            }
        }

        for (int j = 0; j < BOARD_SIZE; j++){
            for (int i = BOARD_SIZE - 1; i >= 0; i--){
                for (int k = i; k < BOARD_SIZE - 1; k++){
                    if (boardAfterMove[k][j] != 0){
                        if (boardAfterMove[k + 1][j] == 0){
                            boardAfterMove[k + 1][j] = boardAfterMove[k][j];
                            boardAfterMove[k][j] = 0;
                        }

                        else if (boardAfterMove[k + 1][j] == boardAfterMove[k][j]){
                            boardAfterMove[k + 1][j] += boardAfterMove[k][j];
                            boardAfterMove[k][j] = 0;
                        }
                    }
                }
            }
        }

        if (areBoardsEqual(board, boardAfterMove)){
            spawnNewNumber = false;
        }

        else{
            for (int i = 0; i < BOARD_SIZE; i++){
                for (int j = 0; j < BOARD_SIZE; j++){
                    board[i][j] = boardAfterMove[i][j];
                }
            }
        }
    }

    private static void moveToLeft(int[][] board){
        int[][] boardAfterMove = new int[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                boardAfterMove[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE - 1; j++){
                for (int k = BOARD_SIZE - 1; k > j; k--){
                    if (boardAfterMove[i][k - 1] == 0){
                        boardAfterMove[i][k - 1] = boardAfterMove[i][k];
                        boardAfterMove[i][k] = 0;
                    }

                    else if (boardAfterMove[i][k - 1] == boardAfterMove[i][k]){
                        boardAfterMove[i][k - 1] += boardAfterMove[i][k];
                        boardAfterMove[i][k] = 0;
                    }
                }
            }
        }

        if (areBoardsEqual(board, boardAfterMove)){
            spawnNewNumber = false;
        }

        else{
            for (int i = 0; i < BOARD_SIZE; i++){
                for (int j = 0; j < BOARD_SIZE; j++){
                    board[i][j] = boardAfterMove[i][j];
                }
            }
        }
    }

    private static void moveToRight(int[][] board){
        int[][] boardAfterMove = new int[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                boardAfterMove[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = BOARD_SIZE - 1; j >= 0; j--){
                for (int k = j; k < BOARD_SIZE - 1; k++){
                    if (boardAfterMove[i][k + 1] == 0){
                        boardAfterMove[i][k + 1] = boardAfterMove[i][k];
                        boardAfterMove[i][k] = 0;
                    }

                    else if (boardAfterMove[i][k + 1] == boardAfterMove[i][k]){
                        boardAfterMove[i][k + 1] += boardAfterMove[i][k];
                        boardAfterMove[i][k] = 0;
                    }
                }
            }
        }

        if (areBoardsEqual(board, boardAfterMove)){
            spawnNewNumber = false;
        }

        else{
            for (int i = 0; i < BOARD_SIZE; i++){
                for (int j = 0; j < BOARD_SIZE; j++){
                    board[i][j] = boardAfterMove[i][j];
                }
            }
        }
    }

    private static void makeMove(int[][] board, String move){
        move = move.toUpperCase(Locale.ROOT);

        switch (move){
            case "W":
                moveToTop(board);
                break;

            case "S":
                moveToBottom(board);
                break;

            case "A":
                moveToLeft(board);
                break;

            case "D":
                moveToRight(board);
                break;

            case "R":
                prepereBoard();
                break;

            case "Q":
                playing = false;
                break;

            default:
                System.out.println("Niepoprawny ruch\n");
        }
    }

    private static boolean addNewNumber(int[][] board){
        List<Integer[]> zerosPositions = findZerosPosition(board);

        if (zerosPositions.size() > 0){
            int choosenZero = new Random().nextInt(zerosPositions.size());

            Integer[] zeroPosition = zerosPositions.get(choosenZero);

            if ((new Random().nextInt(10) + 1) % 10 == 0){
                board[zeroPosition[0]][zeroPosition[1]] = 4;
            }

            else{
                board[zeroPosition[0]][zeroPosition[1]] = 2;
            }

            return true;
        }

        else{
            System.out.println("Przegrales");

            return false;
        }
    }

    private static List<Integer[]> findZerosPosition(int[][] board){
        List<Integer[]> positions = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                if (board[i][j] == 0){
                    positions.add(new Integer[]{i, j});
                }
            }
        }

        return positions;
    }

    private static String printNumber(int number, int biggestValue){
        int lenght = String.valueOf(biggestValue).length();

        String stringNum;

        if (number == 0){
            stringNum = " ";
        }

        else{
            stringNum = String.valueOf(number);
        }

        String result = " ";

        for (int i = 0; i < stringNum.length(); i++){
            result = result.concat(stringNum.charAt(i) + " ");
        }

        for (int i = 0; i < lenght - stringNum.length(); i++){
            result = " ".concat(result).concat(" ");
        }

        return result;
    }

    private static void printBoard(int[][] board){
        int biggestNum = findBiggestValue(board);
        String separator = "";

        for (int j = 0; j < printNumber(0, biggestNum).length(); j++){
            separator = separator.concat("-");
        }

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                System.out.print(printNumber(board[i][j], biggestNum));

                if (j != 3){
                    System.out.print("|");
                }
            }

            System.out.println();

            if (i != 3){
                System.out.println(separator + "+" + separator + "+" + separator + "+" + separator);
            }
        }

        System.out.println();
    }

    private static int findBiggestValue(int[][] board){
        int biggest = 0;

        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                if (board[i][j] > biggest){
                    biggest = board[i][j];
                }
            }
        }

        return biggest;
    }
}
