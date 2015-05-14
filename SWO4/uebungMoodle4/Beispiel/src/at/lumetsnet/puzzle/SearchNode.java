package at.lumetsnet.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchNode implements Comparable<SearchNode> {

	private Board board;
	private SearchNode predecessor;
	private int costsFromStart;
	private Move move;
	private int manhattanDistance;

	/***
	 * Constructor with board
	 * 
	 * @param board
	 */
	public SearchNode(Board board) {
		this.board = board;
		// calculate manhattan distance
		manhattanDistance = calcManhattanDistance();
	}

	/***
	 * Constructor with all data
	 * 
	 * @param board
	 * @param predecessor
	 * @param costsFromStart
	 * @param move
	 */
	public SearchNode(Board board, SearchNode predecessor, int costsFromStart,
			Move move) {
		this(board);
		this.predecessor = predecessor;
		this.costsFromStart = costsFromStart;
		this.move = move;
	}

	/***
	 * Gets the board
	 */
	public Board getBoard() {
		return board;
	}

	/***
	 * Gets the predecessor
	 * 
	 * @return
	 */
	public SearchNode getPredecessor() {
		return predecessor;
	}

	/***
	 * Sets the predecessor
	 * 
	 * @param predecessor
	 */
	public void setPredecessor(SearchNode predecessor) {
		this.predecessor = predecessor;
	}

	/***
	 * Gets the costs from start
	 * 
	 * @return
	 */
	public int costsFromStart() {
		return this.costsFromStart;
	}

	/***
	 * Sets the costs from start
	 * 
	 * @param costsFromStart
	 */
	public void setCostsFromStart(int costsFromStart) {
		this.costsFromStart = costsFromStart;
	}

	/***
	 * Gets the estimated costs to the goal
	 * 
	 * @return
	 */
	public int estimatedCostsToTarget() {
		return manhattanDistance;
	}

	/***
	 * Gets the estimated total costs
	 * 
	 * @return
	 */
	public int estimatedTotalCosts() {
		return costsFromStart + estimatedCostsToTarget();
	}

	/***
	 * Gets the move represented by this node
	 * 
	 * @return
	 */
	public Move getMove() {
		return move;
	}

	/***
	 * Sets the move represented by this node
	 * 
	 * @return
	 */
	public void setMove(Move move) {
		this.move = move;
	}

	/***
	 * Compares the node with another object
	 */
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (!(other instanceof SearchNode))
			return false;

		SearchNode otherNode = (SearchNode) other;
		if (board == null && otherNode.board != null)
			return false;
		return board.equals(otherNode.board);
	}

	/***
	 * Compare the costs of the node against the other
	 * 
	 * @return
	 */
	@Override
	public int compareTo(SearchNode other) {
		return estimatedTotalCosts() - other.estimatedTotalCosts();
	}

	/***
	 * Converts the list into a list of moves from the start
	 * 
	 * @return
	 */
	public List<Move> toMoves() {
		List<Move> result = new ArrayList<Move>();
		SearchNode cur = this;
		while (cur != null) {
			if (cur.getMove() != null) {
				result.add(cur.getMove());
			}
			cur = cur.getPredecessor();
		}
		// reverse the order of the collection
		// to get the moves from the start
		Collections.reverse(result);
		return result;
	}

	/***
	 * Calculates the manhattan distance
	 * 
	 * @return
	 */
	private int calcManhattanDistance() {
		int manhattanDistanceSum = 0;
		for (int x = 1; x <= board.size(); x++)
			for (int y = 1; y <= board.size(); y++) {
				int value = board.getTile(x, y);
				if (value != 0) {
					int targetX = (value - 1) / board.size();
					int targetY = (value - 1) % board.size();
					int dx = x - (targetX + 1);
					int dy = y - (targetY + 1);
					manhattanDistanceSum += Math.abs(dx) + Math.abs(dy);
				}
			}
		return manhattanDistanceSum;
	}

	/***
	 * calculates the hashcode of the SearchNode
	 */
	@Override
	public int hashCode() {
		final int prime = 17;
		int result = 1;
		result = prime * result + ((board == null) ? 0 : board.hashCode());
		return result;
	}

}
