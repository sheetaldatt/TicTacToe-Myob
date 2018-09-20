package com.sheetal.tictactoemyob.game.model.shape;

import com.sheetal.tictactoemyob.game.model.Coordinate;

public class Circle extends Tictactoeshape {
	public Circle(Coordinate coordinate) {
		super(coordinate);
	}

	public Circle() {
	}
	
	@Override
	public String toString() {
		return "Circle";
	}

}
