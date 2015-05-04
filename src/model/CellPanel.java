package model;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import util.CellPanelMouseAdapter;
import util.ImageIconHandler;

public class CellPanel extends JPanel {
	
	public interface CellPanelListener {
		public void onSelect(CellPanel cellPanel);
	}

	private static final long serialVersionUID = -710890541389220734L;

	public static final int SIDE = 64;

	private String iconPath;
	private JLabel labelIcon;
	private Color defaultBackground;
	
	private CellPanelListener listener;

	public CellPanel() {
		addMouseListener(new CellPanelMouseAdapter(this));

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
	
	public void setCellPaneListener(CellPanelListener listener) {
		this.listener = listener;
	}

	public void notifyOnSelect() {
		if(this.listener != null) {
			this.listener.onSelect(this);
		}
	}
}
