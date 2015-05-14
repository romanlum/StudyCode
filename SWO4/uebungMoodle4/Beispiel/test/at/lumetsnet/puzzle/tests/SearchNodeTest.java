package at.lumetsnet.puzzle.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import at.lumetsnet.puzzle.Board;
import at.lumetsnet.puzzle.Move;
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
	public void equalsNullTest() {
		Board board = getTestBoard();
		SearchNode node = new SearchNode(board);
		assertFalse(node.equals(null));
	}
	
	@Test
	public void equalsOtherObjectTest() {
		Board board = getTestBoard();
		SearchNode node = new SearchNode(board);
		assertFalse(node.equals("1"));
	}
	
	@Test
	public void equalsTest() {
		Board board = getTestBoard();
		SearchNode node = new SearchNode(board);
		
		Board copy = getTestBoard();
		SearchNode newNode = new SearchNode(copy);
		assertTrue(node.equals(newNode));
	}

	@Test
	public void compareToTest() {
		Board board = getTestBoard();
		SearchNode node = new SearchNode(board);
		
		Board copy = getTestBoard();
		SearchNode newNode = new SearchNode(copy);
		assertEquals(0, newNode.compareTo(node));
	}
	
	@Test
	public void toMovesTest() {
		Board board = getTestBoard();
		SearchNode node = new SearchNode(board,null, 0, null);
		
		board = (Board) board.clone();
		board.move(1, 2);
		SearchNode newNode =new SearchNode(board, node, 1, new Move(1,2));
		node = newNode;
		
		board = (Board) board.clone();
		board.move(1, 1);
		newNode =new SearchNode(board, node, 1, new Move(1,1));
		node = newNode;
		
		List<Move> moves = node.toMoves();
		assertEquals(2, moves.size());
		assertEquals(1, moves.get(0).getRow());
		assertEquals(2, moves.get(0).getCol());
		assertEquals(1, moves.get(1).getRow());
		assertEquals(1, moves.get(1).getCol());
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
