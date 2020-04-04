package pieces;

public enum PieceType {
    BLANK(0),
    PAWN(1),
    ROOK(2),
    BISHOP(3),
    KNIGHT(4),
    QUEEN(5),
    KING(6);

    private final int type;

    PieceType(int type) {
        this.type = type;
    }
}
