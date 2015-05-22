package model;

import java.awt.Point;
import java.util.ArrayList;

public class Horse extends Piece {
	
	public Horse (String imagePath){
		super(imagePath);
	}
	
	public Horse (String imagePath, Point position){
		super(imagePath, position);
	}
	
	@Override
	public ArrayList<Point> getPossiblePoints(){	
		return null;
	}
}