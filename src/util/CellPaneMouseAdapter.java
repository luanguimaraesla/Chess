package util;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.CellPane;

public class CellPaneMouseAdapter extends MouseAdapter {

	private CellPane cellPane;

	public CellPaneMouseAdapter(CellPane cellPane) {
		this.cellPane = cellPane;
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		this.cellPane.setDefaultBackground(this.cellPane.getBackground());
		this.cellPane.setBackground(Color.BLUE);
	}

	@Override
	public void mouseExited(MouseEvent event) {
		this.cellPane.setBackground(this.cellPane.getDefaultBackground());
	}
}
