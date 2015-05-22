package model;

import java.awt.Point;
import java.util.ArrayList;

public class Pawn extends Piece {
	
	private boolean mooved;
	
	public Pawn (String imagePath){
		super(imagePath);
		this.mooved = false;
	}
	
	public Pawn (String imagePath, Point position){
		super(imagePath, position);
		this.mooved = false;
	}
	
	@Override
	public ArrayList<Point> getPossiblePoints(){
		ArrayList<Point> possiblePointsToGo = new ArrayList<Point>();
		for (int i = -1; i <= 1; i++){
			possiblePointsToGo.add(new Point((int)this.getPosition().getX() + 1, (int)this.getPosition().getY() + i));
			possiblePointsToGo.add(new Point((int)this.getPosition().getX() - 1, (int)this.getPosition().getY() + i));
		}
		
		if (!alreadyMooved()){
			possiblePointsToGo.add(new Point ((int) this.getPosition().getX() + 2, (int) this.getPosition().getY()));
			possiblePointsToGo.add(new Point ((int) this.getPosition().getX() - 2, (int) this.getPosition().getY()));
		}
		
		return possiblePointsToGo;
	}
	
	@Override
	public void move(Point newPosition){
		setPosition(newPosition);
		this.mooved = true;
	}
	
	public boolean alreadyMooved(){
		return mooved;
	}
}
