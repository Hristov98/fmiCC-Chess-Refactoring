package pieces;

import chessApp.Board;
import chessApp.Color;

public class Queen extends Piece{

	public Queen(Color colorIn) {
		super(colorIn, PieceType.QUEEN);
		
		if(color == Color.WHITE){
			symbol = "wQu";
		}
		else{
			symbol = "bQu";
		}
	}

	@Override
	public boolean checkMove(int[] sourceCoordinates, int[] destinationCoordinates, Color playerColor, boolean isKing) {
		
		int moveFromX = sourceCoordinates[0];
		int moveFromY = sourceCoordinates[1];
		int moveToX = destinationCoordinates[0];
		int moveToY = destinationCoordinates[1];
		
		Square toSquare = Board.board[moveToY][moveToX];
		
		String direction;
		String type; //diagonal or straight
		
		if(!isKing){
			if(toSquare.getType() == PieceType.KING){
				return false; //can't move to take a king
			}
		}
		
		if(moveToY == moveFromY){ //setting all possible move directions
			if(moveToX > moveFromX){
				direction = "rite";
				type = "straight";
			}
			else{
				direction = "left";
				type = "straight";
			}
		}
		
		else if(moveToX == moveFromX){
			if(moveToY > moveFromY){
				direction = "bot";
				type = "straight";
			}
			else{
				direction = "top";
				type = "straight";
			}
		}
		else if(moveToX > moveFromX){
			if(moveToY < moveFromY){
				direction = "topRite";
				type = "diagonal";
			}
			else{
				direction = "botRite";
				type = "diagonal";
			}
		}
		else if(moveToX < moveFromX){
			if(moveToY < moveFromY){
				direction = "topLeft";
				type = "diagonal";
			}
			else{
				direction = "botLeft";
				type = "diagonal";
			}
		}
		else{
			return false;
		}
		
		Square testSquare;
		
		if(type == "diagonal"){
			int moveDistance = Math.abs(moveToX - moveFromX);
		
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
		}
		else{ //straight
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
		}
		return false;
	}

}
