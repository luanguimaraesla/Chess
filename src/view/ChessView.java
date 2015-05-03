package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ChessView extends JFrame {

	private static final long serialVersionUID = -7774359147747995296L;

    public ChessView() {
    	super("Chess");
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new ChessPane());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
