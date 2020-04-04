package pieces;

import chessApp.Color;

public class BlankSpace extends Square {
	
	public BlankSpace() {
		super(PieceType.BLANK);
		symbol = "   ";
		color = null; //a blank square has no color
		
	}

	public void move(int[] moveToLoc) {
	}

	public boolean checkMove(int[] sourceCoordinates, int[] destinationCoordinates, Color playerColor, boolean isKing) { //unused
		return false;
	}

}
