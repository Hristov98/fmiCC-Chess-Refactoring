package pieces;

import chessApp.Board;
import chessApp.Color;
import chessApp.PieceType;

public class Bishop extends Piece{

	public Bishop(Color color) {
		super(color, PieceType.BISHOP);
		
		if(color == Color.WHITE){
			symbol = "wBi";
		}
		else{
			symbol = "bBi";
		}
	}

	public boolean checkMove(int[] sourceCoordinates, int[] destinationCoordinates, Color playerColor, boolean isKing) {
		
		int sourceColumn = sourceCoordinates[0];
		int sourceRow = sourceCoordinates[1];
		int destinationColumn = destinationCoordinates[0];
		int destinationRow = destinationCoordinates[1];
		
		Square toSquare = Board.board[destinationRow][destinationColumn];
		
		int moveDistance = Math.abs(destinationColumn - sourceColumn);
		
		if(!isKing){
			if(toSquare.getType() == PieceType.KING){
				return false;
			}
		}
		
		String direction;
		
		if(destinationColumn > sourceColumn){
			if(destinationRow < sourceRow){
				direction = "topRight";
			}
			else{
				direction = "botRight";
			}
		}
		else{
			if(destinationRow < sourceRow){
				direction = "topLeft";
			}
			else{
				direction = "botLeft";
			}
		}
		
		Square testSquare;

		for(int diagonalMoveAway = 1; diagonalMoveAway <= moveDistance; diagonalMoveAway++){

			switch (direction) {
				case "topRight":
					testSquare = Board.board[sourceRow - diagonalMoveAway][sourceColumn + diagonalMoveAway];
					break;
				case "botRight":
					testSquare = Board.board[sourceRow + diagonalMoveAway][sourceColumn + diagonalMoveAway];
					break;
				case "topLeft":
					testSquare = Board.board[sourceRow - diagonalMoveAway][sourceColumn - diagonalMoveAway];
					break;
				default:
					testSquare = Board.board[sourceRow + diagonalMoveAway][sourceColumn - diagonalMoveAway];
					break;
			}
			
			if((testSquare.getType() != PieceType.BLANK) && (diagonalMoveAway != moveDistance)){
				return false;
			}
			else if((diagonalMoveAway == moveDistance) && ((testSquare.getColor() != playerColor) || (testSquare.getType() == PieceType.BLANK))){
				return true;
			}
		}
		return false;
	}
}
