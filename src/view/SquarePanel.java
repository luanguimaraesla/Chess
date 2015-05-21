package view;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Square;
import model.Square.SquareChangeListener;
import util.ImageHandler;
import util.SquareMouseAdapter;

public class SquarePanel extends JPanel implements SquareChangeListener {

	private static final long serialVersionUID = -4103462554324663298L;

	public static final Icon EMPTY_ICON = null;
	public static final Square EMPTY_SQUARE = null;

	private Square square;
	private JLabel labelIcon;
	private Dimension dimension;

	public SquarePanel(Square square) {
		this.labelIcon = new JLabel();
		add(this.labelIcon);
		
		setSquare(square);
	}

	public void setSquare(Square square) {
		this.square = square;
		this.square.setSquareChangeListener(this);
		
		this.labelIcon.setIcon(ImageHandler.load(square.havePiece() ? square.getPiece().getImagePath() : Square.NO_IMAGE_PATH));
		setBackground(square.getColor());

		this.dimension = new Dimension(this.square.getSize(), this.square.getSize());
		
		addMouseListener(new SquareMouseAdapter(this.square));
	}

	@Override
	public void onChangeImagePath(Square square) {
		this.labelIcon.setIcon(ImageHandler.load(square.havePiece() ? square.getPiece().getImagePath() : Square.NO_IMAGE_PATH));
	}

	@Override
	public void onColorChange(Square square) {
		setBackground(square.getColor());
	}

	@Override
	public Dimension getPreferredSize() {
		return this.dimension;
	}
	public Square getSquare() {
		return this.square;
	}
}
