package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Piece;
import model.Square;
import control.SquareControl;

public class SquareBoardPanel extends JPanel {

	private static final long serialVersionUID = 7332850110063699836L;

	private SquareControl squareControl;
	private ArrayList<SquarePanel> squarePanelList;

	public SquareBoardPanel() {
		setLayout(new GridBagLayout());
		this.squarePanelList = new ArrayList<SquarePanel>();

		initializeSquareControl();
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
		String piecePath = "icon/Brown P_48x48.png";
		for (int i = 0; i < SquareControl.COL_NUMBER; i++) {
			this.squareControl.getSquare(1, i).setPiece(new Piece(Piece.PAWN, piecePath));
		}
		
		piecePath = "icon/Brown R_48x48.png";
		this.squareControl.getSquare(0, 0).setPiece(new Piece(Piece.TOWER, piecePath));
		this.squareControl.getSquare(0, 7).setPiece(new Piece(Piece.TOWER, piecePath));

		piecePath = "icon/Brown N_48x48.png";
		this.squareControl.getSquare(0, 1).setPiece(new Piece(Piece.HORSE, piecePath));
		this.squareControl.getSquare(0, 6).setPiece(new Piece(Piece.HORSE, piecePath));

		piecePath = "icon/Brown B_48x48.png";
		this.squareControl.getSquare(0, 2).setPiece(new Piece(Piece.BISHOP, piecePath));
		this.squareControl.getSquare(0, 5).setPiece(new Piece(Piece.BISHOP, piecePath));

		piecePath = "icon/Brown Q_48x48.png";
		this.squareControl.getSquare(0, 4).setPiece(new Piece(Piece.QUEEN, piecePath));

		piecePath = "icon/Brown K_48x48.png";
		this.squareControl.getSquare(0, 3).setPiece(new Piece(Piece.KING, piecePath));

		
		piecePath = "icon/White P_48x48.png";
		for (int i = 0; i < SquareControl.COL_NUMBER; i++) {
			this.squareControl.getSquare(6, i).setPiece(new Piece(Piece.PAWN, piecePath));
		}
		
		piecePath = "icon/White R_48x48.png";
		this.squareControl.getSquare(7, 0).setPiece(new Piece(Piece.TOWER, piecePath));
		this.squareControl.getSquare(7, 7).setPiece(new Piece(Piece.TOWER, piecePath));

		piecePath = "icon/White N_48x48.png";
		this.squareControl.getSquare(7, 1).setPiece(new Piece(Piece.HORSE, piecePath));;
		this.squareControl.getSquare(7, 6).setPiece(new Piece(Piece.HORSE, piecePath));

		piecePath = "icon/White B_48x48.png";
		this.squareControl.getSquare(7, 2).setPiece(new Piece(Piece.BISHOP, piecePath));
		this.squareControl.getSquare(7, 5).setPiece(new Piece(Piece.BISHOP, piecePath));

		piecePath = "icon/White Q_48x48.png";
		this.squareControl.getSquare(7, 4).setPiece(new Piece(Piece.QUEEN, piecePath));

		piecePath = "icon/White K_48x48.png";
		this.squareControl.getSquare(7, 3).setPiece(new Piece(Piece.KING, piecePath));
	}
}
