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
	public ArrayList<Point> getPossiblePoints(){	
		ArrayList<Point> possiblePointsToGo = new ArrayList<Point>();
		int x = (int) this.getPosition().getX(), y = (int) this.getPosition().getY();
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++)
				if(i != 0 || j != 0)
					possiblePointsToGo.add(new Point(x + i, y + j));
			
		return possiblePointsToGo;
	}
	
	public boolean alreadyMooved(){
		return mooved;
	}
}