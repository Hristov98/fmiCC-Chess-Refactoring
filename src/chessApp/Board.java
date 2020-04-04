package chessApp;

import java.util.Scanner;

import pieces.*;

public class Board {
    public static final char[] COLUMNS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    public static final int[] ROWS = {1, 2, 3, 4, 5, 6, 7, 8};
    public static Square[][] board = new Square[8][8];
    private final int BOARD_BOUNDARY = 8;
    private final Scanner scanner = new Scanner(System.in);

    public Square[][] getBoard() {
        return board;
    }

    public void setupBoard() {
        initialiseBlackPieces();
        initialiseBlankSpace();
        initialiseWhitePieces();
    }

    private void initialiseBlackPieces() {
        final int PAWN_ROW = 1;
        final int SPECIAL_ROW = 0;

        for (int i = 0; i < BOARD_BOUNDARY; i++) {
            board[PAWN_ROW][i] = new Pawn(Color.BLACK);
        }

        board[SPECIAL_ROW][0] = new Rook(Color.BLACK);
        board[SPECIAL_ROW][1] = new Knight(Color.BLACK);
        board[SPECIAL_ROW][2] = new Bishop(Color.BLACK);
        board[SPECIAL_ROW][3] = new Queen(Color.BLACK);
        board[SPECIAL_ROW][4] = new King(Color.BLACK);
        board[SPECIAL_ROW][5] = new Bishop(Color.BLACK);
        board[SPECIAL_ROW][6] = new Knight(Color.BLACK);
        board[SPECIAL_ROW][7] = new Rook(Color.BLACK);
    }

    private void initialiseBlankSpace() {
        final int BLANK_LOWER_BOUNDARY = 2;
        final int BLANK_UPPER_BOUNDARY = 6;

        for (int i = BLANK_LOWER_BOUNDARY; i < BLANK_UPPER_BOUNDARY; i++) {
            for (int j = 0; j < BOARD_BOUNDARY; j++) {
                board[i][j] = new BlankSpace();
            }
        }
    }

    private void initialiseWhitePieces() {
        final int PAWN_ROW = 6;
        final int SPECIAL_ROW = 7;

        for (int i = 0; i < BOARD_BOUNDARY; i++) {
            board[PAWN_ROW][i] = new Pawn(Color.WHITE);
        }

        board[SPECIAL_ROW][0] = new Rook(Color.WHITE);
        board[SPECIAL_ROW][1] = new Knight(Color.WHITE);
        board[SPECIAL_ROW][2] = new Bishop(Color.WHITE);
        board[SPECIAL_ROW][3] = new Queen(Color.WHITE);
        board[SPECIAL_ROW][4] = new King(Color.WHITE);
        board[SPECIAL_ROW][5] = new Bishop(Color.WHITE);
        board[SPECIAL_ROW][6] = new Knight(Color.WHITE);
        board[SPECIAL_ROW][7] = new Rook(Color.WHITE);
    }

    public void movePiece(int[] sourceCoordinates, int[] destinationCoordinates) {
        board[destinationCoordinates[1]][destinationCoordinates[0]] = board[sourceCoordinates[1]][sourceCoordinates[0]];
        board[sourceCoordinates[1]][sourceCoordinates[0]] = new BlankSpace();
    }

    public void drawChessBoard() {
        printTab();
        printLetters();
        printDottedLines();
        printNewLine();
        printRows();
        printTab();
        printLetters();
        printNewLine();
    }

    private void printTab() {
        System.out.print("   ");
    }

    private void printLetters() {
        for (char i : COLUMNS) {
            System.out.print("  " + i + "  ");
        }
    }

    private void printNewLine() {
        System.out.print("\n");
    }

    private void printDottedLines() {
        System.out.print("\n   ");
        for (int i = 0; i < 8; i++) {
            System.out.print(" --- ");
        }
    }

    private void printRows() {
        for (int i = 0; i < BOARD_BOUNDARY; i++) {
            printRow(i);
            printChessPieceOnRow(i);
            printRow(i);
            printDottedLines();
            printNewLine();
        }
    }

    private void printRow(int row) {
        System.out.print(" " + (BOARD_BOUNDARY - row) + " ");
    }

    private void printChessPieceOnRow(int row) {
        for (Square j : board[row]) {
            System.out.print("|" + j.getSymbol() + "|");
        }
    }

    public String inputPlayerName(int playerNumber, String previousName) {
        while (true) {
            String name = getPlayerInput(playerNumber);

            if (isValid(name, previousName)) {
                return name;
            } else {
                notifyPlayerOnInvalidName();
            }

        }
    }

    private String getPlayerInput(int playerNumber) {
        System.out.print("Player " + playerNumber + " please enter your name.\n>> ");
        return scanner.nextLine().trim();
    }

    private boolean isValid(String name, String previousName) {
        return !name.isEmpty() && !containsWhiteSpace(name) && !areDuplicates(name, previousName);
    }

    private boolean containsWhiteSpace(String name) {
        return name.contains(" ") || name.contains("\t");
    }

    private boolean areDuplicates(String newName, String previousName) {
        return newName.equals(previousName);
    }

    private void notifyPlayerOnInvalidName() {
        System.out.println("Invalid name. Try again.");
    }

}
