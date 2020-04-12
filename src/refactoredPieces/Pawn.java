package refactoredPieces;

import chessApp.Color;
import chessApp.PieceType;

public class Pawn extends Piece {
    Pawn(String symbol, Color color, PieceType type, int[] position) {
        super(symbol, color, type, position);
    }

    @Override
    public boolean isMoveValid(int[] destinationCoordinates) {
        if (getColor() == Color.WHITE) {
            return isWhitePawnMoveValid(destinationCoordinates);
        } else return isBlackPawnMoveValid(destinationCoordinates);
    }

    private boolean isWhitePawnMoveValid(int[] destinationCoordinates) {
        final int MOVE_TWO_SQUARES = -2;
        final int MOVE_ONE_SQUARE = -1;
        final int destinationColumn = destinationCoordinates[0];
        final int destinationRow = destinationCoordinates[1];

        if (destinationRow == getPositionRow() + MOVE_ONE_SQUARE) {
            return isPawnMovingOneSquareValid(destinationColumn);
        } else if (destinationRow == getPositionRow() + MOVE_TWO_SQUARES) {
            return isWhitePawnMovingTwoSquaresValid(destinationColumn);
        } else return false;
    }

    private boolean isPawnMovingOneSquareValid(int destinationColumn) {
        if (isPawnMovingDiagonally(destinationColumn)) {
            return true;
        } else {
            return isPawnMovingForward(destinationColumn);
        }
    }

    private boolean isPawnMovingDiagonally(int destinationColumn) {
        return destinationColumn == getPositionColumn() - 1 || destinationColumn == getPositionColumn() + 1;
    }

    private boolean isPawnMovingForward(int destinationColumn) {
        return destinationColumn == getPositionColumn();
    }

    private boolean isWhitePawnMovingTwoSquaresValid(int destinationColumn) {
        final int PLAYER_ROW = 6;

        return (getPositionColumn() == destinationColumn) && getPositionRow() == PLAYER_ROW;
    }

    private boolean isBlackPawnMoveValid(int[] destinationCoordinates) {
        final int MOVE_TWO_SQUARES = 2;
        final int MOVE_ONE_SQUARE = 1;
        final int destinationColumn = destinationCoordinates[0];
        final int destinationRow = destinationCoordinates[1];

        if (destinationRow == getPositionRow() + MOVE_ONE_SQUARE) {
            return isPawnMovingOneSquareValid(destinationColumn);
        } else if (destinationRow == getPositionRow() + MOVE_TWO_SQUARES) {
            return isBlackPawnMovingTwoSquaresValid(destinationColumn);
        } else return false;
    }

    private boolean isBlackPawnMovingTwoSquaresValid(int destinationColumn) {
        final int PLAYER_ROW = 1;

        return (getPositionColumn() == destinationColumn) && getPositionRow() == PLAYER_ROW;
    }

}
