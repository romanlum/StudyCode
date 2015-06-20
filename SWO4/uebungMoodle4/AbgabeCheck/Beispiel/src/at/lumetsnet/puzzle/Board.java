package at.lumetsnet.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.lumetsnet.puzzle.exceptions.IllegalMoveException;
import at.lumetsnet.puzzle.exceptions.InvalidBoardIndexException;
import at.lumetsnet.puzzle.exceptions.InvalidTileNumberException;

public class Board implements Comparable<Board> {

	/***
	 * Number of shuffle operations
	 */
	private static final int SHUFFLE_COUNT = 100;

	private final int size;
	private final List<Integer> container;

	public Board(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException(
					"Size has to be greater than zero.");
		}
		this.size = size;
		container = new ArrayList<Integer>(size * size);
		for (int i = 0; i < size * size - 1; i++) {
			container.add(i + 1);
		}
		container.add(0);
	}

	/***
	 * Gets the tile index inside the container
	 * 
	 * @param rowIdx
	 * @param colIdx
	 * @return
	 */
	private int getTileIndex(int rowIdx, int colIdx) {
		return ((rowIdx - 1) * size) + (colIdx - 1);
	}

	/***
	 * Checks for valid board indices
	 * 
	 * @param rowIdx
	 * @param colIdx
	 * @throws InvalidBoardIndexException
	 */
	private void checkBoardIndex(int rowIdx, int colIdx)
			throws InvalidBoardIndexException {
		if (rowIdx < 1 || rowIdx > size || colIdx < 1 || colIdx > size) {
			throw new InvalidBoardIndexException(rowIdx + ", " + colIdx
					+ " are invalid indices");
		}
	}

	/***
	 * Gets the tile from the given position
	 * 
	 * @param rowIdx
	 * @param colIdx
	 * @throws InvalidBoardIndexException
	 * @return
	 */
	public int getTile(int rowIdx, int colIdx)
			throws InvalidBoardIndexException {
		checkBoardIndex(rowIdx, colIdx);
		return container.get(getTileIndex(rowIdx, colIdx));
	}

	/***
	 * Sets the tile from the given position
	 * 
	 * @param rowIdx
	 * @param colIdx
	 * @param number
	 * @throws InvalidBoardIndexException
	 * @throws InvalidTileNumberException
	 */
	public void setTile(int rowIdx, int colIdx, int number)
			throws InvalidBoardIndexException, InvalidTileNumberException {
		checkBoardIndex(rowIdx, colIdx);
		if (number < 0 || number > size * size) {
			throw new InvalidTileNumberException("Tile number " + number
					+ " is not a valid tile number");
		}
		container.set(getTileIndex(rowIdx, colIdx), number);
	}

	/***
	 * Sets the empty tile on the given position
	 * 
	 * @param rowIdx
	 * @param colIdx
	 * @throws InvalidBoardIndexException
	 */
	public void setEmptyTile(int rowIdx, int colIdx)
			throws InvalidBoardIndexException {
		checkBoardIndex(rowIdx, colIdx);
		setTile(rowIdx, colIdx, 0);
	}

	/***
	 * Gets the row index of the empty tile
	 * 
	 * @return
	 */
	public int getEmptyTileRow() {
		return (container.indexOf(0) / size) + 1;
	}

	/***
	 * Gets the column index of the empty tile
	 * 
	 * @return
	 */
	public int getEmptyTileColumn() {
		return (container.indexOf(0) - ((getEmptyTileRow() - 1) * size) + 1);
	}

	/***
	 * Gets the size of the board
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	/***
	 * Checks if the board is valid
	 * 
	 * @return
	 */
	public boolean isValid() {
		if (container.stream().distinct().count() == size * size) {
			for (int i = 0; i < (size * size) - 1; i++) {
				if (!container.contains(i)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/***
	 * Shuffles the board
	 */
	public void shuffle() {
		Random rnd = new Random(System.nanoTime());
		// Do SHUFFLE_COUNT random moves
		for (int i = 0; i < SHUFFLE_COUNT; i++) {
			int random = rnd.nextInt(4);
			try {
				switch (random) {
				case 0:
					moveUp();
					break;
				case 1:
					moveRight();
					break;
				case 2:
					moveDown();
					break;
				case 3:
					moveLeft();
					break;
				}
			} catch (IllegalMoveException ex) {
				// Ignore illegal moves
			}
		}
	}

	/***
	 * Moves the empty tile to the new position
	 * 
	 * @param rowIdx
	 * @param colIdx
	 */
	public void move(int rowIdx, int colIdx) throws IllegalMoveException {
		if (rowIdx < 1 || rowIdx > size || colIdx < 1 || colIdx > size) {
			throw new IllegalMoveException("Cannot move outside the board!");
		}

		int curRowIdx = getEmptyTileRow();
		int curColIdx = getEmptyTileColumn();

		//Check if move is valid
		if ((Math.abs(curRowIdx - rowIdx) == 1 && (curColIdx - colIdx == 0))
				|| (curRowIdx - rowIdx == 0 && Math.abs(curColIdx - colIdx) == 1)) {

			int tile = getTile(rowIdx, colIdx);
			setEmptyTile(rowIdx, colIdx);
			setTile(curRowIdx, curColIdx, tile);
		} else {
			throw new IllegalMoveException("Cannot perform move!");
		}

	}

	/***
	 * Moves the empty tile left
	 * 
	 * @throws IllegalMoveException
	 */
	public void moveLeft() throws IllegalMoveException {
		int curRowIdx = getEmptyTileRow();
		int curColIdx = getEmptyTileColumn();
		move(curRowIdx, curColIdx - 1);
	}

	/***
	 * Moves the empty tile right
	 * 
	 * @throws IllegalMoveException
	 */
	public void moveRight() throws IllegalMoveException {
		int curRowIdx = getEmptyTileRow();
		int curColIdx = getEmptyTileColumn();
		move(curRowIdx, curColIdx + 1);
	}

	/***
	 * Moves the empty tile up
	 * 
	 * @throws IllegalMoveException
	 */
	public void moveUp() throws IllegalMoveException {
		int curRowIdx = getEmptyTileRow();
		int curColIdx = getEmptyTileColumn();
		move(curRowIdx - 1, curColIdx);
	}

	/***
	 * Moves the empty tile down
	 * 
	 * @throws IllegalMoveException
	 */
	public void moveDown() throws IllegalMoveException {
		int curRowIdx = getEmptyTileRow();
		int curColIdx = getEmptyTileColumn();
		move(curRowIdx + 1, curColIdx);
	}

	/***
	 * Copies the board and all data
	 * 
	 * @return
	 */
	public Board copy() {
		Board result = new Board(size);
		result.container.clear();
		result.container.addAll(container);
		return result;
	}

	/***
	 * Executes a series of moves
	 * 
	 * @param moves
	 * @throws IllegalMoveException
	 */
	public void makeMoves(List<Move> moves) throws IllegalMoveException {
		moves.forEach((x) -> {
			move(x.getRow(), x.getCol());
		});
	}

	/***
	 * Clones the board
	 */
	@Override
	public Object clone() {
		return this.copy();
	}

	/**
	 * Compares the size of this board to the other board
	 */
	@Override
	public int compareTo(Board o) {
		return size - o.size();
	}

	/***
	 * Checks if the boards are equal
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (!(other instanceof Board)) {
			return false;
		}
		Board otherBoard = (Board) other;
		if (this.compareTo(otherBoard) != 0) {
			return false;
		}

		return this.container.equals(otherBoard.container);
	}

	/***
	 * Calculates the hashcode of the board
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((container == null) ? 0 : container.hashCode());
		return result;
	}

	/***
	 * Prints the board
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				builder.append(String.format("%2d", getTile(i, j)));
			}
			builder.append("\n");
		}
		return builder.toString();
	}

}
