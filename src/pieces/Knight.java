package pieces;

import chessApp.Board;
import chessApp.Color;
import chessApp.PieceType;

public class Knight extends Piece{

	public Knight(Color color) {
		super(color, PieceType.KNIGHT);
		
		if(color == Color.WHITE){
			symbol = "wKn";
		}
		else{
			symbol = "bKn";
		}
	}

	public boolean checkMove(int[] sourceCoordinates, int[] destinationCoordinates, Color playerColor, boolean isKing) {
		
		int sourceColumn = sourceCoordinates[0];
		int sourceRow = sourceCoordinates[1];
		int destinationColumn = destinationCoordinates[0];
		int destinationRow = destinationCoordinates[1];
		
		Square destinationSquare = Board.board[destinationRow][destinationColumn];
		
		if(!isKing){
			if(destinationSquare.getType() == PieceType.KING){
				return false;
			}
		}
		
		boolean locationPass = false;
		
		for(int columnDisplacement = -2; columnDisplacement <= 2; columnDisplacement++){
		
			if(columnDisplacement != 0){
				if(destinationColumn == sourceColumn + columnDisplacement){
					if(Math.abs(columnDisplacement) == 1){
						for(int rowDisplacement = -2; rowDisplacement <= 2; rowDisplacement += 4){
							if (destinationRow == sourceRow + rowDisplacement) {
								locationPass = true;
								break;
							}
						}
					}
					else{
						for(int displaceY = -1; displaceY <= 1; displaceY += 2){
							if (destinationRow == sourceRow + displaceY) {
								locationPass = true;
								break;
							}
						}
					}
				}
			}
		}
		if(locationPass){
			return (destinationSquare.getType() == PieceType.BLANK) || (destinationSquare.getColor() != playerColor);
		}
		
		return false;
	}
}
