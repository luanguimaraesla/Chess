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
		return ContainsPieceAt(point.getX(), point.getY());
	}
	
	public boolean ContainsPieceAt(double x, double y){
		for(Piece eachPiece : this)
			if (eachPiece.getPosition().getX() == x &&
				eachPiece.getPosition().getY() == y)
				return true;
		return false;
	}
	
	public boolean stillKingAlive() {
		return this.stillKingAlive;
	}

	public void killKing() {
		this.stillKingAlive = false;
	}
	
	public Piece getKing(){
		for(Piece kingSearch : this)
			if(kingSearch instanceof King)
				return kingSearch;
		
		return null;
	}

	public int getTeamOrientation() {
		return teamOrientation;
	}

	public void setTeamOrientation(int teamOrientation) {
		this.teamOrientation = teamOrientation;
	}
	
	public boolean kill(Piece piece){
		if(piece instanceof King)
			killKing();
		
		return this.remove(piece);
	}
	
}
