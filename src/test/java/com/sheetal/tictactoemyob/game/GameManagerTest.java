package com.sheetal.tictactoemyob.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sheetal.tictactoemyob.game.exceptions.*;
import com.sheetal.tictactoemyob.game.model.shape.Asterisk;
import com.sheetal.tictactoemyob.game.model.shape.Circle;
import com.sheetal.tictactoemyob.game.model.shape.Tictactoeshape;

import static org.junit.Assert.*;
import org.easymock.EasyMock;
import org.junit.Before;



@RunWith(SpringRunner.class)
@SpringBootTest
public class GameManagerTest {

	private GameManager gameManager;
	private PlayerManager circlePlayer;
	private PlayerManager asteriskPlayer;
	private Matrix matrix;
	
	@Before
	public void setUp(){
		circlePlayer = EasyMock.createMock(PlayerManager.class);
		asteriskPlayer  = EasyMock.createMock(PlayerManager.class);
		matrix = EasyMock.createMock(Matrix.class);
		
		gameManager = new GameManager(circlePlayer, asteriskPlayer);
		gameManager.setMatrix(matrix);
	}
	
	@Test
	public void testPlay() throws PositionOccupiedException, WaitForYourTurnException{
		PlayerManager playerManager = circlePlayer;
		EasyMock.expect(playerManager.hasPlayed()).andReturn(false);
		Tictactoeshape circleShape = new Circle();
		EasyMock.expect(playerManager.getShape()).andReturn(circleShape);
		matrix.writeElement(circleShape);
		
		circlePlayer.setPlayed(true);
		asteriskPlayer.setPlayed(false);
		
		EasyMock.replay(circlePlayer, asteriskPlayer, matrix);
		
		gameManager.play(playerManager);
		
		EasyMock.verify(circlePlayer, asteriskPlayer, matrix);
	}
	
	@Test(expected = WaitForYourTurnException.class)
	public void testPlayShape_alreadyPlayed() throws PositionOccupiedException, WaitForYourTurnException{
		PlayerManager playerManager = asteriskPlayer;
		EasyMock.expect(asteriskPlayer.hasPlayed()).andReturn(true);
		
		EasyMock.replay(circlePlayer, asteriskPlayer, matrix);
		
		gameManager.play(playerManager);
		
		EasyMock.verify(circlePlayer, asteriskPlayer, matrix);
	}
	
	@Test
	public void testCheckWinner(){
		Tictactoeshape winnerShape = new Asterisk();
		
		EasyMock.expect(matrix.checkRows()).andReturn(winnerShape);
		
		EasyMock.replay(circlePlayer, asteriskPlayer, matrix);
		
		PlayerManager winner = gameManager.checkWinner();
		
		assertEquals(asteriskPlayer, winner);
		
		EasyMock.verify(circlePlayer, asteriskPlayer, matrix);
	}

}

	

