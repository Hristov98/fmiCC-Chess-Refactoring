package pieces;

import chessApp.Board;
import chessApp.Color;

public class Knight extends Piece{

	public Knight(Color colorIn) {
		super(colorIn, PieceType.KNIGHT);
		
		if(color == Color.WHITE){
			symbol = "wKn";
		}
		else{
			symbol = "bKn";
		}
	}

	public boolean checkMove(int[] sourceCoordinates, int[] destinationCoordinates, Color playerColor, boolean isKing) {
		
		int moveFromX = sourceCoordinates[0];
		int moveFromY = sourceCoordinates[1];
		int moveToX = destinationCoordinates[0];
		int moveToY = destinationCoordinates[1];
		
		Square toSquare = Board.board[moveToY][moveToX];
		
		if(!isKing){
			if(toSquare.getType() == PieceType.KING){
				return false; //can't move to take a king
			}
		}
		
		boolean locationPass = false; //default false
		
		for(int displaceX = -2; displaceX <= 2; displaceX++){
		
			if(displaceX != 0){
				if(moveToX == moveFromX + displaceX){
					
					if(Math.abs(displaceX) == 1){ //if the x displace was 1 the y displace must be 2
						for(int displaceY = -2; displaceY <= 2; displaceY += 4){
							if(moveToY == moveFromY + displaceY){
								locationPass = true;
							}
						}
					}
					else{ //x displace is 2 so y displace is 1
						for(int displaceY = -1; displaceY <= 1; displaceY += 2){
							if(moveToY == moveFromY + displaceY){
								locationPass = true;
							}
						}
					}
				}
			}
		}
		if(locationPass){ //if the location was not within a knights move rules
			
			if((toSquare.getType() == PieceType.BLANK) || (toSquare.getColor() != playerColor)){
				return true;
			}
		}
		
		return false;
	}
}
