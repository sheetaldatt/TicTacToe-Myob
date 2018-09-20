package com.sheetal.tictactoemyob.game;

import com.sheetal.tictactoemyob.game.model.*;
import com.sheetal.tictactoemyob.game.model.shape.Tictactoeshape;

public class PlayerManager {
	
	private Player player;
	
	public PlayerManager(Player player) {
		this.setPlayer(player);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean hasPlayed() {
		return player.isPlayed();
	}

	public Tictactoeshape getShape() {
		return player.getShape();
	}

	public void setPlayed(boolean played) {
		player.setPlayed(played);
	}

	public void setCoordinate(Coordinate coordinate) {
		player.getShape().setCoordinate(coordinate);
	}

}
