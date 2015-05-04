package chess;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import view.GameFrame;

public class Main {

	private static final String SCREEN_TYPE = "Nimbus";
	
	public static void main(String[] args) {
		initializeScreen();
	}

	private static void initializeScreen() {
		defineScreenType();
		startScreenThread();
	}

	private static void defineScreenType() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if (SCREEN_TYPE.equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
	}

	private static void startScreenThread() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GameFrame().setVisible(true);
			}
		});
	}

}
