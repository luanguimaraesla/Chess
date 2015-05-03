package control;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.CellPanel;
import model.CellPanel.CellPanelListener;

public class CellControlPanel extends JPanel implements CellPanelListener {

	private static final long serialVersionUID = 4286502528939775085L;

	public static final int ROW_NUMBER = 8;
	public static final int COL_NUMBER = 8;

	public static final Color DEFAULT_COLOR_ONE = Color.WHITE;
	public static final Color DEFAULT_COLOR_TWO = Color.GRAY;
	public static final Color DEFAULT_COLOR_SELECTED = Color.GREEN;
	
	private Color colorOne;
	private Color colorTwo;
	private Color colorSelected;
	
	private CellPanel cellPanelSelected;
	private ArrayList<CellPanel> cellPanelList;
	
	public CellControlPanel() {
		this(DEFAULT_COLOR_ONE, DEFAULT_COLOR_TWO, DEFAULT_COLOR_SELECTED);
	}

	public CellControlPanel(Color colorOne, Color colorTwo, Color colorSelected) {
		this.colorOne = colorOne;
		this.colorTwo = colorTwo;
		this.colorSelected = colorSelected;
		
		this.cellPanelSelected = null;
		this.cellPanelList = new ArrayList<CellPanel>();
		
		setLayout(new GridBagLayout());
		createGridPane();
		
		getCellPane(1, 1).setIcon("icon/Brown K_48x48.png");
	}
	
	public CellPanel getCellPane(int row, int col) {
		return this.cellPanelList.get((row * COL_NUMBER) + col);
	}
	
	private void createGridPane() {
		GridBagConstraints gridBag = new GridBagConstraints();
		for (int row = 0; row < ROW_NUMBER; row++) {
			for (int col = 0; col < COL_NUMBER; col++) {
				gridBag.gridx = col;
				gridBag.gridy = row;

				CellPanel cellPanel = new CellPanel();
				Color cellPaneColor = getGridColor(row, col);

				cellPanel.setDefaultBackground(cellPaneColor);
				cellPanel.setCellPaneListener(this);
				add(cellPanel, gridBag);
				this.cellPanelList.add(cellPanel);
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
	
	private void resetColor(CellPanel cellPanel) {
		int index = this.cellPanelList.indexOf(cellPanel);
		int row = index / COL_NUMBER;
		int col = index % COL_NUMBER;
		
		cellPanel.setDefaultBackground(getGridColor(row, col));
	}

	@Override
	public void onSelect(CellPanel cellPanel) {
		if(this.cellPanelSelected == null) {
			markSelectedCellPanel(cellPanel);
		} else {
			moveSelectedCellPanel(cellPanel);
		}
	}
	
	private void markSelectedCellPanel(CellPanel cellPanel) {
		if(cellPanel.haveIcon()) {
			this.cellPanelSelected = cellPanel;
			this.cellPanelSelected.setDefaultBackground(this.colorSelected);
		}
	}
	
	private void moveSelectedCellPanel(CellPanel cellPanel) {
		cellPanel.setIcon(this.cellPanelSelected.getIconPath());
		resetColor(this.cellPanelSelected);
		this.cellPanelSelected.removeIcon();
		this.cellPanelSelected = null;
	}
}
