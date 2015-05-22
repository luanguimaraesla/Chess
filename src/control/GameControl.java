package control;

import model.Team;

public class GameControl {
	
	private Team brownTeam;
	private Team whiteTeam;
	
	public GameControl(){
		TeamControl teamControler = new TeamControl();
		this.brownTeam = teamControler.createTeam(Team.TEAM_UP);
		this.whiteTeam = teamControler.createTeam(Team.TEAM_DOWN);
	}

	public Team getBrownTeam() {
		return brownTeam;
	}

	public void setBrownTeam(Team brownTeam) {
		this.brownTeam = brownTeam;
	}

	public Team getWhiteTeam() {
		return whiteTeam;
	}

	public void setWhiteTeam(Team whiteTeam) {
		this.whiteTeam = whiteTeam;
	}
	
	
	
}
