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
		ArrayList<Point> possiblePoints = new ArrayList<Point>();
		int x = (int)getPosition().getX();
		int y = (int)getPosition().getY();
		
		for(int i = 0; i < 8; i++){
			if(i != y)
				possiblePoints.add(new Point(x, i));
			if(i != x)
				possiblePoints.add(new Point(i, y));
		}
		
		return possiblePoints;
	}
	
	public boolean alreadyMooved(){
		return mooved;
	}
}