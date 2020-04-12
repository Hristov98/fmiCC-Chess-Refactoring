package refactoredPieces;

import chessApp.Color;
import chessApp.PieceType;

public abstract class Piece {
    private final String symbol;
    private final Color color;
    private final PieceType type;
    private int[] position = new int[2];

    Piece(String symbol, Color color, PieceType type, int[] position) {
        this.symbol = symbol;
        this.color = color;
        this.type = type;
        this.position[0] = position[0];
        this.position[1] = position[1];
    }

    public String getSymbol() {
        return symbol;
    }

    public Color getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public int getPositionRow() {
        return position[1];
    }

    public int getPositionColumn() {
        return position[0];
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public abstract boolean isMoveValid(int[] destinationCoordinates);
}
