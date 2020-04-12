package pieces;

import chessApp.Color;
import chessApp.PieceType;

public class BlankSpace extends Square {
	
	public BlankSpace() {
		super(PieceType.BLANK);
		symbol = "   ";
		color = null;
		
	}

	public boolean checkMove(int[] sourceCoordinates, int[] destinationCoordinates, Color playerColor, boolean isKing) {
		return false;
	}

}
