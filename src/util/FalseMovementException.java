package util;

public class FalseMovementException extends Exception{
	private static final long serialVersionUID = 4861916885742145456L;
	
	public FalseMovementException (){
		super("Sorry, bad square movement!");
	}
	
	public FalseMovementException (String message){
		super(message);
	}
}
