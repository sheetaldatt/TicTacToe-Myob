package com.sheetal.tictactoemyob.game;

import java.util.HashMap;
import java.util.Map;

import com.sheetal.tictactoemyob.game.exceptions.*;
import com.sheetal.tictactoemyob.game.model.Player;
import com.sheetal.tictactoemyob.game.model.shape.*;



public class TicTacToeImplementation implements TicTacToe {
	private enum PlayerShape{CIRCLE, ASTERISK}

	private static final String TIE_MESSAGE = "Game Over, there was a tie";


	private static final String WINNER_MESSAGE = "Game Over, there is already a winner";

	
	private GameManager gameManager;
	
	private PlayerManager circlePlayerManager;
	private PlayerManager asteriskPlayerManager;

	
	public void registerPlayers(Player circlePlayer, Player asteriskPlayer) throws WrongShapeException{
		if(!(circlePlayer.getShape() instanceof Circle) || !(asteriskPlayer.getShape() instanceof Asterisk)){
			throw new WrongShapeException();
		}
		
		circlePlayerManager = new PlayerManager(circlePlayer);
		asteriskPlayerManager = new PlayerManager(asteriskPlayer);
		
		gameManager = new GameManager(circlePlayerManager, asteriskPlayerManager);
		
	}
	
	public void playAsterisk(DefineGameCoordinates coordinate) throws PositionOccupiedException, WaitForYourTurnException, GameOverException, NoPlayersRegisteredException{
		play(PlayerShape.ASTERISK, coordinate);
	}
	
	public void playCircle(DefineGameCoordinates coordinate) throws PositionOccupiedException, WaitForYourTurnException, GameOverException, NoPlayersRegisteredException{
		play(PlayerShape.CIRCLE, coordinate);
	}
	
	private void play(PlayerShape shape, DefineGameCoordinates coordinate) throws PositionOccupiedException, WaitForYourTurnException, GameOverException, NoPlayersRegisteredException{
		
		if(asteriskPlayerManager == null || circlePlayerManager == null){
			throw new NoPlayersRegisteredException();
		}
		
		PlayerManager playerManager;
		if(shape == PlayerShape.ASTERISK){
			playerManager = asteriskPlayerManager;
		} else {
			playerManager = circlePlayerManager;
		}
		playerManager.setCoordinate(coordinate.getCoordinates());
		
		Player winner = checkWinner();
		if(winner == null && !isTie()){ 
			gameManager.play(playerManager);
		} else {
			if(isTie()){
				throw new GameOverException(TIE_MESSAGE);
			}else{
				throw new GameOverException(String.format("%s: %s", WINNER_MESSAGE, winner.getShape().toString()));
			}
			
		}
	}
	
	public Player checkWinner(){
		PlayerManager playerManager = gameManager.checkWinner();
		
		if(playerManager != null){
			return playerManager.getPlayer();
		}
		
		return null;
	}
	
	public Map<DefineGameCoordinates, Tictactoeshape> getGameMatrix(){
		Tictactoeshape[][] matrix = gameManager.getGameMatrix();
		Map<DefineGameCoordinates, Tictactoeshape> gameMatrix = new HashMap<>();
		
		for(int i = 0; i < matrix.length; i++){
			for(int j=0; j < matrix[i].length ; j++){
				Tictactoeshape shape = matrix[i][j];
				DefineGameCoordinates gameCoordinates = DefineGameCoordinates.getGameCoordinates(i, j);
				
				gameMatrix.put(gameCoordinates, shape);
			}
		}
		
		return gameMatrix;
	}
	
	public boolean isTie(){
		return gameManager.isTie();
	}

	
}
