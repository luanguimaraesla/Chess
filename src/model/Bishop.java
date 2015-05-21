package model;

import java.awt.Point;
import java.util.ArrayList;

public class Bishop extends Piece {
	
	public Bishop (String imagePath){
		super(imagePath);
	}
	
	public Bishop (String imagePath, Point position){
		super(imagePath, position);
	}
	
	@Override
	public ArrayList<Point> getPossiblePoints(Point myPoint){	
		return null;
	}
}