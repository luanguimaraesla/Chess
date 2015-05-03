package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import util.CellPaneMouseAdapter;
import util.ImageIconHandler;

public class CellPane extends JPanel {
	
	public interface CellPaneListener {
		public void onSelect(CellPane cellPane);
	}

	private static final long serialVersionUID = -710890541389220734L;

	public static final int SIDE = 64;

	private String iconPath;
	private JLabel labelIcon;
	private Color defaultBackground;
	
	private CellPaneListener listener;

	public CellPane() {
		addMouseListener(new CellPaneMouseAdapter(this));

		this.iconPath = "";
		this.labelIcon = new JLabel();
		add(this.labelIcon);
	}

	public void setIcon(String iconPath) {
		this.iconPath = iconPath;
		this.labelIcon.setIcon(ImageIconHandler.load(iconPath));
	}
	
	public void removeIcon() {
		setIcon("");
	}
	
	public String getIconPath() {
		return this.iconPath;
	}
	
	public boolean haveIcon() {
		return !this.iconPath.isEmpty();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(SIDE, SIDE);
	}

	public Color getDefaultBackground() {
		return this.defaultBackground;
	}

	public void setDefaultBackground(Color defaultBackground) {
		this.defaultBackground = defaultBackground;
		setBackground(this.defaultBackground);
	}
	
	public void setCellPaneListener(CellPaneListener listener) {
		this.listener = listener;
	}

	public void notifyOnSelect() {
		if(this.listener != null) {
			this.listener.onSelect(this);
		}
	}
}
