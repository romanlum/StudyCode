package at.lumetsnet.puzzle.exceptions;

public class InvalidTileNumberException extends BoardException {

	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTileNumberException(String message) {
		super(message);
	}
}
