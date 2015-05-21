package model;

import java.awt.Point;
import java.util.ArrayList;

public class King extends Piece {
	
	private boolean mooved;
	
	public King (String imagePath){
		super(imagePath);
		this.mooved = false;
	}
	
	public King (String imagePath, Point position){
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