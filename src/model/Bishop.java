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
	public ArrayList<Point> getPossiblePoints(){
		ArrayList<Point> possiblePointsToGo = new ArrayList<Point>();
		int x = (int)this.getPosition().getX();
		int y = (int)this.getPosition().getY();
		
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