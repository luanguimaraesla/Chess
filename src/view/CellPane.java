package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.CellPaneMouseAdapter;

public class CellPane extends JPanel {

	private static final long serialVersionUID = -710890541389220734L;

	public static final int SIDE = 64;

	private JLabel labelIcon;
	private Color defaultBackground;

	public CellPane() {
		addMouseListener(new CellPaneMouseAdapter(this));
		
		this.labelIcon = new JLabel();
		add(this.labelIcon);
	}

	public void setIcon(String iconPath) {
		this.labelIcon.setIcon(new ImageIcon(iconPath));
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
		setBackground(this.defaultBackground);
	}
}
