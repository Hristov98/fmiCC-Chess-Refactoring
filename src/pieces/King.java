package pieces;

import chessApp.Board;
import chessApp.Color;
import chessApp.PieceType;

public class King extends Piece{

	public King(Color color) {
		super(color, PieceType.KING);
		
		if(this.color == Color.WHITE){
			symbol = "wKi";
		}
		else{
			symbol = "bKi";
		}
	}

	public boolean checkMove(int[] sourceCoordinates, int[] destinationCoordinates, Color playerColor, boolean isKing) {
		
		int sourceColumn = sourceCoordinates[0];
		int sourceRow = sourceCoordinates[1];
		int destinationColumn = destinationCoordinates[0];
		int destinationRow = destinationCoordinates[1];
		
		Square destinationSquare = Board.board[destinationRow][destinationColumn];
		
		for (int moveAwayColumn = -1; moveAwayColumn <= 1; moveAwayColumn++){
			for (int moveAwayRow = -1; moveAwayRow <= 1; moveAwayRow++){
				if(destinationColumn == sourceColumn + moveAwayColumn && destinationRow == sourceRow + moveAwayRow){
					if((destinationSquare.getType() != PieceType.BLANK) && (destinationSquare.getColor() != playerColor)){
						return true;
					}
					else if(destinationSquare.getType() == PieceType.BLANK){
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
