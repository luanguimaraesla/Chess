package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;

import javax.swing.JPanel;

import util.CellPaneMouseAdapter;

public class CellPane extends JPanel {

	private static final long serialVersionUID = -710890541389220734L;

	public static final int SIDE = 50;

	private Label labelText;
	private Color defaultBackground;

	public CellPane() {
		this.labelText = new Label();
		addMouseListener(new CellPaneMouseAdapter(this));
	}

	public void setText(String text) {
		if (!text.isEmpty()) {
			this.labelText.setText(text);
			add(this.labelText);
		} else {
			remove(this.labelText);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(SIDE, SIDE);
	}

	public Color getDefaultBackground() {
		return defaultBackground;
	}

	public void setDefaultBackground(Color defaultBackground) {
		this.defaultBackground = defaultBackground;
	}
}
