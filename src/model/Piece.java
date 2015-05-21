package model;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Piece {
	private String imagePath;
	private Point position;

	public Piece (String imagePath){
		this(imagePath, new Point(0, 0));
	}
	
	public Piece(String imagePath, Point position){
		setPosition(position);
		setImagePath(imagePath);
	}
	
	public abstract ArrayList<Point> getPossiblePoints(Point myPoint);

	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Point getPosition() {
		return position;
	}
	public void setPosition(Point position) {
		this.position = position;
	}
}
