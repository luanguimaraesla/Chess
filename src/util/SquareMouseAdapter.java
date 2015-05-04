package util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Square;

public class SquareMouseAdapter extends MouseAdapter {

	private Square square;
	
	public SquareMouseAdapter(Square square) {
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
