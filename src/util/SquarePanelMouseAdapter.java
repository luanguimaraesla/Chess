package util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.SquarePanel;

public class SquarePanelMouseAdapter extends MouseAdapter {

	private SquarePanel squarePanel;
	
	public SquarePanelMouseAdapter(SquarePanel squarePanel) {
		this.squarePanel = squarePanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		this.squarePanel.getSquare().notifyOnSelectEvent();
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		this.squarePanel.getSquare().notifyOnHoverEvent();
	}

	@Override
	public void mouseExited(MouseEvent event) {
		this.squarePanel.getSquare().notifyOnOutEvent();
	}
}
