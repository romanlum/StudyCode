package at.lumetsnet.puzzle.exceptions;

public class InvalidBoardIndexException extends BoardException {

	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 1L;

	public InvalidBoardIndexException(String message) {
		super(message);
	}
}
