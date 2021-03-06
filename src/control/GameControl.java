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
	
	private Team turnTeam;
	private Team brownTeam;
	private Team whiteTeam;
	private SquareControl squareControl;
	private boolean gameOver;
	
	public GameControl(SquareControl squareControl){
		this.squareControl = squareControl;
		TeamSetup teamSetup = new TeamSetup();
		this.brownTeam = teamSetup.createTeam(Team.TEAM_UP);
		this.turnTeam = this.whiteTeam = teamSetup.createTeam(Team.TEAM_DOWN);
		this.gameOver = false;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean isMovementValid(Point point, Piece piece){
		if (getValidPieceMovements(piece).contains(point))
				return true;
		return false;
	}
	
	public ArrayList<Point> getValidPieceMovements(Piece piece){
		Team pieceTeam = getPieceTeam(piece);
		Team rivalTeam = getRivalTeam(piece);
		
		if(piece instanceof Pawn){
			return validPawnDestinationPoints(piece, pieceTeam, rivalTeam);
		}else if(piece instanceof Tower){
			return validTowerDestinationPoints(piece, pieceTeam, rivalTeam);
		}else if(piece instanceof Horse){
			return validHorseDestinationPoints(piece, pieceTeam);
		}else if(piece instanceof Bishop){
			return validBishopDestinationPoints(piece, pieceTeam, rivalTeam);
		}else if(piece instanceof Queen){
			return validQueenDestinationPoints(piece, pieceTeam, rivalTeam);
		}else if(piece instanceof King){
			return validKingDestinationPoints(piece, pieceTeam, rivalTeam);
		}
		return null;
	}
	public ArrayList<Point> validKingDestinationPoints(Piece piece, Team pieceTeam, Team rivalTeam){
		return validQueenDestinationPoints(piece, pieceTeam, rivalTeam);
	}
	
	public ArrayList<Point> validQueenDestinationPoints(Piece piece, Team pieceTeam, Team rivalTeam){
		ArrayList<Point> validDestinationPoints = validBishopDestinationPoints(piece, pieceTeam, rivalTeam);
		for (Point eachPoint : validTowerDestinationPoints(piece, pieceTeam, rivalTeam))
			validDestinationPoints.add(eachPoint);
		
		return validDestinationPoints;
	}
	
	public ArrayList<Point> validBishopDestinationPoints(Piece piece, Team pieceTeam, Team rivalTeam){
		ArrayList<Point> validDestinationPoints = new ArrayList<Point>();
		ArrayList<Point> possiblePointsToGo = piece.getPossiblePoints();
		double x = piece.getPosition().getX(), y = piece.getPosition().getY();
		double limitNW, limitNE, limitSW, limitSE;
		limitNW = limitNE = -1;
		limitSW = limitSE = SquareControl.COL_NUMBER;
		
		for(Point eachPoint : possiblePointsToGo){
			if((rivalTeam.ContainsPieceAt(eachPoint) || pieceTeam.ContainsPieceAt(eachPoint))
			    && !(eachPoint.getX() == x || eachPoint.getY() == y)){			
				if(eachPoint.getX() < x && eachPoint.getY() < y && Math.abs(eachPoint.getX() - x) < Math.abs(limitNW - x))
					limitNW = rivalTeam.ContainsPieceAt(eachPoint) ? eachPoint.getX() - 1 : eachPoint.getX();
				else if(eachPoint.getX() < x && eachPoint.getY() > y && Math.abs(eachPoint.getX() - x) < Math.abs(limitNE - x))
					limitNE = rivalTeam.ContainsPieceAt(eachPoint) ? eachPoint.getX() - 1 : eachPoint.getX();
				if(eachPoint.getX() > x && eachPoint.getY() < y && Math.abs(eachPoint.getX() - x) < Math.abs(limitSW - x))
					limitSW = rivalTeam.ContainsPieceAt(eachPoint) ? eachPoint.getX() + 1 : eachPoint.getX();
				else if(eachPoint.getX() > x && eachPoint.getY() > y && Math.abs(eachPoint.getX() - x) < Math.abs(limitSE - x))
					limitSE = rivalTeam.ContainsPieceAt(eachPoint) ? eachPoint.getX() + 1 : eachPoint.getX();
			}
		}
		
		for(Point eachPoint : possiblePointsToGo){
			if(this.squareControl.isPointValid(eachPoint) && !(eachPoint.getX() == x || eachPoint.getY() == y)){
				if(eachPoint.getX() < x && eachPoint.getY() < y && (eachPoint.getX() > limitNW || limitNW == SquareControl.COL_NUMBER))
					validDestinationPoints.add(getSquareSamePosition(eachPoint));
				else if(eachPoint.getX() < x && eachPoint.getY() > y && (eachPoint.getX() > limitNE || limitNE == SquareControl.COL_NUMBER))
					validDestinationPoints.add(getSquareSamePosition(eachPoint));
				if(eachPoint.getX() > x && eachPoint.getY() < y && (eachPoint.getX() < limitSW || limitSW == SquareControl.COL_NUMBER))
					validDestinationPoints.add(getSquareSamePosition(eachPoint));
				else if(eachPoint.getX() > x && eachPoint.getY() > y && (eachPoint.getX() < limitSE || limitSE == SquareControl.COL_NUMBER))
					validDestinationPoints.add(getSquareSamePosition(eachPoint));
			}
		}
			
		return validDestinationPoints;
	}
	
	public ArrayList<Point> validTowerDestinationPoints(Piece tower, Team pieceTeam, Team rivalTeam){
		ArrayList<Point> validDestinationPoints = new ArrayList<Point>();
		ArrayList<Point> possiblePointsToGo = tower.getPossiblePoints();
		double x = tower.getPosition().getX();
		double y = tower.getPosition().getY();
		double limitUp, limitDown, limitLeft, limitRight;
		limitDown = limitRight = SquareControl.COL_NUMBER;
		limitUp = limitLeft = -1;
		
		for(Point eachPoint : possiblePointsToGo){
			if((rivalTeam.ContainsPieceAt(eachPoint) || pieceTeam.ContainsPieceAt(eachPoint)) && 
					(eachPoint.getX() == x || eachPoint.getY() == y)){				
				if(eachPoint.getX() < x && eachPoint.getX() > limitUp)
					limitUp = rivalTeam.ContainsPieceAt(eachPoint) ? eachPoint.getX() - 1 : eachPoint.getX();
				else if(eachPoint.getX() > x && eachPoint.getX() < limitDown)
					limitDown = rivalTeam.ContainsPieceAt(eachPoint) ? eachPoint.getX() + 1 : eachPoint.getX();
				if(eachPoint.getY() < y && eachPoint.getY() > limitLeft)
					limitLeft = rivalTeam.ContainsPieceAt(eachPoint) ? eachPoint.getY() - 1 : eachPoint.getY();
				else if(eachPoint.getY() > y && eachPoint.getY() < limitRight)
					limitRight = rivalTeam.ContainsPieceAt(eachPoint) ? eachPoint.getY() + 1 : eachPoint.getY();
			}
		}
		
		for(Point eachPoint : possiblePointsToGo){
			if(this.squareControl.isPointValid(eachPoint) && (eachPoint.getX() == x || eachPoint.getY() == y)){
				if(eachPoint.getX() < x && eachPoint.getX() > limitUp)
					validDestinationPoints.add(getSquareSamePosition(eachPoint));
				else if(eachPoint.getX() > x && eachPoint.getX() < limitDown)
					validDestinationPoints.add(getSquareSamePosition(eachPoint));
				if(eachPoint.getY() < y && eachPoint.getY() > limitLeft)
					validDestinationPoints.add(getSquareSamePosition(eachPoint));
				else if(eachPoint.getY() > y && eachPoint.getY() < limitRight)
					validDestinationPoints.add(getSquareSamePosition(eachPoint));
			}
		}
		
		return validDestinationPoints;
	}
	
	public ArrayList<Point> validHorseDestinationPoints(Piece piece, Team pieceTeam){
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
			if(x != (int)(piece.getPosition().getX() + invalidLineConstant) && 
				this.squareControl.isPointValid(eachPoint) && 
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
	
	public boolean checkIsOnCheckmat(Piece king){
		for(Piece eachRivalPiece : getRivalTeam(king))
			if(getValidPieceMovements(eachRivalPiece).contains(king.getPosition()))
				return true;
		return false;
	}
	
	public boolean checkIsOnCheckmat(Team team){
		return checkIsOnCheckmat(team.getKing());
	}
	
	public Team getPieceTeam(Piece piece){
		if(this.brownTeam.contains(piece)) return this.brownTeam;
		else return this.whiteTeam;
	}
	
	public Team getRivalTeam(Piece piece){
		if(getPieceTeam(piece) == this.brownTeam)
			return this.whiteTeam;
		return this.brownTeam;
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

	public Team getTurnTeam() {
		return turnTeam;
	}

	public void changeTurnTeam() {
		if(this.turnTeam == this.whiteTeam)
			this.turnTeam = this.brownTeam;
		else
			this.turnTeam = this.whiteTeam;
	}
	
	public boolean isTurnOfTheTeam(Team team){
		return team == getTurnTeam();
	}
}
