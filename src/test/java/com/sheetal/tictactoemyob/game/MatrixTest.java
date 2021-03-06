package com.sheetal.tictactoemyob.game;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sheetal.tictactoemyob.game.exceptions.PositionOccupiedException;
import com.sheetal.tictactoemyob.game.model.Coordinate;
import com.sheetal.tictactoemyob.game.model.shape.*;


public class MatrixTest {

	private Matrix matrix;

	@Before
	public void setUp() {
		matrix = new Matrix();
	}

	@Test
	public void checkArrayMatrixSize() {
		assertEquals(3, matrix.getArrayMatrix().length);
		assertEquals(3, matrix.getArrayMatrix()[0].length);
	}

	@Test
	public void writeElementTest() throws PositionOccupiedException {
		Tictactoeshape element = new Asterisk(new Coordinate(2, 2));

		matrix.writeElement(element);

		for (int i = 0; i < matrix.getArrayMatrix().length; i++) {
			for (int j = 0; j < matrix.getArrayMatrix()[i].length; j++) {
				if (j == 2 && i == 2) {
					assertNotNull(matrix.getArrayMatrix()[i][j]);
				} else {
					assertNull(matrix.getArrayMatrix()[i][j]);
				}
			}
		}
	}

	@Test(expected = PositionOccupiedException.class)
	public void writeElementTest_positionOccupied() throws PositionOccupiedException {
		Tictactoeshape element = new Asterisk(new Coordinate(2, 2));
		
		matrix.writeElement(element);
		
		//Rewrite the same element
		matrix.writeElement(element);
	}
	
	@Test
	public void checkRowsTest_winner(){
		for(int j = 0 ; j < 3; j++){
			Tictactoeshape[][] arrayMatrix = new Tictactoeshape[3][3];
			matrix.setArrayMatrix(arrayMatrix);
			arrayMatrix[j][0] = new Circle();
			arrayMatrix[j][1] = new Circle();
			arrayMatrix[j][2] = new Circle();
			
			Tictactoeshape winnShape = matrix.checkRows();
			
			assertTrue(winnShape instanceof Circle);
			assertFalse(winnShape instanceof Asterisk);
		}
	}
	
	@Test
	public void checkRowsTest_NoWinner(){
		for(int j = 0 ; j < 3; j++){
			Tictactoeshape[][] arrayMatrix = new Tictactoeshape[3][3];
			matrix.setArrayMatrix(arrayMatrix);
			arrayMatrix[j][0] = new Circle();
			arrayMatrix[j][2] = new Circle();
			
			Tictactoeshape winnShape = matrix.checkRows();
			
			assertNull(winnShape);
		}
	}
	
	@Test
	public void checkColTest_winner(){
		Tictactoeshape[][] arrayMatrix = matrix.getArrayMatrix();
		arrayMatrix[0][0] = new Asterisk();
		arrayMatrix[2][0] = new Asterisk();
		
		arrayMatrix[0][1] = new Asterisk();
		arrayMatrix[1][1] = new Asterisk();
		
		arrayMatrix[0][2] = new Asterisk();
		arrayMatrix[1][2] = new Asterisk();
		arrayMatrix[2][2] = new Asterisk();
		
		TicTacToeShape winnerShape = matrix.checkCols();
		assertTrue(winnerShape instanceof Asterisk);
	}
	
	@Test
	public void checkColTest_NoWinner(){
		TicTacToeShape[][] arrayMatrix = matrix.getArrayMatrix();
		arrayMatrix[0][0] = new Asterisk();
		arrayMatrix[1][0] = new Asterisk();
		
		arrayMatrix[0][1] = new Asterisk();
		arrayMatrix[1][1] = new Asterisk();
		
		arrayMatrix[0][2] = new Asterisk();
		arrayMatrix[1][2] = new Asterisk();
		
		Tictactoeshape winnerShape = matrix.checkCols();
		assertNull(winnerShape);
	}
	
	@Test
	public void checkDiagTest_winner(){
		Tictactoeshape[][] arrayMatrix = matrix.getArrayMatrix();
		arrayMatrix[0][0] = new Asterisk();
		arrayMatrix[1][1] = new Asterisk();
		arrayMatrix[2][2] = new Asterisk();
		
		TicTacToeShape winnerShape = matrix.checkDiagonals();
		
		assertTrue(winnerShape instanceof Asterisk);
		
		arrayMatrix[2][2] = new Circle();
		
		arrayMatrix[2][0] = new Asterisk();
		arrayMatrix[1][1] = new Asterisk();
		arrayMatrix[0][2] = new Asterisk();
		
		Tictactoeshape newWinnerShape = matrix.checkDiagonals();
		
		assertTrue(newWinnerShape instanceof Asterisk);
	}
	
	@Test
	public void checkDiagTest_NoWinner(){
		Tictactoeshape[][] arrayMatrix = matrix.getArrayMatrix();
		arrayMatrix[0][0] = new Asterisk();
		arrayMatrix[1][1] = new Asterisk();
		arrayMatrix[2][2] = new Asterisk();
		
		TicTacToeShape winnerShape = matrix.checkDiagonals();
		
		assertTrue(winnerShape instanceof Asterisk);
		
		arrayMatrix[2][2] = new Circle();
		
		Tictactoeshape newWinnerShape = matrix.checkDiagonals();
		
		assertNull(newWinnerShape);
	}
	
	@Test
	public void testFullMatrix(){
		assertFalse(matrix.isFull());
		
		for(int i = 0; i < 3 ; i++){
			for(int j = 0 ; j < 3 ; j++ ){
				matrix.getArrayMatrix()[i][j] = new Asterisk();
			}
			
		}
		
		assertTrue(matrix.isFull());
	}

}
