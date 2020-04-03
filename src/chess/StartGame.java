package chess;

import pieces.Square;

public class StartGame {
    private static final Board chessBoard = new Board();
    private static String whitePlayerName;
    private static String blackPlayerName;
    private static Player whitePlayer;
    private static Player blackPlayer;
    private static int[][] whitePlayerMove;
    private static int[][] blackPlayerMove;

    public static void main(String[] args) {
        printHeader();
        initialisePlayers();
        chessBoard.setupBoard();
        startGameLoop();
    }

    private static void printHeader() {
        System.out.println("=====> CHESS <=====");
    }

    private static void initialisePlayers() {
        inputPlayerNames();
        assignPlayers();
    }

    private static void inputPlayerNames() {
        whitePlayerName = chessBoard.inputPlayerName(1, null);
        blackPlayerName = chessBoard.inputPlayerName(2, whitePlayerName);
    }

    private static void assignPlayers() {
        whitePlayer = new Player(whitePlayerName, "white");
        blackPlayer = new Player(blackPlayerName, "black");
    }

    private static void startGameLoop() {
        while (true) {
            executePlayerTurns();
        }
    }

    private static void executePlayerTurns() {
        executeWhitePlayerTurn();
        executeBlackPlayerTurn();
    }

    private static void executeWhitePlayerTurn() {
        while (true) {
            chessBoard.drawChessBoard();
            getValidWhitePlayerTurn();

            if (pieceMoveForWhitePlayerIsValid()) {
                chessBoard.movePiece(whitePlayerMove[0], whitePlayerMove[1]);
                break;
            }
        }
    }

    private static void getValidWhitePlayerTurn() {
        do {
            whitePlayerMove = whitePlayer.getPlayerTurn();
        } while (isNotValid(whitePlayerMove));
    }

    private static boolean pieceMoveForWhitePlayerIsValid() {
        int[] sourceCoordinates = whitePlayerMove[0];
        int[] destinationCoordinates = whitePlayerMove[1];
        Square sourcePosition = chessBoard.getBoard()[sourceCoordinates[1]][sourceCoordinates[0]];

        return sourcePosition.checkMove(sourceCoordinates, destinationCoordinates, "white", false);
    }

    private static boolean isNotValid(int[][] playerInput) {
        if (playerInput[0][0] == -1) {
            System.out.println("Invalid location. Try again.");
            return true;
        } else return false;
    }

    private static void executeBlackPlayerTurn() {
        while (true) {
            chessBoard.drawChessBoard();
            getValidBlackPlayerTurn();

            if (pieceMoveForBlackPlayerIsValid()) {
                chessBoard.movePiece(blackPlayerMove[0], blackPlayerMove[1]);
                break;
            }
        }
    }

    private static void getValidBlackPlayerTurn() {
        do {
            blackPlayerMove = blackPlayer.getPlayerTurn();
        } while (isNotValid(blackPlayerMove));
    }

    private static boolean pieceMoveForBlackPlayerIsValid() {
        int[] sourceCoordinates = blackPlayerMove[0];
        int[] destinationCoordinates = blackPlayerMove[1];
        Square sourcePosition = chessBoard.getBoard()[sourceCoordinates[1]][sourceCoordinates[0]];

        return sourcePosition.checkMove(sourceCoordinates, destinationCoordinates, "black", false);
    }
}
