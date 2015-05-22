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
		ArrayList<Point> possiblePointsToGo = new ArrayList<Point>();
		int x = (int)this.getPosition().getX();
		int y = (int)this.getPosition().getY();
		
		for(int i = -2; i <= 2; i++)
			for(int j = -2; j <= 2; j++)
				if(Math.abs(i) != Math.abs(j) && i != 0 && j != 0)
					possiblePointsToGo.add(new Point(i + x, j + y));
		
		return possiblePointsToGo;
	}
}