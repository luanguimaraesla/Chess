package control;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import model.Piece;
import model.Square;
import model.Square.SquareEventListener;

import util.FalseMovementException;

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
	private GameControl gameControl;

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
		square.setColor(getGridColor((int)(square.getPosition().getX()), (int)(square.getPosition().getY())));
	}

	@Override
	public void onHoverEvent(Square square) {
		square.setColor(this.colorHover);
	}

	@Override
	public void onSelectEvent(Square square){
		if (haveSelectedCellPanel()) {
			if (!this.selectedSquare.equals(square)){
				try{
					moveContentOfSelectedSquare(square);
				}catch(FalseMovementException ex){
					System.out.println("BAD MOVEMENT!");
					unselectSquare(square);
				}
			}else
				unselectSquare(square);	
		} else
			selectSquare(square);
	}

	@Override
	public void onOutEvent(Square square) {
		if (this.selectedSquare != square) {
			resetColor(square);
		} else {
			square.setColor(this.colorSelected);
		}
	}

	public Square getSquare(int row, int col){
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
		resetPosition(square);
		resetColor(square);
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

	private void moveContentOfSelectedSquare(Square square) throws FalseMovementException{
		Piece selectedPiece = selectedSquare.getPiece();
		
		if(!this.gameControl.isMovementValid(square.getPosition(), selectedPiece))
			throw new FalseMovementException();
		
		if(square.getPiece() != Square.NO_PIECE)
			this.gameControl.getPieceTeam(square.getPiece()).remove(square.getPiece());
		
		square.setPiece(this.selectedSquare.getPiece());
		square.getPiece().move(square.getPosition());
		this.selectedSquare.removePiece();
		unselectSquare(square);
	}

	public GameControl getGameControl() {
		return gameControl;
	}

	public void setGameControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}

	private void selectSquare(Square square) {
		if (square.havePiece()) {
			this.selectedSquare = square;
			this.selectedSquare.setColor(this.colorSelected);
		}
	}
	
	public boolean isPointValid(Point point){
		int x = (int) point.getX(), y = (int) point.getY();
		if(x < SquareControl.ROW_NUMBER && y < SquareControl.COL_NUMBER && x >= 0 &&  y >= 0)
			return true;
		return false;
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
