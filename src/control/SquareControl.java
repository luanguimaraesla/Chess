package control;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import model.Piece;
import model.King;
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
	public static final Color DEFAULT_COLOR_CHECKMAT = Color.RED;
	public static final Color DEFAULT_COLOR_POSSIBLE_MOVEMENTS = Color.YELLOW;

	public static final Square EMPTY_SQUARE = null;

	private Color colorOne;
	private Color colorTwo;
	private Color colorHover;
	private Color colorSelected;
	private Color colorCheckmat;
	private Color colorPossibleMovements;

	private Square selectedSquare;
	private ArrayList<Square> squareList;
	private ArrayList<Square> possiblePieceDestinationSquareList;
	private GameControl gameControl;

	public SquareControl() {
		this(DEFAULT_COLOR_ONE, DEFAULT_COLOR_TWO, DEFAULT_COLOR_HOVER,
				DEFAULT_COLOR_SELECTED, DEFAULT_COLOR_CHECKMAT,
				DEFAULT_COLOR_POSSIBLE_MOVEMENTS);
	}

	public SquareControl(Color colorOne, Color colorTwo, Color colorHover,
			Color colorSelected, Color colorCheckmat, Color colorPossibleMovements) {
		this.colorOne = colorOne;
		this.colorTwo = colorTwo;
		this.colorHover = colorHover;
		this.colorSelected = colorSelected;
		this.colorCheckmat = colorCheckmat;
		this.colorPossibleMovements = colorPossibleMovements;
		
		this.squareList = new ArrayList<>();
		this.possiblePieceDestinationSquareList = new ArrayList<Square>();
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
					resetColorPiecePossibleMovements(selectedSquare.getPiece());
					unselectSquare(square);
				}
			}else{
				unselectSquare(square);
			}
		} else{
			selectSquare(square);
		}
	}

	@Override
	public void onOutEvent(Square square) {	
		if (this.possiblePieceDestinationSquareList.contains(square))
			square.setColor(this.colorPossibleMovements);
		else if (square.havePiece() && square.getPiece() instanceof King)
			colorSquareIfCheckmat(square.getPiece());
		else if (this.selectedSquare != square) 
			resetColor(square);
		else 
			square.setColor(this.colorSelected);
		
	}

	public Square getSquare(int row, int col){
		return this.squareList.get((row * COL_NUMBER) + col);
	}
	
	public Square getSquare(Piece piece){
		return getSquare((int)piece.getPosition().getX(), (int)piece.getPosition().getY());
	}
	
	public Square getSquare(Point point){
		return getSquare((int) point.getX(), (int) point.getY());
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
		
		resetColorPiecePossibleMovements(selectedPiece);
		
		if(square.getPiece() != Square.NO_PIECE)
			this.gameControl.getPieceTeam(square.getPiece()).kill(square.getPiece());
		
		square.setPiece(selectedPiece);
		square.getPiece().move(square.getPosition());
		this.selectedSquare.removePiece();
		unselectSquare(square);
		
		if(gameControl.getRivalTeam(selectedPiece).stillKingAlive()){
			colorSquareIfCheckmat(selectedPiece);
			this.gameControl.changeTurnTeam();
		}else{
			this.gameControl.setGameOver(true);
		}
	}
	
	public void colorSquareIfCheckmat(Piece selectedPiece){
		if(this.gameControl.checkIsOnCheckmat(this.gameControl.getPieceTeam(selectedPiece)))
			getSquare(this.gameControl.getPieceTeam(selectedPiece).getKing()).setColor(this.colorCheckmat);
		else
			resetColor(getSquare(this.gameControl.getPieceTeam(selectedPiece).getKing()));
		if(this.gameControl.checkIsOnCheckmat(this.gameControl.getRivalTeam(selectedPiece)))
			getSquare(this.gameControl.getRivalTeam(selectedPiece).getKing()).setColor(this.colorCheckmat);
		else
			resetColor(getSquare(this.gameControl.getRivalTeam(selectedPiece).getKing()));
	}

	public GameControl getGameControl() {
		return gameControl;
	}

	public void setGameControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}

	private void selectSquare(Square square){
		if(!gameControl.isGameOver()){
			if(this.selectedSquare != EMPTY_SQUARE && this.selectedSquare.havePiece())
				resetColorPiecePossibleMovements(selectedSquare.getPiece());
			
			if (square.havePiece() && gameControl.isTurnOfTheTeam(gameControl.getPieceTeam(square.getPiece()))) {
				this.selectedSquare = square;
				this.selectedSquare.setColor(this.colorSelected);
				colorPiecePossibleMovements(square.getPiece());
			}
		}
	}
	
	public void colorPiecePossibleMovements(Piece piece){
		resetColorPiecePossibleMovements(piece);
		for(Point eachPoint : this.gameControl.getValidPieceMovements(piece)){
			getSquare(eachPoint).setColor(this.colorPossibleMovements);
			this.possiblePieceDestinationSquareList.add(getSquare(eachPoint));
		}
	}
	
	public void resetColorPiecePossibleMovements(Piece piece){
		for(Point eachPoint : this.gameControl.getValidPieceMovements(piece)){
			resetColor(getSquare(eachPoint));
			this.possiblePieceDestinationSquareList.remove(getSquare(eachPoint));
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
