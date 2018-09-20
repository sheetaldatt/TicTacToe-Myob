package com.sheetal.tictactoemyob.game;

import java.util.Map;

import com.sheetal.tictactoemyob.game.exceptions.*;
import com.sheetal.tictactoemyob.game.model.Player;
import com.sheetal.tictactoemyob.game.model.shape.Tictactoeshape;

public interface TicTacToe {
	
	/**
	 * Method that will register two players
	 * to play the game.
	 * 
	 * @param circlePlayer
	 * @param AsteriskPlayer
	 * @throws WrongShapeException
	 */
	void registerPlayers(Player circlePlayer, Player asteriskPlayer) throws WrongShapeException;
	
	/**
	 * Place a 'X' in the matrix of the game.
	 * 
	 * @param coordinate
	 * @throws PositionOccupiedException
	 * @throws WaitYourTurnException
	 * @throws GameOverException
	 */
	void playAsterisk(DefineGameCoordinates coordinate) throws PositionOccupiedException, WaitForYourTurnException, GameOverException, NoPlayersRegisteredException;
	
	/**
	 * Place a 'O' in the matrix of the game.
	 * 
	 * @param coordinate
	 * @throws PositionOccupiedException
	 * @throws WaitYourTurnException
	 * @throws GameOverException
	 */
	void playCircle(DefineGameCoordinates coordinate) throws PositionOccupiedException, WaitForYourTurnException, GameOverException, NoPlayersRegisteredException;
	
	/**
	 * Method that will return the winner player in case there is a winner.
	 * 
	 * @return
	 */
	Player checkWinner();
	
	/**
	 * Method that will verify if the game is a tie. 
	 * @return
	 */
	boolean isTie();
	
	/**
	 * Method that will return the shapes that occupy
	 * the cells of the matrix of the game.
	 * 
	 * @return
	 */
	Map<DefineGameCoordinates, Tictactoeshape> getGameMatrix();
	
	

}
