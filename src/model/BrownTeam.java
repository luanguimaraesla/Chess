package model;

import java.util.ArrayList;
import java.awt.Point;
import control.SquareControl;


public class BrownTeam extends Team{
	public BrownTeam(){
		super();
		setPieces(initPieces());
	}
	
	@Override
	public ArrayList<Piece> initPieces(){
		ArrayList<Piece> newTeam = new ArrayList<Piece>();
		
		String imagePath = "icon/Brown P_48x48.png";
		for(int i = 0; i < SquareControl.COL_NUMBER; i++)
			newTeam.add(new Pawn(imagePath, new Point(1, i)));
		
		imagePath = "icon/Brown R_48x48.png";
		newTeam.add(new Tower(imagePath, new Point(0, 0)));
		newTeam.add(new Tower(imagePath, new Point(0, 7)));
		
		imagePath = "icon/Brown N_48x48.png";
		newTeam.add(new Horse(imagePath, new Point(0, 1)));
		newTeam.add(new Horse(imagePath, new Point(0, 6)));
		
		imagePath = "icon/Brown B_48x48.png";
		newTeam.add(new Bishop(imagePath, new Point(0, 2)));
		newTeam.add(new Bishop(imagePath, new Point(0, 5)));
		
		imagePath = "icon/Brown Q_48x48.png";
		newTeam.add(new Queen(imagePath, new Point(0, 4)));

		imagePath = "icon/Brown K_48x48.png";
		newTeam.add(new King(imagePath, new Point(0, 3)));
		
		return newTeam;
	}
}
