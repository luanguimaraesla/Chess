package view;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Square;
import model.Square.SquareChangeListener;
import util.ImageHandler;
import util.SquarePanelMouseAdapter;

public class SquarePanel extends JPanel implements SquareChangeListener {

	private static final long serialVersionUID = -4103462554324663298L;

	public static final Icon EMPTY_ICON = null;
	public static final Square EMPTY_SQUARE = null;

	private Square square;
	private JLabel labelIcon;

	public SquarePanel() {
		this.square = EMPTY_SQUARE;
		this.labelIcon = new JLabel();

		add(this.labelIcon);
	}

	public void setSquare(Square square) {
		this.square = square;
		this.square.setSquareChangeListener(this);
		this.labelIcon.setIcon(ImageHandler.load(square.getImagePath()));

		addMouseListener(new SquarePanelMouseAdapter(this.square));
	}

	@Override
	public void onChangeImagePath(Square square) {
		this.labelIcon.setIcon(ImageHandler.load(square.getImagePath()));
	}

	@Override
	public void onColorChange(Square square) {
		this.setBackground(square.getColor());
	}

	public Square getSquare() {
		return this.square;
	}
}
