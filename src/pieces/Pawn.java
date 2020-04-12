package pieces;

import chessApp.Board;
import chessApp.Color;
import chessApp.PieceType;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color, PieceType.PAWN);

        if (color == Color.WHITE) {
            symbol = "wPa";
        } else {
            symbol = "bPa";
        }
    }

    public boolean checkMove(int[] sourceCoordinates, int[] destinationCoordinates, Color playerColor, boolean isKing) {

        int sourceColumn = sourceCoordinates[0];
        int sourceRow = sourceCoordinates[1];
        int destinationColumn = destinationCoordinates[0];
        int destinationRow = destinationCoordinates[1];

        int moveTwoSquares;
        int moveOneSquare;
        int pawnRowOnPlayerSide;

        Square toSquare = Board.board[destinationRow][destinationColumn];

        if (!isKing) {
            if (toSquare.getType() == PieceType.KING) {
                return false;
            }
        }

        if (playerColor == Color.WHITE) {
            moveTwoSquares = -2;
            moveOneSquare = -1;
            pawnRowOnPlayerSide = 6;
        } else {
            moveTwoSquares = 2;
            moveOneSquare = 1;
            pawnRowOnPlayerSide = 1;
        }

        if (destinationRow == sourceRow + moveOneSquare) {
            if ((destinationColumn == sourceColumn - 1) || (destinationColumn == sourceColumn + 1)) {
                return (toSquare.getType() != PieceType.BLANK) && (toSquare.getColor() != playerColor);
            } else {
                return (destinationColumn == sourceColumn) && (toSquare.getType() == PieceType.BLANK);
            }
        }
        else if ((destinationRow == sourceRow + moveTwoSquares) && (destinationColumn == sourceColumn) && (toSquare.getType() == PieceType.BLANK)) {
                return sourceRow == pawnRowOnPlayerSide;
        }

        return false;
    }
}
