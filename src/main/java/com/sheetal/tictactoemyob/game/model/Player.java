package com.sheetal.tictactoemyob.game.model;

import com.sheetal.tictactoemyob.game.model.shape.Tictactoeshape;

public class Player {
	private Tictactoeshape shape;
	
	private boolean played;
	public Player(Tictactoeshape shape) {
		this.shape = shape;
	}

	public Tictactoeshape getShape() {
		return shape;
	}

	public void setShape(Tictactoeshape shape) {
		this.shape = shape;
	}

	public boolean isPlayed() {
		return played;
	}

	public void setPlayed(boolean played) {
		this.played = played;
	}
}
