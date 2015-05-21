package model;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Piece {
	public static final char PAWN = 'P';
	public static final char TOWER = 'T';
	public static final char HORSE = 'H';
	public static final char BISHOP = 'B';
	public static final char QUEEN = 'Q';
	public static final char KING = 'K';
	
	private char id;
	private String imagePath;
	
	public Piece (char id, String imagePath){
		this.id = id;
		this.imagePath = imagePath;
	}
	
	public abstract ArrayList<Point> getPossiblePoints();
	
	public char getId() {
		return id;
	}
	public void setId(char id) {
		this.id = id;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
