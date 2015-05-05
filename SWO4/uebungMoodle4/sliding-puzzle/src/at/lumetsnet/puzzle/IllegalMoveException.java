package at.lumetsnet.puzzle;

public class IllegalMoveException extends RuntimeException {
	public IllegalMoveException (String message) {
		super(message);
	}
}
