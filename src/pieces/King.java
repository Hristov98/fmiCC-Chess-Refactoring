package pieces;

import chessApp.Board;
import chessApp.Color;

public class King extends Piece{

	public King(Color colorIn) {
		super(colorIn, PieceType.KING);
		
		if(this.color == Color.WHITE){
			symbol = "wKi";
		}
		else{
			symbol = "bKi";
		}
	}

	public boolean checkMove(int[] sourceCoordinates, int[] destinationCoordinates, Color playerColor, boolean isKing) {
		
		int moveFromX = sourceCoordinates[0];
		int moveFromY = sourceCoordinates[1];
		int moveToX = destinationCoordinates[0];
		int moveToY = destinationCoordinates[1];
		
		Square toSquare = Board.board[moveToY][moveToX];
		
		for (int moveAwayX = -1; moveAwayX <= 1; moveAwayX++){
			for (int moveAwayY = -1; moveAwayY <= 1; moveAwayY++){
				if(moveToX == moveFromX + moveAwayX && moveToY == moveFromY + moveAwayY){
					if((toSquare.getType() != PieceType.BLANK) && (toSquare.getColor() != playerColor)){
						return true;
					}
					else if(toSquare.getType() == PieceType.BLANK){
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
