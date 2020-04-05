package pieces;

import chessApp.Color;

public abstract class Piece extends Square{
	
	public Piece(Color color, PieceType type) {
		super(type);
		this.color = color;
	} 
}
