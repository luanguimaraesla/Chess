package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import control.CellControlPanel;

public class ChessView extends JFrame {

	private static final long serialVersionUID = -7774359147747995296L;

	private CellControlPanel cellControlPanel;

	public ChessView() {
		super("Chess");

		initializeCellControlPanel();
		initializeScreen();
		initializePiecesInChess();
	}

	public void initializeCellControlPanel() {
		Color colorOne = Color.WHITE;
		Color colorTwo = Color.GRAY;
		Color colorSelected = Color.GREEN;

		this.cellControlPanel = new CellControlPanel(colorOne, colorTwo,
				colorSelected);
	}

	public void initializePiecesInChess() {
		String pawnPath = "icon/Brown P_48x48.png";
		for (int i = 0; i < CellControlPanel.COL_NUMBER; i++) {
			this.cellControlPanel.getCellPane(0, i).setIcon(pawnPath);
		}
	}

	public void initializeScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(this.cellControlPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
