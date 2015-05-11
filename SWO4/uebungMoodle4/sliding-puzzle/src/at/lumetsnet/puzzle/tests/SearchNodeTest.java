package at.lumetsnet.puzzle.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import at.lumetsnet.puzzle.Board;
import at.lumetsnet.puzzle.SearchNode;
import at.lumetsnet.puzzle.exceptions.BoardException;

public class SearchNodeTest extends AbstractTest {

	@Test
	public void estimatedCostsToTargetTest() {
		Board board = getTestBoard();
		SearchNode node = new SearchNode(board);
		assertEquals(2, node.estimatedCostsToTarget());
	}

	@Test
	public void simpleNodeTest() {
		try {
			Board board = new Board(3);
			board.setTile(1, 1, 1);
			board.setTile(1, 2, 2);
			board.setTile(1, 3, 3);
			board.setTile(2, 1, 4);
			board.setTile(2, 2, 5);
			board.setTile(2, 3, 6);
			board.setTile(3, 1, 7);
			board.setTile(3, 2, 8);
			board.setTile(3, 3, 0);
			SearchNode node = new SearchNode(board);
			assertEquals(0, node.estimatedCostsToTarget());

			board = new Board(3);
			board.setTile(1, 1, 1);
			board.setTile(1, 2, 2);
			board.setTile(1, 3, 3);
			board.setTile(2, 1, 4);
			board.setTile(2, 2, 0);
			board.setTile(2, 3, 6);
			board.setTile(3, 1, 7);
			board.setTile(3, 2, 8);
			board.setTile(3, 3, 5);
			node = new SearchNode(board);
			assertEquals(2, node.estimatedCostsToTarget());

			board = new Board(3);
			board.setTile(1, 1, 1);
			board.setTile(1, 2, 0);
			board.setTile(1, 3, 3);
			board.setTile(2, 1, 4);
			board.setTile(2, 2, 5);
			board.setTile(2, 3, 6);
			board.setTile(3, 1, 7);
			board.setTile(3, 2, 8);
			board.setTile(3, 3, 2);
			node = new SearchNode(board);
			assertEquals(3, node.estimatedCostsToTarget());
		} catch (BoardException e) {
			fail("Unexpeced BoardException.");
		}
	}

}
