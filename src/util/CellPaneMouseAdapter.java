package util;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.CellPane;

public class CellPaneMouseAdapter extends MouseAdapter {
	
	public static final Color DEFAULT_COLOR_MOUSE_ENTERED = Color.BLUE;

	private CellPane cellPane;
	private Color colorMouseEntered;

	public CellPaneMouseAdapter(CellPane cellPane) {
		this.cellPane = cellPane;
		this.colorMouseEntered = DEFAULT_COLOR_MOUSE_ENTERED;
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		this.cellPane.notifyOnSelect();
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		this.cellPane.setDefaultBackground(this.cellPane.getBackground());
		this.cellPane.setBackground(this.colorMouseEntered);
	}

	@Override
	public void mouseExited(MouseEvent event) {
		this.cellPane.setBackground(this.cellPane.getDefaultBackground());
	}

	public Color getColorMouseEntered() {
		return colorMouseEntered;
	}

	public void setColorMouseEntered(Color colorMouseEntered) {
		this.colorMouseEntered = colorMouseEntered;
	}
}
