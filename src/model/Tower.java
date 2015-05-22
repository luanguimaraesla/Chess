package model;

import java.awt.Point;
import java.util.ArrayList;

public class Tower extends Piece {
	
	private boolean mooved;
	
	public Tower (String imagePath){
		super(imagePath);
		this.mooved = false;
	}
	
	public Tower (String imagePath, Point position){
		super(imagePath, position);
		this.mooved = false;
	}
	
	@Override
	public ArrayList<Point> getPossiblePoints(){	
		return null;
	}
	
	public boolean alreadyMooved(){
		return mooved;
	}
}