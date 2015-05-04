package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = -6399886456682347905L;
	
	public GameFrame() {
		super("Chess");

		initializeScreen();
	}
	
	public void initializeScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(new SquareBoardPanel());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
