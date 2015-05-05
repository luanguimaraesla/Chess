package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

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
		String peacePath = "icon/Brown P_48x48.png";
		for (int i = 0; i < SquareControl.COL_NUMBER; i++) {
			this.squareControl.getSquare(1, i).setImagePath(peacePath);
		}
		peacePath = "icon/Brown R_48x48.png";
		this.squareControl.getSquare(0, 0).setImagePath(peacePath);
		this.squareControl.getSquare(0, 7).setImagePath(peacePath);

		peacePath = "icon/Brown N_48x48.png";
		this.squareControl.getSquare(0, 1).setImagePath(peacePath);
		this.squareControl.getSquare(0, 6).setImagePath(peacePath);

		peacePath = "icon/Brown B_48x48.png";
		this.squareControl.getSquare(0, 2).setImagePath(peacePath);
		this.squareControl.getSquare(0, 5).setImagePath(peacePath);

		peacePath = "icon/Brown Q_48x48.png";
		this.squareControl.getSquare(0, 4).setImagePath(peacePath);

		peacePath = "icon/Brown K_48x48.png";
		this.squareControl.getSquare(0, 3).setImagePath(peacePath);

		
		peacePath = "icon/White P_48x48.png";
		for (int i = 0; i < SquareControl.COL_NUMBER; i++) {
			this.squareControl.getSquare(6, i).setImagePath(peacePath);
		}
		
		peacePath = "icon/White R_48x48.png";
		this.squareControl.getSquare(7, 0).setImagePath(peacePath);
		this.squareControl.getSquare(7, 7).setImagePath(peacePath);

		peacePath = "icon/White N_48x48.png";
		this.squareControl.getSquare(7, 1).setImagePath(peacePath);
		this.squareControl.getSquare(7, 6).setImagePath(peacePath);

		peacePath = "icon/White B_48x48.png";
		this.squareControl.getSquare(7, 2).setImagePath(peacePath);
		this.squareControl.getSquare(7, 5).setImagePath(peacePath);

		peacePath = "icon/White Q_48x48.png";
		this.squareControl.getSquare(7, 4).setImagePath(peacePath);

		peacePath = "icon/White K_48x48.png";
		this.squareControl.getSquare(7, 3).setImagePath(peacePath);
	}
}
