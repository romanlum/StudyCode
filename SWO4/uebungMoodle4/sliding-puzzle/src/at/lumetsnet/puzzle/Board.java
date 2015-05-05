package at.lumetsnet.puzzle;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

public class Board implements Comparable<Board> {

	private final int size;
	private final List<Integer> container;

	
	public Board(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Size has to be greater than zero.");
		}
		this.size = size;
		//allocate puzzle contaier
		container = new ArrayList<Integer>(size*size);
		for(int i=0;i<size*size;i++) {
			container.add(0);
		}
	}
	
	private int getTileIndex(int rowIdx, int colIdx) {
		return ((rowIdx - 1) * size) + (colIdx - 1);
	}

	public int getTile(int rowIdx, int colIdx) {
		return container.get(getTileIndex(rowIdx, colIdx));
	}

	public void setTile(int rowIdx, int colIdx, int number) {
		container.set(getTileIndex(rowIdx, colIdx), number);
	}

	public void setEmptyTile(int rowIdx, int colIdx) {
		setTile(rowIdx, colIdx, 0);
	}
	
	public int getEmptyTileRow() {
		return (container.indexOf(0)/size)+1;
	}
	
	
	public int getEmptyTileColumn() {
		return (container.indexOf(0) - ((getEmptyTileRow() - 1) * size) + 1);
	}
	
	public int size() {
		return size;
	}

	public boolean isValid() {
		if(container.stream().distinct().count() == size*size) {
			for(int i = 0; i < (size*size) -1; i++) {
				if(!container.contains(i)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public void shuffle() {
		
		throw new RuntimeException("Not implemented");
	}

	public void move(int rowIdx, int colIdx) {
		if(rowIdx < 1 || rowIdx > size || colIdx < 1 || colIdx > size) {
			throw new IllegalMoveException("Cannot move outside the board!");
		}
		
		int curRowIdx = getEmptyTileRow();
		int curColIdx = getEmptyTileColumn();
		
		if((Math.abs(curRowIdx - rowIdx ) == 1 &&
		   (curColIdx - colIdx == 0)) ||
		   (curRowIdx - rowIdx == 0 &&
		   Math.abs(curColIdx - colIdx) == 1)){
			
			int tile = getTile(rowIdx, colIdx);
			setEmptyTile(rowIdx, colIdx);
			setTile(curRowIdx, curColIdx, tile);
		} else {
			throw new IllegalMoveException("Cannot perform move!");
		}
		
	}

	public void moveLeft() {
		int curRowIdx = getEmptyTileRow();
		int curColIdx = getEmptyTileColumn();
		move(curRowIdx, curColIdx-1);
	}

	
	public void moveRight() {
		int curRowIdx = getEmptyTileRow();
		int curColIdx = getEmptyTileColumn();
		move(curRowIdx, curColIdx+1);
	}

	public void moveUp() {
		int curRowIdx = getEmptyTileRow();
		int curColIdx = getEmptyTileColumn();
		move(curRowIdx-1, curColIdx);
	}

	public void moveDown() {
		int curRowIdx = getEmptyTileRow();
		int curColIdx = getEmptyTileColumn();
		move(curRowIdx+1, curColIdx);
	}
	
	public Board copy() {
		Board result = new Board(size);
		result.container.clear();
		result.container.addAll(container);
		return result;
	}

	/**
	 * Compares the size of this and the other board
	 */
	@Override
	public int compareTo(Board o) {
		return size - o.size();
	}

	@Override
	public boolean equals(Object other) {
		if(this == other) {
			return true;
		}
		
		if(!(other instanceof Board)) {
			return false;
		}
		Board otherBoard = (Board) other;
		if(this.compareTo(otherBoard)!= 0) {
			return false;
		}
		
		return this.container.equals(otherBoard.container);
	}
}
