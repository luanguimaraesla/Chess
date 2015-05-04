package model;

import java.awt.Color;
import java.awt.Point;

public class Square {

	public interface SquereListener {
		public void onHoverEvent(Square square);
		public void onSelectEvent(Square square);
	}

	public static final int DEFAULT_SIZE = 64;
	public static final String EMPTY_PATH = "";
	public static final SquereListener EMPTY_LISTENER = null;

	private Color color;
	private Integer size;
	private Point position;
	private String imagePath;
	
	private SquereListener squereListener;

	public Square(int x, int y, Color color) {
		this(x, y, color, EMPTY_PATH);
	}

	public Square(Point position, Color color) {
		this(position, color, EMPTY_PATH);
	}

	public Square(int x, int y, Color color, int size) {
		this(x, y, color, EMPTY_PATH, size);
	}

	public Square(Point position, Color color, int size) {
		this(position, color, EMPTY_PATH, size);
	}

	public Square(int x, int y, Color color, String imagePath) {
		this(new Point(x, y), color, imagePath, DEFAULT_SIZE);
	}

	public Square(Point position, Color color, String imagePath) {
		this(position, color, imagePath, DEFAULT_SIZE);
	}

	public Square(int x, int y, Color color, String imagePath, int size) {
		this(new Point(x, y), color, imagePath, size);
	}

	public Square(Point position, Color color, String imagePath, int size) {
		this.size = size;
		this.color = color;
		this.position = position;
		this.imagePath = imagePath;
		
		this.squereListener = EMPTY_LISTENER;
	}

	public void removeImage() {
		this.imagePath = EMPTY_PATH;
	}
	
	public void notifyOnHoverEvent() {
		if(haveSquereListener()) {
			this.squereListener.onHoverEvent(this);
		}
	}
	
	public void notifyOnSelectEvent() {
		if(haveSquereListener()) {
			this.squereListener.onSelectEvent(this);
		}
	}

	public Point getPosition() {
		return this.position;
	}
	
	public Integer getSize() {
		return this.size;
	}
	
	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	private boolean haveSquereListener() {
		return this.squereListener != EMPTY_LISTENER;
	}
}
