package model;

import java.awt.Point;
import java.util.ArrayList;

public class Queen extends Piece {
	
	public Queen (String imagePath){
		super(imagePath);
	}
	
	public Queen (String imagePath, Point position){
		super(imagePath, position);
	}
	
	@Override
	public ArrayList<Point> getPossiblePoints(){	
		return null;
	}
}