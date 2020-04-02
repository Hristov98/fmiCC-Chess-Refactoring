package chess;

import java.util.Scanner;

import pieces.*;

//board is the main class for the game
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

    private static String checkForCheckOrMate(String plyColor) { //check for win or check
        for (int kingY = 0; kingY < 8; kingY++) {
            for (int kingX = 0; kingX < 8; kingX++) {
                Square kingSquare = board[kingY][kingX];

                String kingColor;
                if (plyColor == "white") {
                    kingColor = "black";
                } else { //black
                    kingColor = "white";
                }

                if ((kingSquare.getType() == "king") && (kingSquare.getColor() == kingColor)) {

                    for (int threatY = 0; threatY < 8; threatY++) {
                        for (int threatX = 0; threatX < 8; threatX++) {
                            Square threatSquare = board[threatY][threatX];

                            if ((threatSquare.getType() != "blank") && (threatSquare.getColor() == plyColor)) {
                                int[] moveFrom = {threatX, threatY};
                                int[] moveTo = {kingX, kingY};

                                if (threatSquare.checkMove(moveFrom, moveTo, plyColor, true)) {
                                    return "check";
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private static void movePiece(int[] sourceCoordinates, int[] destinationCoordinates) {
        board[destinationCoordinates[1]][destinationCoordinates[0]] = board[sourceCoordinates[1]][sourceCoordinates[0]];
        board[sourceCoordinates[1]][sourceCoordinates[0]] = new BlankSpace();
    }

    private static void drawChessBoard() {
        System.out.print("\n   ");

        for (char i : COLUMNS) { //printing letters across the top
            System.out.print("  " + i + "  ");
        }
        System.out.print("\n   ");

        for (int i = 0; i < 8; i++) {
            System.out.print(" --- ");
        }

        System.out.print("\n");
        for (int i = 0; i < 8; i++) { //looping through the board and printing symbols
            System.out.print(" " + (8 - i) + " "); //print number on left side

            for (Square j : board[i]) {
                System.out.print("|" + j.getSymbol() + "|");
            }
            System.out.print(" " + (8 - i) + " "); //number on right side

            System.out.print("\n   ");//to get next line

            for (int j = 0; j < 8; j++) {
                System.out.print(" --- ");
            }
            System.out.print("\n");
        }
        System.out.print("   ");
        for (char i : COLUMNS) { //printing letters across the bottom
            System.out.print("  " + i + "  ");
        }
        System.out.print("\n\n");
    }


    //prevName value can be set to null if this is the first call
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

    private static void notifyPlayerOnInvalidName()
	{
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

                        if (runNum == 1) {
                            if (checkForCheckOrMate("white") == "check") {
                                System.out.println("Check!");
                            }
                        } else {
                            if (checkForCheckOrMate("black") == "check") {
                                System.out.println("Check!");
                            }
                        }
                        break;
                    }
                    System.out.println("Invalid move. Try again."); //not printed if break is called
                }
            }
        }
    }
}
