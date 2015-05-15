package at.lumetsnet.puzzle.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import at.lumetsnet.puzzle.Board;
import at.lumetsnet.puzzle.Move;
import at.lumetsnet.puzzle.exceptions.BoardException;
import at.lumetsnet.puzzle.exceptions.IllegalMoveException;

public class BoardTest extends AbstractTest {

	@Test
	public void getTileTest() {
		Board board = getTestBoard();
		assertEquals(0, board.getTile(2, 2));
		assertEquals(1, board.getTile(1, 1));
	}

	@Test
	public void setTileTest() {
		Board board = new Board(3);
		// default empty tile
		assertEquals(1, board.getTile(1, 1));
		board.setTile(1, 1, 2);
		assertEquals(2, board.getTile(1, 1));
	}

	@Test
	public void getEmptyTileTest() {
		Board board = getTestBoard();
		assertEquals(2, board.getEmptyTileColumn());
		assertEquals(2, board.getEmptyTileRow());
	}

	@Test
	public void sizeTest() {
		Board board = getTestBoard();
		assertEquals(3, board.size());
		board = new Board(9);
		assertEquals(9, board.size());
	}

	@Test
	public void simpleIsValidTest() {
		Board board;
		try {
			board = getTestBoard();
			assertTrue(board.isValid());
		} catch (BoardException e) {
			fail("BoardException not expected.");
		}
	}

	@Test
	public void simpleIsNotValidTest() {
		Board board;
		try {
			board = new Board(3);
			board.setTile(1, 1, 1);
			board.setTile(1, 2, 2);
			board.setTile(1, 3, 3);
			board.setTile(2, 1, 4);
			board.setTile(2, 2, 5);
			board.setTile(2, 3, 6);
			board.setTile(3, 1, 7);
			board.setTile(3, 2, 1);
			board.setTile(3, 3, 0);

			assertTrue(!board.isValid());
		} catch (BoardException e) {
			fail("BoardException not expected.");
		}
	}

	@Test
	public void simpleIsNotValidTest2() {
		Board board;
		try {
			board = new Board(3);
			board.setTile(1, 1, 8);
			board.setTile(1, 2, 2);
			board.setTile(1, 3, 0);
			board.setTile(2, 1, 7);
			board.setTile(2, 2, 5);
			board.setTile(2, 3, 4);
			board.setTile(3, 1, 3);
			board.setTile(3, 2, 1);
			board.setTile(3, 3, 6);

			assertTrue(board.isValid());
		} catch (BoardException e) {
			fail("BoardException not expected.");
		}
	}

	@Test
	public void simpleIsNotValidTest3() {
		Board board;
		try {
			board = new Board(3);
			board.setTile(1, 1, 8);
			board.setTile(1, 2, 2);
			// not all tiles set
			assertFalse(board.isValid());
		} catch (BoardException e) {
			fail("BoardException not expected.");
		}
	}

	@Test
	public void simpleIsNotValidTest4() {
		Board board;
		try {
			board = new Board(3);
			board.setTile(1, 1, 8);
			board.setTile(1, 2, 2);
			board.setTile(1, 3, 0);
			board.setTile(2, 1, 7);
			board.setTile(2, 2, 5);
			board.setTile(2, 3, 4);
			board.setTile(3, 1, 3);
			board.setTile(3, 2, 1);
			board.setTile(3, 3, 8); // invalid entry

			assertFalse(board.isValid());
		} catch (BoardException e) {
			fail("BoardException not expected.");
		}
	}

	@Test
	public void moveOutsideTest() {
		Board board = getTestBoard();
		boolean hadExeption = false;
		try {
			board.move(1, 0);
		} catch (IllegalMoveException ex) {
			hadExeption = true;
		}
		assertTrue(hadExeption);
		hadExeption = false;
		try {
			board.move(0, 1);
		} catch (IllegalMoveException ex) {
			hadExeption = true;
		}
		assertTrue(hadExeption);
		hadExeption = false;
		try {
			board.move(4, 1);
		} catch (IllegalMoveException ex) {
			hadExeption = true;
		}
		assertTrue(hadExeption);
		hadExeption = false;
		try {
			board.move(1, 4);
		} catch (IllegalMoveException ex) {
			hadExeption = true;
		}
		assertTrue(hadExeption);
		hadExeption = false;

	}

	@Test
	public void illegalMoveTest() {
		Board board = getTestBoard();
		boolean hadExeption = false;
		try {
			board.move(1, 3);
		} catch (IllegalMoveException ex) {
			hadExeption = true;
		}
		assertTrue(hadExeption);
		hadExeption = false;

		try {
			board.move(3, 1);
		} catch (IllegalMoveException ex) {
			hadExeption = true;
		}
		assertTrue(hadExeption);
		hadExeption = false;
		try {
			board.move(2, 2);
		} catch (IllegalMoveException ex) {
			hadExeption = true;
		}
		assertTrue(hadExeption);
		hadExeption = false;
	}

	@Test
	public void allowdMovesTest() {
		Board board = getTestBoard();
		board.move(2, 3);
		assertTrue(board.isValid());
		assertEquals(2, board.getEmptyTileRow());
		assertEquals(3, board.getEmptyTileColumn());

		board = getTestBoard();
		board.move(3, 2);
		assertTrue(board.isValid());
		assertEquals(3, board.getEmptyTileRow());
		assertEquals(2, board.getEmptyTileColumn());

		board = getTestBoard();
		board.move(2, 1);
		assertTrue(board.isValid());
		assertEquals(2, board.getEmptyTileRow());
		assertEquals(1, board.getEmptyTileColumn());

		board = getTestBoard();
		board.move(2, 3);
		assertTrue(board.isValid());
		assertEquals(2, board.getEmptyTileRow());
		assertEquals(3, board.getEmptyTileColumn());
	}

	@Test
	public void moveLeftTest() {
		Board board = getTestBoard();
		board.moveLeft();
		assertTrue(board.isValid());
		assertEquals(2, board.getEmptyTileRow());
		assertEquals(1, board.getEmptyTileColumn());
	}

	@Test(expected = IllegalMoveException.class)
	public void moveLeftExceptionTest() {
		Board board = getTestBoard();
		board.moveLeft();
		board.moveLeft();
	}

	@Test
	public void moveRightTest() {
		Board board = getTestBoard();
		board.moveRight();
		assertTrue(board.isValid());
		assertEquals(2, board.getEmptyTileRow());
		assertEquals(3, board.getEmptyTileColumn());
	}

	@Test(expected = IllegalMoveException.class)
	public void moveRightExceptionTest() {
		Board board = getTestBoard();
		board.moveRight();
		board.moveRight();
	}

	@Test
	public void moveUpTest() {
		Board board = getTestBoard();
		board.moveUp();
		assertTrue(board.isValid());
		assertEquals(1, board.getEmptyTileRow());
		assertEquals(2, board.getEmptyTileColumn());
	}

	@Test(expected = IllegalMoveException.class)
	public void moveUpExceptionTest() {
		Board board = getTestBoard();
		board.moveUp();
		board.moveUp();
	}

	@Test
	public void moveDownTest() {
		Board board = getTestBoard();
		board.moveDown();
		assertTrue(board.isValid());
		assertEquals(3, board.getEmptyTileRow());
		assertEquals(2, board.getEmptyTileColumn());
	}

	@Test(expected = IllegalMoveException.class)
	public void moveDownExceptionTest() {
		Board board = getTestBoard();
		board.moveDown();
		board.moveDown();
	}

	@Test
	public void equalsTest() {
		Board testBoard = getTestBoard();
		Board sameBoard = getTestBoard();
		Board otherBoard = getTestBoard();
		otherBoard.moveDown();

		assertTrue(testBoard.equals(sameBoard));
		assertFalse(testBoard.equals(otherBoard));
		assertFalse(testBoard.equals(1));
	}

	@Test
	public void compareToTest() {
		Board testBoard = new Board(3);
		Board sameSizeBoard = new Board(3);
		Board biggerBoard = new Board(4);
		Board smallerBoard = new Board(2);
		assertTrue(testBoard.compareTo(sameSizeBoard) == 0);
		assertTrue(testBoard.compareTo(smallerBoard) == 1);
		assertTrue(testBoard.compareTo(biggerBoard) == -1);
	}

	@Test
	public void copyTest() {
		Board board = getTestBoard();
		Board copyBoard = board.copy();
		// change original board to check copy of references
		board.moveDown();
		Board originalBoard = getTestBoard();
		// check against original board
		assertTrue(copyBoard.equals(originalBoard));
		// check against changed board
		assertFalse(copyBoard.equals(board));
	}

	@Test
	public void shuffleTest() {
		Board board = getTestBoard();
		board.shuffle();
		assertTrue(board.isValid());
		// check that the board is not the same as the
		// original board
		assertFalse(board.equals(getTestBoard()));

	}

	@Test
	public void makeMovesTest() {
		Board board = getTestBoard();
		List<Move> moveList = new ArrayList<Move>();
		moveList.add(new Move(1, 2));
		moveList.add(new Move(1, 3));
		moveList.add(new Move(2, 3));
		moveList.add(new Move(3, 3));
		moveList.add(new Move(3, 2));
		board.makeMoves(moveList);
		assertTrue(board.isValid());
		assertEquals(3, board.getEmptyTileRow());
		assertEquals(2, board.getEmptyTileColumn());
	}
}
