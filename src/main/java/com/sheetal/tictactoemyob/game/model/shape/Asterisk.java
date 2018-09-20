package com.sheetal.tictactoemyob.game.model.shape;

import com.sheetal.tictactoemyob.game.model.Coordinate;

public class Asterisk extends Tictactoeshape {
	public Asterisk(Coordinate coordinate) {
		super(coordinate);
	}

	public Asterisk() {
	}
	
	@Override
	public String toString() {
		return "Asterisk";
	}

}
