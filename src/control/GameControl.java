package control;

import java.awt.Point;
import java.util.ArrayList;

import model.Piece;
import model.Team;
import model.King;
import model.Queen;
import model.Bishop;
import model.Horse;
import model.Tower;
import model.Pawn;

public class GameControl {
	
	private Team brownTeam;
	private Team whiteTeam;
	private SquareControl squareControl;
	
	public GameControl(SquareControl squareControl){
		this.squareControl = squareControl;
		TeamControl teamControler = new TeamControl();
		this.brownTeam = teamControler.createTeam(Team.TEAM_UP);
		this.whiteTeam = teamControler.createTeam(Team.TEAM_DOWN);
	}

	public boolean isMovementValid(Point point, Piece piece){
		if (getValidPieceMovements(piece).contains(point))
				return true;
		return false;
	}
	
	public ArrayList<Point> getValidPieceMovements(Piece piece){
		Team pieceTeam;
		Team rivalTeam;
		
		if(this.brownTeam.contains(piece)){
			pieceTeam = brownTeam;
			rivalTeam = whiteTeam;
		}
		else{
			pieceTeam = whiteTeam;
			rivalTeam = brownTeam;
		}
		
		if(piece instanceof Pawn){
			return validPawnDestinationPoints(piece, pieceTeam, rivalTeam);
		}else if(piece instanceof Tower){
			
		}else if(piece instanceof Horse){
			return validHorseDestinationPoints(piece, pieceTeam, rivalTeam);
		}else if(piece instanceof Bishop){
			
		}else if(piece instanceof Queen){
			
		}else if(piece instanceof King){
			
		}
		return null;
	}
	
	public ArrayList<Point> validHorseDestinationPoints(Piece piece, Team pieceTeam, Team rivalTeam){
		ArrayList<Point> validDestinationPoints = new ArrayList<Point>();
		
		for(Point eachPoint : piece.getPossiblePoints())
			if(!pieceTeam.ContainsPieceAt(eachPoint) && this.squareControl.isPointValid(eachPoint))
				validDestinationPoints.add(getSquareSamePosition(eachPoint));
		
		return validDestinationPoints;
	}
	
	public ArrayList<Point> validPawnDestinationPoints(Piece piece, Team pieceTeam, Team rivalTeam){
		ArrayList<Point> validDestinationPoints = new ArrayList<Point>();
		int invalidLineConstant, x, y;
			
		if(pieceTeam.getTeamOrientation() == Team.TEAM_DOWN)
			invalidLineConstant = 1;
		else invalidLineConstant = -1;
		
		for(Point eachPoint : piece.getPossiblePoints()){
			x = (int) eachPoint.getX();
			y = (int) eachPoint.getY();
			if(x != (int)(piece.getPosition().getX() + invalidLineConstant) && this.squareControl.isPointValid(eachPoint) && 
				x != (int)(piece.getPosition().getX() + 2 * invalidLineConstant)){
				if(rivalTeam.ContainsPieceAt(eachPoint) && y != piece.getPosition().getY())
					validDestinationPoints.add(getSquareSamePosition(eachPoint));
				else if(!rivalTeam.ContainsPieceAt(eachPoint) &&
						!pieceTeam.ContainsPieceAt(eachPoint) &&
						y == piece.getPosition().getY())
					validDestinationPoints.add(getSquareSamePosition(eachPoint));
			}
		}
		return validDestinationPoints;
	}
	
	private Point getSquareSamePosition(Point point){
		return this.squareControl.getSquare((int)point.getX(),
				   (int)point.getY()).getPosition();
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
