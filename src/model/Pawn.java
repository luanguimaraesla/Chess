package model;

import java.awt.Point;
import java.util.ArrayList;

public class Pawn extends Piece {
	
	private boolean mooved;
	
	public Pawn (String imagePath){
		super(imagePath);
		this.mooved = false;
	}
	
	public Pawn (String imagePath, Point position){
		super(imagePath, position);
		this.mooved = false;
	}
	
	@Override
	public ArrayList<Point> getPossiblePoints(Point myPoint){	
		return null;
	}
	
	public boolean alreadyMooved(){
		return mooved;
	}
}
