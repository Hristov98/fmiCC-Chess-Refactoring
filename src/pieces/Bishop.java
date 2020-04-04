package pieces;

import chessApp.Board;
import chessApp.Color;

public class Bishop extends Piece{

	public Bishop(Color colorIn) {
		super(colorIn, PieceType.BISHOP);
		
		if(color == Color.WHITE){
			symbol = "wBi";
		}
		else{
			symbol = "bBi";
		}
	}

	public boolean checkMove(int[] sourceCoordinates, int[] destinationCoordinates, Color playerColor, boolean isKing) {
		
		int moveFromX = sourceCoordinates[0];
		int moveFromY = sourceCoordinates[1];
		int moveToX = destinationCoordinates[0];
		int moveToY = destinationCoordinates[1];
		
		Square toSquare = Board.board[moveToY][moveToX];
		
		int moveDistance = Math.abs(moveToX - moveFromX);
		
		if(!isKing){
			if(toSquare.getType() == PieceType.KING){
				return false; //can't move to take a king
			}
		}
		
		String direction; //direction the bishop will take
		
		if(moveToX > moveFromX){
			if(moveToY < moveFromY){
				direction = "topRite";
			}
			else{
				direction = "botRite";
			}
		}
		else{
			if(moveToY < moveFromY){
				direction = "topLeft";
			}
			else{
				direction = "botLeft";
			}
		}
		
		
		Square testSquare; //square that will be tested for pieces
		
		//for loop iterating through the diagonal path of the bishop
		for(int diagMoveAway = 1; diagMoveAway <= moveDistance; diagMoveAway++){
			
			if(direction == "topRite"){
				testSquare = Board.board[moveFromY - diagMoveAway][moveFromX + diagMoveAway];
			}
			else if(direction == "botRite"){
				testSquare = Board.board[moveFromY + diagMoveAway][moveFromX + diagMoveAway];
			}
			else if(direction == "topLeft"){
				testSquare = Board.board[moveFromY - diagMoveAway][moveFromX - diagMoveAway];
			}
			else{ //botLeft
				testSquare = Board.board[moveFromY + diagMoveAway][moveFromX - diagMoveAway];
			}
			
			if((testSquare.getType() != PieceType.BLANK) && (diagMoveAway != moveDistance)){
				return false;
			}
			else if((diagMoveAway == moveDistance) && ((testSquare.getColor() != playerColor) || (testSquare.getType() == PieceType.BLANK))){
				return true;
			}
		}
		return false; //default return value
	}
}
