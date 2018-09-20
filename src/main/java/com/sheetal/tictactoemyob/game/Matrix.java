package com.sheetal.tictactoemyob.game;

import com.sheetal.tictactoemyob.game.exceptions.PositionOccupiedException;
import com.sheetal.tictactoemyob.game.model.shape.Asterisk;
import com.sheetal.tictactoemyob.game.model.shape.Circle;
import com.sheetal.tictactoemyob.game.model.shape.Tictactoeshape;

public class Matrix {
	
		private enum MatrixDirection{VERTICAL, HORIZONTAL, DIAGONAL}
		
		private final static int MATRIX_DIMENSION = 3;
		
		private Tictactoeshape[][] arrayMatrix;
		
		
		public Matrix(){
			setArrayMatrix(new Tictactoeshape[MATRIX_DIMENSION][MATRIX_DIMENSION]);
		}
		
		public void writeElement(Tictactoeshape element) throws PositionOccupiedException{
			int x = element.getCoordinate().getX();
			int y = element.getCoordinate().getY();
			
			if(getArrayMatrix()[x][y] == null){
				getArrayMatrix()[x][y] = element;
			} else {
				throw new PositionOccupiedException(x, y);
			}
		}
		
		public Tictactoeshape checkRows(){
			return linesChecker(MatrixDirection.HORIZONTAL);
		}
		
		public Tictactoeshape checkCols() {
			return linesChecker(MatrixDirection.VERTICAL);
		}
		
		public Tictactoeshape checkDiagonals() {
			return linesChecker(MatrixDirection.DIAGONAL);
		}
		
		private Tictactoeshape linesChecker(MatrixDirection direction){
			int crossCounter = 0;
			int circleCounter = 0;
			
			for(int i = 0; i < arrayMatrix.length; i++){
				for(int j = 0; j < arrayMatrix[i].length; j++){
					Tictactoeshape shape;
					if(direction == MatrixDirection.VERTICAL){
						shape = getArrayMatrix()[j][i];
					} else if( direction == MatrixDirection.HORIZONTAL) {
						shape = getArrayMatrix()[i][j];
					} else {
						if(i == 0){
							shape = arrayMatrix[j][j];
						} else {
							shape = arrayMatrix[j][(MATRIX_DIMENSION - 1) - j];
						}
					}
					  
					if(shape != null){
						if(shape instanceof Circle){
							circleCounter++;
						}else{
							crossCounter++;
						}
					}
				}
				
				if(circleCounter == 3){
					return new Circle();
				}
				
				if(crossCounter == 3){
					return new Asterisk();
				}
				
				crossCounter = 0;
				circleCounter = 0;
				
				if(direction == MatrixDirection.DIAGONAL && i > 0){
					break;
				}
			}
			
			return null;
		}
		
		public boolean isFull() {
			for(int i = 0; i < arrayMatrix.length; i++){
				for(int j = 0 ; j < arrayMatrix[i].length ; j++){
					if(arrayMatrix[i][j] == null){
						return false;
					}
				}
			}
			
			return true;
		}

		public Tictactoeshape[][] getArrayMatrix() {
			return arrayMatrix;
		}

		public void setArrayMatrix(Tictactoeshape[][] arrayMatrix) {
			this.arrayMatrix = arrayMatrix;
		}

	}

