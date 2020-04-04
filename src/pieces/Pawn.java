package pieces;

import chessApp.Board;
import chessApp.Color;

public class Pawn extends Piece{

	public Pawn(Color colorIn) {
		super(colorIn, PieceType.PAWN);
		
		if(color == Color.WHITE){
			symbol = "wPa";
		}
		else{
			symbol = "bPa";
		}
	}
	
	public boolean checkMove(int[] sourceCoordinates, int[] destinationCoordinates, Color playerColor, boolean isKing) {
		
		int moveFromX = sourceCoordinates[0];
		int moveFromY = sourceCoordinates[1];
		int moveToX = destinationCoordinates[0];
		int moveToY = destinationCoordinates[1];
		
		int moveForwardTwo; //vars that increment the moves | change based on direction
		int moveForwardOne;
		int pawnRowOnPlySide; //row that the certain players pawns are on
		
		Square toSquare = Board.board[moveToY][moveToX];
		
		if(!isKing){
			if(toSquare.getType() == PieceType.KING){
				return false; //can't move to take a king
			}
		}
		
		if(playerColor == Color.WHITE){ //for white pieces direction changes
			moveForwardTwo = -2;
			moveForwardOne = -1;
			pawnRowOnPlySide = 6;
		}
		else{ //black
			moveForwardTwo = 2;
			moveForwardOne = 1;
			pawnRowOnPlySide = 1;
		}
			
		if(moveToY == moveFromY + moveForwardOne){
			
			//move to take a piece that is of a different color to the diagonally
			if((moveToX == moveFromX - 1) || (moveToX == moveFromX + 1)){
				if((toSquare.getType() != PieceType.BLANK) && (toSquare.getColor() != playerColor)){
					return true; 
				}
			}	
			//straight move forward 1 and move is to blank space
			else if((moveToX == moveFromX) && (toSquare.getType() == PieceType.BLANK)){
				return true;
			}
		}
		//move forward 2 straight and is to blank space
		else if((moveToY == moveFromY + moveForwardTwo) && (moveToX == moveFromX) && (toSquare.getType() == PieceType.BLANK)){
			if(moveFromY == pawnRowOnPlySide){ //if pawn moves from the starting row
				return true;
			}
		}
		
		return false; //only get here if other possiblities fail
	}	
}
