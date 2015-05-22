package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Piece;
import model.Square;
import control.SquareControl;
import control.GameControl;

public class SquareBoardPanel extends JPanel {

	private static final long serialVersionUID = 7332850110063699836L;

	private SquareControl squareControl;
	private ArrayList<SquarePanel> squarePanelList;
	private GameControl gameControl;

	public SquareBoardPanel() {
		setLayout(new GridBagLayout());
		this.squarePanelList = new ArrayList<SquarePanel>();
		initializeSquareControl();
		initializeGameControl();
		initializeGrid();
		initializePiecesInChess();
	}

	private void initializeSquareControl() {
		Color colorOne = Color.WHITE;
		Color colorTwo = Color.GRAY;
		Color colorHover = Color.BLUE;
		Color colorSelected = Color.GREEN;

		this.squareControl = new SquareControl(colorOne, colorTwo, colorHover,
				colorSelected);
	}
	
	private void initializeGameControl(){
		this.gameControl = new GameControl(this.squareControl);
		this.squareControl.setGameControl(this.gameControl);
	}

	private void initializeGrid() {
		GridBagConstraints gridBag = new GridBagConstraints();

		Square square;
		for (int i = 0; i < this.squareControl.getSquareList().size(); i++) {
			square = this.squareControl.getSquareList().get(i);
			gridBag.gridx = square.getPosition().y;
			gridBag.gridy = square.getPosition().x;

			SquarePanel squarePanel = new SquarePanel(square);

			add(squarePanel, gridBag);
			this.squarePanelList.add(squarePanel);
		}

	}

	private void initializePiecesInChess() {
		for (Piece eachPiece : this.gameControl.getBrownTeam()) {
			this.squareControl.getSquare((int)(eachPiece.getPosition().getX()),
										(int)(eachPiece.getPosition().getY())).setPiece(eachPiece);
			
			eachPiece.setPosition(this.squareControl.getSquare((int)(eachPiece.getPosition().getX()),
					(int)(eachPiece.getPosition().getY())).getPosition());
		}
		
		for (Piece eachPiece : this.gameControl.getWhiteTeam()) {
			this.squareControl.getSquare((int)(eachPiece.getPosition().getX()),
										(int)(eachPiece.getPosition().getY())).setPiece(eachPiece);
			
			eachPiece.setPosition(this.squareControl.getSquare((int)(eachPiece.getPosition().getX()),
					(int)(eachPiece.getPosition().getY())).getPosition());
		}
		
	}
}
