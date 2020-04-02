package chess;

import java.util.Scanner;

import pieces.*;

public abstract class Board {

    static final char[] COLUMNS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    static final int[] ROWS = {1, 2, 3, 4, 5, 6, 7, 8};
    private static final int BOARD_BOUNDARY = 8;
    public static Square[][] board = new Square[8][8];

    private static final Scanner scanner = new Scanner(System.in);

    private static void setup() {
        initialiseBlackPieces();
        initialiseBlankSpace();
        initialiseWhitePieces();
    }

    private static void initialiseBlackPieces() {
        final int PAWN_ROW = 1;
        final int SPECIAL_ROW = 0;

        for (int i = 0; i < BOARD_BOUNDARY; i++) {
            board[PAWN_ROW][i] = new Pawn("black");
        }

        board[SPECIAL_ROW][0] = new Rook("black");
        board[SPECIAL_ROW][1] = new Knight("black");
        board[SPECIAL_ROW][2] = new Bishop("black");
        board[SPECIAL_ROW][3] = new Queen("black");
        board[SPECIAL_ROW][4] = new King("black");
        board[SPECIAL_ROW][5] = new Bishop("black");
        board[SPECIAL_ROW][6] = new Knight("black");
        board[SPECIAL_ROW][7] = new Rook("black");
    }

    private static void initialiseBlankSpace() {
        final int BLANK_LOWER_BOUNDARY = 2;
        final int BLANK_UPPER_BOUNDARY = 6;

        for (int i = BLANK_LOWER_BOUNDARY; i < BLANK_UPPER_BOUNDARY; i++) {
            for (int j = 0; j < BOARD_BOUNDARY; j++) {
                board[i][j] = new BlankSpace();
            }
        }
    }

    private static void initialiseWhitePieces() {
        final int PAWN_ROW = 6;
        final int SPECIAL_ROW = 7;

        for (int i = 0; i < BOARD_BOUNDARY; i++) {
            board[PAWN_ROW][i] = new Pawn("white");
        }

        board[SPECIAL_ROW][0] = new Rook("white");
        board[SPECIAL_ROW][1] = new Knight("white");
        board[SPECIAL_ROW][2] = new Bishop("white");
        board[SPECIAL_ROW][3] = new Queen("white");
        board[SPECIAL_ROW][4] = new King("white");
        board[SPECIAL_ROW][5] = new Bishop("white");
        board[SPECIAL_ROW][6] = new Knight("white");
        board[SPECIAL_ROW][7] = new Rook("white");
    }

    private static void movePiece(int[] sourceCoordinates, int[] destinationCoordinates) {
        board[destinationCoordinates[1]][destinationCoordinates[0]] = board[sourceCoordinates[1]][sourceCoordinates[0]];
        board[sourceCoordinates[1]][sourceCoordinates[0]] = new BlankSpace();
    }

    private static void drawChessBoard() {
        printTab();
        printLetters();
        printDottedLines();
        printNewLine();
        printRows();
        printTab();
        printLetters();
        printNewLine();
    }

    private static void printTab() {
        System.out.print("   ");
    }

    private static void printLetters() {
        for (char i : COLUMNS) {
            System.out.print("  " + i + "  ");
        }
    }

    private static void printNewLine() {
        System.out.print("\n");
    }

    private static void printDottedLines() {
        System.out.print("\n   ");
        for (int i = 0; i < 8; i++) {
            System.out.print(" --- ");
        }
    }

    private static void printRows() {
        for (int i = 0; i < BOARD_BOUNDARY; i++) {
            printRow(i);
            printChessPieceOnRow(i);
            printRow(i);
            printDottedLines();
            printNewLine();
        }
    }

    private static void printRow(int row) {
        System.out.print(" " + (BOARD_BOUNDARY - row) + " ");
    }

    private static void printChessPieceOnRow(int row) {
        for (Square j : board[row]) {
            System.out.print("|" + j.getSymbol() + "|");
        }
    }

    private static String inputPlayerName(int playerNumber, String previousName) {
        while (true) {
            String name = getPlayerInput(playerNumber);

            if (isValid(name, previousName)) {
                return name;
            } else {
                notifyPlayerOnInvalidName();
            }

        }
    }

    private static String getPlayerInput(int playerNumber) {
        System.out.print("Player " + playerNumber + " please enter your name.\n>> ");
        return scanner.nextLine().trim();
    }

    private static boolean isValid(String name, String previousName) {
        return !name.isEmpty() && !containsWhiteSpace(name) && !areDuplicates(name, previousName);
    }

    private static boolean containsWhiteSpace(String name) {
        return name.contains(" ") || name.contains("\t");
    }

    private static boolean areDuplicates(String newName, String previousName) {
        return newName.equals(previousName);
    }

    private static void notifyPlayerOnInvalidName() {
        System.out.println("Invalid name. Try again.");
    }


    public static void main(String[] args) {
        System.out.println("=====> CHESS <====="); //title

        String ply1Name = inputPlayerName(1, null);
        String ply2Name = inputPlayerName(2, ply1Name);

        Player whitePly = new Player(ply1Name, "white");
        Player blackPly = new Player(ply2Name, "black");

        setup(); //get board set

        //start main loop
        while (true) {

            for (int runNum = 1; runNum <= 2; runNum++) { //run for each player
                drawChessBoard(); //show board

                int move[][] = new int[2][2];

                while (true) {

                    if (runNum == 1) { //first run
                        move = whitePly.getPlayerTurn();
                    } else { //second run
                        move = blackPly.getPlayerTurn();
                    }

                    if (move[0][0] == -1) { //restarting loop if input is wrong
                        System.out.println("Invalid location. Try again.");
                        continue;
                    }

                    int[] moveFrom = move[0];
                    int[] moveTo = move[1];
                    Square fromSquare = board[moveFrom[1]][moveFrom[0]];

                    boolean checkValue;
                    if (runNum == 1) {
                        checkValue = fromSquare.checkMove(moveFrom, moveTo, "white", false); //checking for pawn move validity
                    } else {
                        checkValue = fromSquare.checkMove(moveFrom, moveTo, "black", false);
                    }
                    if (checkValue) {
                        movePiece(moveFrom, moveTo);

                        break;
                    }
                    System.out.println("Invalid move. Try again."); //not printed if break is called
                }
            }
        }
    }
}
