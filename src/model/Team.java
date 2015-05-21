package model;

import java.util.ArrayList;

public abstract class Team {
	private ArrayList<Piece> pieces;
	private boolean stillKingAlive;
	
	public Team (){
		this.stillKingAlive = true;
		this.pieces = null;
	}
	
	public abstract ArrayList<Piece> initPieces();

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}
	
	public boolean stillKingAlive() {
		return this.stillKingAlive;
	}

	public void killKing() {
		this.stillKingAlive = false;
	}
	
}
