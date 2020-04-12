package pieces;

import chessApp.Color;
import chessApp.PieceType;

public abstract class Piece extends Square{
	
	public Piece(Color color, PieceType type) {
		super(type);
		this.color = color;
	} 
}
