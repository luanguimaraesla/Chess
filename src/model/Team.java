package model;

import java.awt.Point;
import java.util.ArrayList;

public class Team extends ArrayList<Piece>{
	
	private static final long serialVersionUID = 1L;
	public static final int TEAM_UP = 1;
	public static final int TEAM_DOWN = 0;
	
	private boolean stillKingAlive;
	private int teamOrientation;
	
	public Team (int teamOrientation){
		this.stillKingAlive = true;
		this.setTeamOrientation(teamOrientation);
	}
	
	public boolean ContainsPieceAt(Point point){
		for(Piece eachPiece : this)
			if (eachPiece.getPosition().getX() == point.getX() &&
				eachPiece.getPosition().getY() == point.getY())
				return true;
		return false;
	}
	
	public boolean ContainsPieceAt(int x, int y){
		for(Piece eachPiece : this)
			if ((int) eachPiece.getPosition().getX() == x &&
				(int) eachPiece.getPosition().getY() == y)
				return true;
		return false;
	}
	
	public boolean stillKingAlive() {
		return this.stillKingAlive;
	}

	public void killKing() {
		this.stillKingAlive = false;
	}

	public int getTeamOrientation() {
		return teamOrientation;
	}

	public void setTeamOrientation(int teamOrientation) {
		this.teamOrientation = teamOrientation;
	}
	
}
