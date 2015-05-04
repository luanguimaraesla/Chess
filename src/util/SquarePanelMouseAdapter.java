package util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Square;

public class SquarePanelMouseAdapter extends MouseAdapter {

	private Square square;
	
	public SquarePanelMouseAdapter(Square square) {
		this.square = square;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		this.square.notifyOnSelectEvent();
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		this.square.notifyOnHoverEvent();
	}

	@Override
	public void mouseExited(MouseEvent event) {
		this.square.notifyOnOutEvent();
	}
}
