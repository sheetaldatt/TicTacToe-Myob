package com.sheetal.tictactoemyob.game;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import com.sheetal.tictactoemyob.game.exceptions.*;

import com.sheetal.tictactoemyob.game.model.Player;
import com.sheetal.tictactoemyob.game.model.shape.*;



public class TicTacToeImplementationTest {
	
		
		private TicTacToeImplementation game;
		
		@Before
		public void setUp(){
			game = new TicTacToeImplementation();
		}
		
		private void startGame() throws NoPlayersRegisteredException, WrongShapeException{
			Player circlePlayer = new Player(new Circle());
			Player crossPlayer = new Player(new Asterisk());
			game.registerPlayers(circlePlayer, asteriskPlayer);
		}
		
		@Test
		public void testStartGame() throws NoPlayersRegisteredException, WrongShapeException{
			startGame();
		}
		
		@Test(expected = WrongShapeException.class)
		public void testStartGame_wrongShape() throws NoPlayersRegisteredException, WrongShapeException{
			Player circlePlayer = new Player(new Asterisk());
			Player asteriskPlayer = new Player(new Asterisk());
			game.registerPlayers(circlePlayer, asteriskPlayer);
		}
		
		@Test
		public void testPlay() throws PositionOccupiedException, WaitForYourTurnException, GameOverException, NoPlayersRegisteredException, WrongShapeException{
			startGame();
			DefineGameCoordinates coordinate = DefineGameCoordinates.A1;
			game.playAsterisk(coordinate);
			
			DefineGameCoordinates coordinateCircle = DefineGameCoordinates.C2;
			game.playCircle(coordinateCircle );
			
			DefineGameCoordinates coordinate2 = DefineGameCoordinates.C1;
			game.playAsterisk(coordinate2);
			
			DefineGameCoordinates coordinateCircle2 = DefineGameCoordinates.B2;
			game.playCircle(coordinateCircle2 );
			
		}
		
		@Test(expected = WaitForYourTurnException.class)
		public void testPlay_twice() throws PositionOccupiedException, WaitForYourTurnException, GameOverException, NoPlayersRegisteredException, WrongShapeException{
			startGame();
			DefineGameCoordinates coordinate = DefineGameCoordinates.B1;
			game.playAsterisk(coordinate);
			
			DefineGameCoordinates coordinate2 = DefineGameCoordinates.B1;
			game.playAsterisk(coordinate2);
		}
		
		
		@Test(expected = PositionOccupiedException.class)
		public void testPlay_positionOccupied() throws PositionOccupiedException, WaitForYourTurnException, GameOverException, NoPlayersRegisteredException, WrongShapeException{
			startGame();
			DefineGameCoordinates coordinate = DefineGameCoordinates.C2;
			game.playAsterisk(coordinate );
			
			DefineGameCoordinates coordinateCircle = DefineGameCoordinates.C2;
			game.playCircle(coordinateCircle );
		}
		
		@Test(expected = GameOverException.class)
		public void testPlay_gameFinished() throws PositionOccupiedException, WaitForYourTurnException, GameOverException, NoPlayersRegisteredException, WrongShapeException{
			winGameAsterisk();
			
			DefineGameCoordinates coordinate = DefineGameCoordinates.C1;
			game.playCircle(coordinate );
		}
		
		@Test
		public void testWinner() throws NoPlayersRegisteredException, WrongShapeException, PositionOccupiedException, WaitYourTurnException, GameOverException{
			startGame();
			
			game.playAsterisk(DefineGameCoordinates.A1);
			game.playCircleDefine(DefineGameCoordinates.A3);
			
			game.playAsterisk(DefineGameCoordinates.B2);
			game.playCircle(DefineGameCoordinates.C1);
			
			game.playAsterisk(DefineGameCoordinates.C3);
			
			Player winner = game.checkWinner();
			
			assertFalse(game.isTie());
			assertTrue(winner.getShape() instanceof Asterisk);
			
		}
		
		@Test
		public void testIsTie() throws NoPlayersRegisteredException, WrongShapeException, PositionOccupiedException, WaitYourTurnException, GameOverException{
			tieGame();
			assertTrue(game.isTie());
		}
		
		@Test
		public void testCheckWinner() throws NoPlayersRegisteredException, WrongShapeException, PositionOccupiedException, WaitForYourTurnException, GameOverException{
			winGameAsterisk();
			Player winner = game.checkWinner();
			
			assertNotNull(winner);
			assertTrue(winner.getShape() instanceof Asterisk);
		}
		
		@Test
		public void testCheckWinner_noWinner() throws NoPlayersRegisteredException, WrongShapeException, PositionOccupiedException, WaitForYourTurnException, GameOverException{
			startGame();
			
			DefineGameCoordinates coordinate = DefineGameCoordinates.B2;
			game.playAsterisk(coordinate);
			
			Player winner = game.checkWinner();
			
			assertNull(winner);
		}
		
		@Test(expected = GameOverException.class)
		public void testTieGame() throws NoPlayersRegisteredException, WrongShapeException, PositionOccupiedException, WaitForYourTurnException, GameOverException{
			tieGame();
			
			game.playAsterisk(DefineGameCoordinates.A1);
		}
		
		@Test
		public void testGameMatrix() throws NoPlayersRegisteredException, WrongShapeException, PositionOccupiedException, WaitForYourTurnException, GameOverException{
			tieGame();
			
			Map<DefineGameCoordinates, Tictactoeshape> matrix = game.getGameMatrix();
			
			assertTrue(matrix.get(DefineGameCoordinates.A1) instanceof Circle);
			assertTrue(matrix.get(DefineGameCoordinates.A3) instanceof Circle);
			assertTrue(matrix.get(DefineGameCoordinates.A2) instanceof Asterisk);
			assertTrue(matrix.get(DefineGameCoordinates.C1) instanceof Circle);
			
		}
		
		private void tieGame() throws NoPlayersRegisteredException, WrongShapeException, PositionOccupiedException, WaitForYourTurnException, GameOverException{
			startGame();
			DefineGameCoordinates x1 = DefineGameCoordinates.A2;
			DefineGameCoordinates x2 = DefineGameCoordinates.B1;
			DefineGameCoordinates x3 = DefineGameCoordinates.C2;
			DefineGameCoordinates x4 = DefineGameCoordinates.C3;
			
			DefineGameCoordinates o1 = DefineGameCoordinates.A1;
			DefineGameCoordinates o2 = DefineGameCoordinates.A3;
			DefineGameCoordinates o3 = DefineGameCoordinates.B2;
			DefineGameCoordinates o4 = DefineGameCoordinates.B3;
			DefineGameCoordinates o5 = DefineGameCoordinates.C1;
			
			game.playCircle(o1);
			game.playAsterisk(x1);
			
			game.playCircle(o2);
			game.playAsterisk(x2);
			
			game.playCircle(o3);
			game.playAsterisk(x3);
			
			game.playCircle(o4);
			game.playAsterisk(x4);
			
			game.playCircle(o5);
		}
		
		private void winGameCross() throws NoPlayersRegisteredException, WrongShapeException, PositionOccupiedException, WaitYourTurnException, GameOverException{
			startGame();
			DefineGameCoordinates x1 = DefineGameCoordinates.A1;
			DefineGameCoordinates x2 = DefineGameCoordinates.A2;
			DefineGameCoordinates x3 = DefineGameCoordinates.A3;
			
			DefineGameCoordinates o1 = DefineGameCoordinates.B1;
			DefineGameCoordinates o2 = DefineGameCoordinates.B2;
			
			game.playAsterisk(x1);
			game.playCircle(o1);
			
			game.playAsterisk(x2);
			game.playCircle(o2);
			
			game.playAsterisk(x3);
			
		}
		

	}

