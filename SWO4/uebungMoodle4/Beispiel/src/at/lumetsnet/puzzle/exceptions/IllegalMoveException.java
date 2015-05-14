package at.lumetsnet.puzzle.exceptions;

public class IllegalMoveException extends BoardException {
	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 1L;

	public IllegalMoveException(String message) {
		super(message);
	}
}
