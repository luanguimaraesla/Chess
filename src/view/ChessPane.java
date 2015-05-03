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

	public ChessPane() {
        setLayout(new GridBagLayout());

        GridBagConstraints gridBag = new GridBagConstraints();
        for (int row = 0; row < ROW_NUMBER; row++) {
            for (int col = 0; col < COL_NUMBER; col++) {
                gridBag.gridx = col;
                gridBag.gridy = row;

                CellPane cellPane = new CellPane();
                Border border = null;
                if (row < (ROW_NUMBER - 1)) {
                    if (col < (COL_NUMBER - 1)) {
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if (col < (COL_NUMBER - 1)) {
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
                cellPane.setText("Torre");
                cellPane.setText("");
                cellPane.setBorder(border);
                add(cellPane, gridBag);
            }
        }
    }
}
