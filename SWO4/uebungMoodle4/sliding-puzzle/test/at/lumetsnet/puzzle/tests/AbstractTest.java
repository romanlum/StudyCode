package at.lumetsnet.puzzle.tests;

import static org.junit.Assert.assertTrue;
import at.lumetsnet.puzzle.Board;

public class AbstractTest {
	/***
	 * Creates a 3x3 testboard with empty tile on 2x2
	 * 
	 * @return board
	 */
	protected Board getTestBoard() {
		Board board = new Board(3);
		board.setTile(1, 1, 1);
		board.setTile(1, 2, 2);
		board.setTile(1, 3, 3);
		board.setTile(2, 1, 4);
		board.setEmptyTile(2, 2);
		board.setTile(2, 3, 6);
		board.setTile(3, 1, 7);
		board.setTile(3, 2, 8);
		board.setTile(3, 3, 5);

		assertTrue(board.isValid());
		return board;
	}
}
