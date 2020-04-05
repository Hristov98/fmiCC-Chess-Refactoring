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
		
		int sourceColumn = sourceCoordinates[0];
		int sourceRow = sourceCoordinates[1];
		int destinationColumn = destinationCoordinates[0];
		int destinationRow = destinationCoordinates[1];
		
		Square toSquare = Board.board[destinationRow][destinationColumn];
		
		String direction;
		
		if(!isKing){
			if(toSquare.getType() == PieceType.KING){
				return false;
			}
		}
		
		if(destinationRow == sourceRow){
			if(destinationColumn > sourceColumn){
				direction = "right";
			}
			else{
				direction = "left";
			}
		}
		
		else if(destinationColumn == sourceColumn){
			if(destinationRow > sourceRow){
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
		
		if((direction.equals("right")) || (direction.equals("left"))){
			int maxDisplacement = Math.abs(destinationColumn - sourceColumn);
		
			for(int displacement = 1; displacement <= maxDisplacement; displacement++){
				if(direction.equals("right")){
					testSquare = Board.board[sourceRow][sourceColumn + displacement];
					
					if((testSquare.getType() != PieceType.BLANK) && (displacement != maxDisplacement)){
						return false;
					}
					else if((displacement == maxDisplacement) && ((testSquare.getType() == PieceType.BLANK) || (testSquare.getColor() != playerColor))){
						return true;
					}
				}
				else{
					testSquare = Board.board[sourceRow][sourceColumn - displacement];
					
					if((testSquare.getType() != PieceType.BLANK) && (displacement != maxDisplacement)){
						return false;
					}
					else if((displacement == maxDisplacement) && ((testSquare.getType() == PieceType.BLANK) || (testSquare.getColor() != playerColor))){
						return true;
					}
				}
			}
		}
		else{
			int displaceMax = Math.abs(destinationRow - sourceRow);
				
			for(int displacement = 1; displacement <= displaceMax; displacement++){
				
				if(direction.equals("top")){
					testSquare = Board.board[sourceRow - displacement][sourceColumn];

				}
				else{
					testSquare = Board.board[sourceRow + displacement][sourceColumn];

				}
				if((testSquare.getType() != PieceType.BLANK) && (displacement != displaceMax)){
					return false;
				}
				else if((displacement == displaceMax) && ((testSquare.getType() == PieceType.BLANK) || (testSquare.getColor() != playerColor))){
					return true;
				}
			}
		}
		return false;
	}
}
