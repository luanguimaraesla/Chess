package control;

import java.awt.Point;

import model.Team;
import model.King;
import model.Queen;
import model.Bishop;
import model.Horse;
import model.Tower;
import model.Pawn;

public class TeamSetup {

	public Team createTeam(int teamOrientation){
		return teamOrientation == Team.TEAM_DOWN ? createTeamDown() : createTeamUp(); 
	}
	
	public Team createTeamUp(){
        Team upTeam = new Team(Team.TEAM_UP);
		
		String imagePath = "icon/Brown P_48x48.png";
		for(int i = 0; i < SquareControl.COL_NUMBER; i++)
			upTeam.add(new Pawn(imagePath, new Point(1, i)));
		
		imagePath = "icon/Brown R_48x48.png";
		upTeam.add(new Tower(imagePath, new Point(0, 0)));
		upTeam.add(new Tower(imagePath, new Point(0, 7)));
		
		imagePath = "icon/Brown N_48x48.png";
		upTeam.add(new Horse(imagePath, new Point(0, 1)));
		upTeam.add(new Horse(imagePath, new Point(0, 6)));
		
		imagePath = "icon/Brown B_48x48.png";
		upTeam.add(new Bishop(imagePath, new Point(0, 2)));
		upTeam.add(new Bishop(imagePath, new Point(0, 5)));
		
		imagePath = "icon/Brown Q_48x48.png";
		upTeam.add(new Queen(imagePath, new Point(0, 4)));

		imagePath = "icon/Brown K_48x48.png";
		upTeam.add(new King(imagePath, new Point(0, 3)));
		
		return upTeam;
	}
	
	public Team createTeamDown(){
        Team downTeam = new Team(Team.TEAM_DOWN);
		
		String imagePath = "icon/White P_48x48.png";
		for(int i = 0; i < SquareControl.COL_NUMBER; i++)
			downTeam.add(new Pawn(imagePath, new Point(6, i)));
		
		imagePath = "icon/White R_48x48.png";
		downTeam.add(new Tower(imagePath, new Point(7, 0)));
		downTeam.add(new Tower(imagePath, new Point(7, 7)));
		
		imagePath = "icon/White N_48x48.png";
		downTeam.add(new Horse(imagePath, new Point(7, 1)));
		downTeam.add(new Horse(imagePath, new Point(7, 6)));
		
		imagePath = "icon/White B_48x48.png";
		downTeam.add(new Bishop(imagePath, new Point(7, 2)));
		downTeam.add(new Bishop(imagePath, new Point(7, 5)));
		
		imagePath = "icon/White Q_48x48.png";
		downTeam.add(new Queen(imagePath, new Point(7, 4)));

		imagePath = "icon/White K_48x48.png";
		downTeam.add(new King(imagePath, new Point(7, 3)));
		
		return downTeam;
	}
	
}
