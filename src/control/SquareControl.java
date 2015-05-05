package control;

import java.awt.Color;
import java.util.ArrayList;

import model.Square;
import model.Square.SquareEventListener;

public class SquareControl implements SquareEventListener {

	public static final int ROW_NUMBER = 8;
	public static final int COL_NUMBER = 8;

	public static final Color DEFAULT_COLOR_ONE = Color.WHITE;
	public static final Color DEFAULT_COLOR_TWO = Color.GRAY;
	public static final Color DEFAULT_COLOR_HOVER = Color.BLUE;
	public static final Color DEFAULT_COLOR_SELECTED = Color.GREEN;

	public static final Square EMPTY_SQUARE = null;

	private Color colorOne;
	private Color colorTwo;
	private Color colorHover;
	private Color colorSelected;

	private Square selectedSquare;
	private ArrayList<Square> squareList;

	public SquareControl() {
		this(DEFAULT_COLOR_ONE, DEFAULT_COLOR_TWO, DEFAULT_COLOR_HOVER,
				DEFAULT_COLOR_SELECTED);
	}

	public SquareControl(Color colorOne, Color colorTwo, Color colorHover,
			Color colorSelected) {
		this.colorOne = colorOne;
		this.colorTwo = colorTwo;
		this.colorHover = colorHover;
		this.colorSelected = colorSelected;

		this.squareList = new ArrayList<>();
		createSquares();
	}

	public void resetColor(Square square) {
		int index = this.squareList.indexOf(square);
		int row = index / COL_NUMBER;
		int col = index % COL_NUMBER;

		square.setColor(getGridColor(row, col));
	}

	@Override
	public void onHoverEvent(Square square) {
		square.setColor(this.colorHover);
	}

	@Override
	public void onSelectEvent(Square square) {
		if (haveSelectedCellPanel()) {
			if (!this.selectedSquare.equals(square)) {
				moveContentOfSelectedSquare(square);
			} else {
				unselectSquare(square);
			}
		} else {
			selectSquare(square);
		}
	}

	@Override
	public void onOutEvent(Square square) {
		if (this.selectedSquare != square) {
			resetColor(square);
		} else {
			square.setColor(this.colorSelected);
		}
	}

	public Square getSquare(int row, int col) {
		return this.squareList.get((row * COL_NUMBER) + col);
	}

	public ArrayList<Square> getSquareList() {
		return this.squareList;
	}

	public Color getGridColor(int row, int col) {
		if ((row + col) % 2 == 0) {
			return this.colorOne;
		} else {
			return this.colorTwo;
		}
	}

	private void addSquare() {
		Square square = new Square();
		this.squareList.add(square);
		resetColor(square);
		resetPosition(square);
		square.setSquareEventListener(this);
	}

	private void resetPosition(Square square) {
		int index = this.squareList.indexOf(square);
		int row = index / COL_NUMBER;
		int col = index % COL_NUMBER;

		square.getPosition().setLocation(row, col);
	}

	private boolean haveSelectedCellPanel() {
		return this.selectedSquare != EMPTY_SQUARE;
	}

	private void moveContentOfSelectedSquare(Square square) {
		square.setImagePath(this.selectedSquare.getImagePath());
		this.selectedSquare.removeImage();
		unselectSquare(square);
	}

	private void selectSquare(Square square) {
		if (square.haveImagePath()) {
			this.selectedSquare = square;
			this.selectedSquare.setColor(this.colorSelected);
		}
	}

	private void unselectSquare(Square square) {
		resetColor(this.selectedSquare);
		this.selectedSquare = EMPTY_SQUARE;
	}

	private void createSquares() {
		for (int i = 0; i < (ROW_NUMBER * COL_NUMBER); i++) {
			addSquare();
		}
	}
}
