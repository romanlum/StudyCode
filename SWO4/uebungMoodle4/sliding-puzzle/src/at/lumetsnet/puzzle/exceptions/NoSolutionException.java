package at.lumetsnet.puzzle.exceptions;

public class NoSolutionException extends RuntimeException {
	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 1L;

	public NoSolutionException(String message) {
		super(message);
	}
}
