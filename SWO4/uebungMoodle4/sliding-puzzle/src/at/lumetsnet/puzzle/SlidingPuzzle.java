package at.lumetsnet.puzzle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import at.lumetsnet.puzzle.exceptions.IllegalMoveException;
import at.lumetsnet.puzzle.exceptions.NoSolutionException;

public class SlidingPuzzle {

	/***
	 * Solves the board
	 * 
	 * @param board
	 * @return
	 */
	public List<Move> solve(Board board) {
		Queue<SearchNode> openQueue = new PriorityQueue<SearchNode>();
		HashSet<SearchNode> closedSet = new HashSet<SearchNode>();

		// create search node from current board
		SearchNode current = new SearchNode(board);
		openQueue.add(current);

		while (!openQueue.isEmpty()) {
			// get next node
			current = openQueue.poll();

			// estimatedCostsToTarget = 0 means we found a solution
			if (current.estimatedCostsToTarget() == 0) {
				return current.toMoves();
			}

			closedSet.add(current);

			// calculate the successors
			final List<SearchNode> successors = getSuccessors(current);
			for (SearchNode successor : successors) {

				if (!closedSet.contains(successor)) {
					if (openQueue.contains(successor)
							&& current.estimatedTotalCosts() >= successor
									.estimatedTotalCosts()) {
						//remove old node
						openQueue.remove(successor);
					}
					openQueue.add(successor);
				}
			}
		}
		throw new NoSolutionException("Board has no solution");
	}

	/***
	 * returns the successors for the node
	 * 
	 * @param parent
	 * @return
	 */
	private List<SearchNode> getSuccessors(SearchNode parent) {
		final List<SearchNode> result = new ArrayList<SearchNode>();
		for (int i = 0; i < 4; i++) {
			Board newBoard = parent.getBoard().copy();
			try {
				switch (i) {
				case 0:
					newBoard.moveLeft();
					break;
				case 1:
					newBoard.moveUp();
					break;
				case 2:
					newBoard.moveRight();
					break;
				case 3:
					newBoard.moveDown();
					break;
				}
				SearchNode node = new SearchNode(newBoard, parent,
						parent.costsFromStart() + 1, new Move(
								newBoard.getEmptyTileRow(),
								newBoard.getEmptyTileColumn()));
				result.add(node);
			} catch (IllegalMoveException ex) {
				// ignore illegal moves
			}
		}
		return result;
	}

	/***
	 * Prints the moves to the console
	 * 
	 * @param board
	 * @param moves
	 */
	public void printMoves(Board board, List<Move> moves) {
		System.out.println("Starting board");
		System.out.println(board);
		moves.stream().forEach((x) -> {
			board.move(x.getRow(), x.getCol());
			System.out.println(board);
		});
	}
}
