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
		ArrayList<Point> possiblePointsToGo = new ArrayList<Point>();
		int x = (int)getPosition().getX();
		int y = (int)getPosition().getY();
		
		for(int i = 0; i < 8; i++){
			if(i != y)
				possiblePointsToGo.add(new Point(x, i));
			if(i != x)
				possiblePointsToGo.add(new Point(i, y));
		}
		
		for(int i = 1; i < 8; i++){
			if(x + i < 8 && y + i < 8)
				possiblePointsToGo.add(new Point(x + i, y + i));
			if(x - i >= 0 && y + i < 8)
				possiblePointsToGo.add(new Point(x - i, y + i));
			if(x + i < 8 && y - i >= 0)
				possiblePointsToGo.add(new Point(x + i, y - i));
			if(x - i >= 0 && y - i >= 0)
				possiblePointsToGo.add(new Point(x - i, y - i));
		}
		
		return possiblePointsToGo;
	}
}