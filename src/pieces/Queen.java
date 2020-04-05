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
		
		int sourceColumn = sourceCoordinates[0];
		int sourceRow = sourceCoordinates[1];
		int destinationColumn = destinationCoordinates[0];
		int destinationRow = destinationCoordinates[1];
		
		Square toSquare = Board.board[destinationRow][destinationColumn];
		
		String direction;
		String type;
		
		if(!isKing){
			if(toSquare.getType() == PieceType.KING){
				return false;
			}
		}
		
		if(destinationRow == sourceRow){
			if(destinationColumn > sourceColumn){
				direction = "right";
				type = "straight";
			}
			else{
				direction = "left";
				type = "straight";
			}
		}
		
		else if(destinationColumn == sourceColumn){
			if(destinationRow > sourceRow){
				direction = "bot";
				type = "straight";
			}
			else{
				direction = "top";
				type = "straight";
			}
		}
		else if(destinationColumn > sourceColumn){
			if(destinationRow < sourceRow){
				direction = "topRight";
				type = "diagonal";
			}
			else{
				direction = "botRight";
				type = "diagonal";
			}
		}
		else {
			if(destinationRow < sourceRow){
				direction = "topLeft";
				type = "diagonal";
			}
			else{
				direction = "botLeft";
				type = "diagonal";
			}
		}
		
		Square testSquare;
		
		if(type.equals("diagonal")){
			int moveDistance = Math.abs(destinationColumn - sourceColumn);
		
			for(int diagonalMoveAway = 1; diagonalMoveAway <= moveDistance; diagonalMoveAway++){
			
				if(direction.equals("topRight")){
					testSquare = Board.board[sourceRow - diagonalMoveAway][sourceColumn + diagonalMoveAway];
				}
				else if(direction.equals("botRight")){
					testSquare = Board.board[sourceRow + diagonalMoveAway][sourceColumn + diagonalMoveAway];
				}
				else if(direction.equals("topLeft")){
					testSquare = Board.board[sourceRow - diagonalMoveAway][sourceColumn - diagonalMoveAway];
				}
				else{
					testSquare = Board.board[sourceRow + diagonalMoveAway][sourceColumn - diagonalMoveAway];
				}
			
				if((testSquare.getType() != PieceType.BLANK) && (diagonalMoveAway != moveDistance)){
					return false;
				}
				else if((diagonalMoveAway == moveDistance) && ((testSquare.getColor() != playerColor) || (testSquare.getType() == PieceType.BLANK))){
					return true;
				}
			}
		}
		else{
			if((direction.equals("right")) || (direction.equals("left"))){
				int displaceMax = Math.abs(destinationColumn - sourceColumn); //displacement max depending on what the move to values are
		
				for(int displace = 1; displace <= displaceMax; displace++){ //looping through squares on the rooks path
					if(direction.equals("right")){
						testSquare = Board.board[sourceRow][sourceColumn + displace];
					
						if((testSquare.getType() != PieceType.BLANK) && (displace != displaceMax)){
						return false;
						}
						else if((displace == displaceMax) && ((testSquare.getType() == PieceType.BLANK) || (testSquare.getColor() != playerColor))){
							return true;
						}
					}
					else{
						testSquare = Board.board[sourceRow][sourceColumn - displace];
					
						if((testSquare.getType() != PieceType.BLANK) && (displace != displaceMax)){
							return false;
						}
						else if((displace == displaceMax) && ((testSquare.getType() == PieceType.BLANK) || (testSquare.getColor() != playerColor))){
							return true;
						}
					}
				}
			}
			else{
				int displaceMax = Math.abs(destinationRow - sourceRow);
				
				for(int displace = 1; displace <= displaceMax; displace++){
				
					if(direction == "top"){
						testSquare = Board.board[sourceRow - displace][sourceColumn];
					
						if((testSquare.getType() != PieceType.BLANK) && (displace != displaceMax)){
							return false;
						}
						else if((displace == displaceMax) && ((testSquare.getType() == PieceType.BLANK) || (testSquare.getColor() != playerColor))){
							return true;
						}
					}
					else{
						testSquare = Board.board[sourceRow + displace][sourceColumn];
					
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
