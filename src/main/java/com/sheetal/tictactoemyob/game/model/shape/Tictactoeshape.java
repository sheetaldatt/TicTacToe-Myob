package com.sheetal.tictactoemyob.game.model.shape;

import com.sheetal.tictactoemyob.game.model.Coordinate;

public abstract class Tictactoeshape {
	
	private Coordinate coordinate;
	
	
	public Tictactoeshape(){}

	public Tictactoeshape(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public Coordinate getCoordinate(){
		return coordinate;
	}
	
	public void setCoordinate(Coordinate coordinate){
		this.coordinate = coordinate;
	}

}

