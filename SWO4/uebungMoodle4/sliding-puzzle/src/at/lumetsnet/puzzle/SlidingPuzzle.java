package at.lumetsnet.puzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		Queue<SearchNode> queue = new PriorityQueue<SearchNode>();
		Map<Board, SearchNode> closedMap = new HashMap<Board, SearchNode>();
		Map<Board, SearchNode> openMap = new HashMap<Board, SearchNode>();

		// create search node from current board
		SearchNode current = new SearchNode(board);
		queue.add(current);
		openMap.put(current.getBoard(), current);
		while (!queue.isEmpty()) {

			current = queue.poll();
			// remove board from the open list
			openMap.remove(current.getBoard());
			// calculate the successors
			final List<SearchNode> successors = getSuccessors(current);
			for (SearchNode successor : successors) {

				// estimatedCostsToTarget = 0 means we found a solution
				if (successor.estimatedCostsToTarget() == 0) {
					return successor.toMoves();
				}

				SearchNode tmpNode = openMap.get(successor.getBoard());
				if ((tmpNode != null) && (tmpNode.compareTo(successor) <= 0)) {
					continue;
				} else {
					openMap.remove(successor.getBoard());
				}

				tmpNode = closedMap.get(successor.getBoard());
				if ((tmpNode != null) && (tmpNode.compareTo(successor) <= 0)) {
					continue;
				}

				openMap.put(successor.getBoard(), successor);
				queue.add(successor);
			}
			closedMap.put(current.getBoard(), current);

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
