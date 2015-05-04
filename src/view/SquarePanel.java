package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Square;
import model.Square.SquareChangeListener;
import util.ImageHandler;
import control.CellControlPanel;
import control.SquareControl;

public class SquarePanel extends JPanel implements SquareChangeListener {

	private static final long serialVersionUID = 7332850110063699836L;

	private SquareControl squareControl;
	private HashMap<Square, JPanel> mapSquareGridPanel;
	private HashMap<Square, JLabel> mapSquareGridLabel;

	public SquarePanel() {
		setLayout(new GridBagLayout());

		this.mapSquareGridPanel = new HashMap<Square, JPanel>();
		this.mapSquareGridLabel = new HashMap<Square, JLabel>();
		
		initializeSquareControl();
		initializeGrid();
		initializePiecesInChess();
	}
	
	@Override
	public void onChangeImagePath(Square square) {
		this.mapSquareGridLabel.get(square).setIcon(ImageHandler.load(square.getImagePath()));
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
		for(int i = 0; i < this.squareControl.getSquareList().size(); i++) {
			square = this.squareControl.getSquareList().get(i);
			square.setSquareChangeListener(this);
			gridBag.gridx = square.getPosition().y;
			gridBag.gridy = square.getPosition().x;

			JPanel gridPanel = new JPanel();
			JLabel gridLabelIcon = new JLabel();

			gridPanel.setBackground(square.getColor());
			gridPanel.setPreferredSize(new Dimension(square.getSize(), square
					.getSize()));
			gridPanel.add(gridLabelIcon);
			
			add(gridPanel, gridBag);
			this.mapSquareGridPanel.put(square, gridPanel);
			this.mapSquareGridLabel.put(square, gridLabelIcon);
		}

	}

	private void initializePiecesInChess() {
		String pawnPath = "icon/Brown P_48x48.png";
		for (int i = 0; i < CellControlPanel.COL_NUMBER; i++) {
			this.squareControl.getSquare(0, i).setImagePath(pawnPath);
		}
	}
}
