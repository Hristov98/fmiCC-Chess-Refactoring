package pieces;

import chessApp.Board;
import chessApp.Color;

public class Rook extends Piece{

	public Rook(Color colorIn) {
		super(colorIn, PieceType.ROOK);
		
		if(color == Color.WHITE){
			symbol = "wRo";
		}
		else{
			symbol = "bRo";
		}
	}

	public boolean checkMove(int[] sourceCoordinates, int[] destinationCoordinates, Color playerColor, boolean isKing) {
		
		int moveFromX = sourceCoordinates[0];
		int moveFromY = sourceCoordinates[1];
		int moveToX = destinationCoordinates[0];
		int moveToY = destinationCoordinates[1];
		
		Square toSquare = Board.board[moveToY][moveToX];
		
		String direction;
		
		if(!isKing){
			if(toSquare.getType() == PieceType.KING){
				return false; //can't move to take a king
			}
		}
		
		if(moveToY == moveFromY){
			if(moveToX > moveFromX){
				direction = "rite";
			}
			else{
				direction = "left";
			}
		}
		
		else if(moveToX == moveFromX){
			if(moveToY > moveFromY){
				direction = "bot";
			}
			else{
				direction = "top";
			}
		}
		else{
			return false;
		}
		
		Square testSquare;
		
		if((direction == "rite") || (direction == "left")){
			int displaceMax = Math.abs(moveToX - moveFromX); //displacement max depending on what the move to values are
		
			for(int displace = 1; displace <= displaceMax; displace++){ //looping through squares on the rooks path
				if(direction == "rite"){
					testSquare = Board.board[moveFromY][moveFromX + displace];
					
					if((testSquare.getType() != PieceType.BLANK) && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType() == PieceType.BLANK) || (testSquare.getColor() != playerColor))){
						return true;
					}
				}
				else{
					testSquare = Board.board[moveFromY][moveFromX - displace];
					
					if((testSquare.getType() != PieceType.BLANK) && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType() == PieceType.BLANK) || (testSquare.getColor() != playerColor))){
						return true;
					}
				}
			}
		}
		else{ // direction : top or bot
			int displaceMax = Math.abs(moveToY - moveFromY); //displacement max depending on what the move to values are
				
			for(int displace = 1; displace <= displaceMax; displace++){ //looping through squares on the rooks path	
				
				if(direction == "top"){
					testSquare = Board.board[moveFromY - displace][moveFromX];
					
					if((testSquare.getType() != PieceType.BLANK) && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType() == PieceType.BLANK) || (testSquare.getColor() != playerColor))){
						return true;
					}
				}
				else{
					testSquare = Board.board[moveFromY + displace][moveFromX];
					
					if((testSquare.getType() != PieceType.BLANK) && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType() == PieceType.BLANK) || (testSquare.getColor() != playerColor))){
						return true;
					}
				}
			}
		}
		return false;
	}
}
