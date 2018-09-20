package com.sheetal.tictactoemyob.game.exceptions;

public class WaitForYourTurnException extends Exception {
private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "Wait your turn.";
	
	public WaitForYourTurnException() {
		super(MESSAGE);
	}

}

