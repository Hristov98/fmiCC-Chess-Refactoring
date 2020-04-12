package refactoredPieces;

import chessApp.Color;
import chessApp.PieceType;

public class Bishop extends Piece{
    Bishop(String symbol, Color color, PieceType type, int[] position) {
        super(symbol, color, type, position);
    }

    @Override
    public boolean isMoveValid(int[] destinationCoordinates) {
        return false;
    }
}
