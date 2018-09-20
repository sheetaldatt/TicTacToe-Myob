package com.sheetal.tictactoemyob.game;

import com.sheetal.tictactoemyob.game.exceptions.PositionOccupiedException;
import com.sheetal.tictactoemyob.game.exceptions.WaitForYourTurnException;
import com.sheetal.tictactoemyob.game.model.shape.Circle;
import com.sheetal.tictactoemyob.game.model.shape.Tictactoeshape;

public class GameManager {
		
		private PlayerManager circlePlayer;
		private PlayerManager crossPlayer;
		
		private Matrix matrix;
		
		public GameManager(PlayerManager circlePlayer, PlayerManager crossPlayer){
			matrix = new Matrix();
			this.circlePlayer = circlePlayer;
			this.crossPlayer = crossPlayer;
		}
		
		public void play(PlayerManager playerManager) throws PositionOccupiedException, WaitForYourTurnException{
			if(!playerManager.hasPlayed()){
				matrix.writeElement(playerManager.getShape());
				gameTurnsSetter(playerManager);
			} else {
				throw new WaitForYourTurnException();
			}
		}

		private void gameTurnsSetter(PlayerManager playerManager) {
			if(playerManager.equals(circlePlayer)){
				circlePlayer.setPlayed(true);
				crossPlayer.setPlayed(false);
			} else {
				circlePlayer.setPlayed(false);
				crossPlayer.setPlayed(true);
			}
		}

		public PlayerManager checkWinner(){
			
			Tictactoeshape rowWinnerShape = matrix.checkRows();
			if(rowWinnerShape != null){
				return verifyWinnerShape(rowWinnerShape);
			}
			
			Tictactoeshape colWinnerShape = matrix.checkCols();
			if(colWinnerShape != null){
				return verifyWinnerShape(colWinnerShape);
			}
			
			Tictactoeshape diagonalWinnerShape = matrix.checkDiagonals();
			if(diagonalWinnerShape != null){
				return verifyWinnerShape(diagonalWinnerShape);
			}
			
			return null;
		}
		
		public Tictactoeshape[][] getGameMatrix(){
			return matrix.getArrayMatrix();
		}
		
		private PlayerManager verifyWinnerShape(Tictactoeshape winnerShape){
			if(winnerShape != null){
				if(winnerShape instanceof Circle){
					return circlePlayer;
				} else {
					return crossPlayer;
				}
			}
			return null;
		}
		
		public void setMatrix(Matrix matrix){
			this.matrix = matrix;
		}

		public boolean isTie() {
			return matrix.isFull();
		}
}
