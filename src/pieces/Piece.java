package pieces;

import chessApp.Color;

public abstract class Piece extends Square{
	
	public Piece(Color colorIn, PieceType type) {
		super(type);
		color = colorIn;
	} 
}
