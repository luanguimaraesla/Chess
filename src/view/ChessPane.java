package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import view.CellPane.CellPaneListener;

public class ChessPane extends JPanel implements CellPaneListener {

	private static final long serialVersionUID = 4286502528939775085L;

	public static final int ROW_NUMBER = 8;
	public static final int COL_NUMBER = 8;

	public static final Color DEFAULT_COLOR_ONE = Color.WHITE;
	public static final Color DEFAULT_COLOR_TWO = Color.GRAY;
	
	private Color colorOne;
	private Color colorTwo;
	
	private CellPane cellPaneSelected;
	private ArrayList<CellPane> cellPaneList;
	
	public ChessPane() {
		this(DEFAULT_COLOR_ONE, DEFAULT_COLOR_TWO);
	}

	public ChessPane(Color colorOne, Color colorTwo) {
		this.colorOne = colorOne;
		this.colorTwo = colorTwo;
		
		this.cellPaneSelected = null;
		this.cellPaneList = new ArrayList<CellPane>();
		
		setLayout(new GridBagLayout());
		createGridPane();
		
		getCellPane(1, 1).setIcon("icon/Brown K_48x48.png");
	}
	
	public CellPane getCellPane(int row, int col) {
		return this.cellPaneList.get((row * COL_NUMBER) + col);
	}
	
	private void createGridPane() {
		GridBagConstraints gridBag = new GridBagConstraints();
		for (int row = 0; row < ROW_NUMBER; row++) {
			for (int col = 0; col < COL_NUMBER; col++) {
				gridBag.gridx = col;
				gridBag.gridy = row;

				CellPane cellPane = new CellPane();
				Color cellPaneColor = getGridColor(row, col);

				cellPane.setDefaultBackground(cellPaneColor);
				cellPane.setCellPaneListener(this);
				add(cellPane, gridBag);
				this.cellPaneList.add(cellPane);
			}
		}
	}
	
	private Color getGridColor(int row, int col) {
		if ((row + col) % 2 == 0) {
			return this.colorOne;
		} else {
			return this.colorTwo;
		}
	}
	
	private void resetColor(CellPane cellPane) {
		int index = this.cellPaneList.indexOf(cellPane);
		int row = index / COL_NUMBER;
		int col = index % COL_NUMBER;
		
		cellPane.setDefaultBackground(getGridColor(row, col));
	}

	@Override
	public void onSelect(CellPane cellPane) {
		if(this.cellPaneSelected == null) {
			if(cellPane.haveIcon()) {
				this.cellPaneSelected = cellPane;
				this.cellPaneSelected.setDefaultBackground(Color.GREEN);
			}
		} else {
			cellPane.setIcon(this.cellPaneSelected.getIconPath());
			resetColor(this.cellPaneSelected);
			this.cellPaneSelected.removeIcon();
			this.cellPaneSelected = null;
		}
	}
}
