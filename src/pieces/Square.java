package pieces;

import chessApp.Color;

public abstract class Square {
	protected String symbol;
	protected Color color;
	protected PieceType type;
	
	public Square(PieceType type){
		this.type = type;
	}
	
	public String getSymbol(){
		return symbol;
	}
	public Color getColor(){
		return color;
	}
	public PieceType getType(){
		return type;
	}
	
	public abstract boolean checkMove(int[] sourceCoordinates, int[] destinationCoordinates, Color playerColor, boolean isKing);
}
