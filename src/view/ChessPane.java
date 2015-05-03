package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class ChessPane extends JPanel {

	private static final long serialVersionUID = 4286502528939775085L;

	public static final int ROW_NUMBER = 8;
	public static final int COL_NUMBER = 8;

	public static final Color COLOR_ONE = Color.WHITE;
	public static final Color COLOR_TWO = Color.GRAY;

	public ChessPane() {
		setLayout(new GridBagLayout());

		GridBagConstraints gridBag = new GridBagConstraints();
		for (int row = 0; row < ROW_NUMBER; row++) {
			for (int col = 0; col < COL_NUMBER; col++) {
				gridBag.gridx = col;
				gridBag.gridy = row;

				CellPane cellPane = new CellPane();
				Border border = null;

				Color borderColor = Color.GRAY;
				Color cellPaneColor = (row + col) % 2 == 0 ? COLOR_ONE
						: COLOR_TWO;

				if (row < (ROW_NUMBER - 1)) {
					if (col < (COL_NUMBER - 1)) {
						border = new MatteBorder(1, 1, 0, 0, borderColor);
					} else {
						border = new MatteBorder(1, 1, 0, 1, borderColor);
					}
				} else {
					if (col < (COL_NUMBER - 1)) {
						border = new MatteBorder(1, 1, 1, 0, borderColor);
					} else {
						border = new MatteBorder(1, 1, 1, 1, borderColor);
					}
				}

				cellPane.setIcon("icon/White K_48x48.png");
				cellPane.setDefaultBackground(cellPaneColor);
				cellPane.setBorder(border);
				add(cellPane, gridBag);
			}
		}
	}
}
