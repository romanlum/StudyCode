package at.lumetsnet.puzzle;

import java.util.ArrayList;
import java.util.List;

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
	}

	public void move(int rowIdx, int colIdx) {
	}

	public void moveLeft() {

	}

	
	public void moveRight() {
		
	}

	public void moveUp() {
	}

	public void moveDown() {
	}

	@Override
	public int compareTo(Board o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
