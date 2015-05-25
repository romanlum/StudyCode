package at.lumetsnet.swe4.collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class RedBlack<Key> extends AbstractSortedTreeSet<Key> {

	/***
	 * Iterator class
	 * @author romanlum
	 *
	 * @param <T>
	 */
	private class BSTIterator<T> implements Iterator<T> {

		private Stack<Node> unvisitedParents = new Stack<>();

		public BSTIterator(Node root) {
			Node next = root;
			while (next != null) {
				if(next != EMPTY)
					unvisitedParents.push(next);
				next = next.left;
			}
		}

		@Override
		public boolean hasNext() {
			return !unvisitedParents.isEmpty();
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException("Stack is empty");
			}

			Node cur = unvisitedParents.pop();
			Node next = cur.right;
			while (next != null) {
				if(next != EMPTY)
					unvisitedParents.add(next);
				next = next.left;
			}
			return (T) cur.key;
		}

	}

	
    /** Node labels. */
    private enum Color {RED, BLACK};

    /** Value returned from a comparator */
    private static final int LESS    = -1;

    /** Value returned from a comparator */
    private static final int EQUAL   =  0;

    /** Value returned from a comparator */
    private static final int GREATER = +1;
    
    /** NULL when the tree is empty */
    private Node root;

    /** All leaf nodes are black empty nodes that share this one instance. */
    final private Node EMPTY = new Empty();

    public RedBlack(Comparator<Key> comparator) {
    	super(comparator);
    	root = EMPTY;
    }
    
    public RedBlack() {
    	this(null);
    }
    
    public boolean add(Key key) {
    	if(contains(key)) return false;
        root = root.add(key);
        root.color = Color.BLACK;
        size++;
        return true;
    }

    /** Returns null if not found. */
    public Key get(Key key) {
        Node n = root.getNode(key);
        if (n != null) {
            return n.key;
        } else {
            return null;
        }
    }
    
    public boolean contains(Key key) {
        return root.getNode(key) != null;
    }

    private class Node {
        public Key    key;
        
        public Color  color;
        public Node   left;
        public Node   right;
        
        /** Used by Empty */
        protected Node() {
            assert EMPTY == null;
        }

        /** Nodes always begin red */
        public Node(Key k) {
            key   = k;
            color = Color.RED;
            left  = EMPTY;
            right = EMPTY;
        }
        
        private boolean isRed() {
            return color == Color.RED;
        }

        public Node add(Key k) {
            switch (compareElements(k, key)) {
            case LESS:
                left = left.add(k);
                break;

            case GREATER:
                right = right.add(k);
                break;

            case EQUAL:
                return this;  
            }
        
        
            // Check for two red nodes in a row: Red child and red grandchild
            if (left.isRed() && left.left.isRed()) {

                //       z           y
                //      / \         / \
                //     y   D  ==>  /   \
                //    / \         x     z
                //   x   C       / \   / \
                //  / \         A   B C   D
                // A   B
                return balance(left.left, left, this,              // x,y,z
                               left.left.right, left.right);       // B,C

            } else if (left.isRed() && left.right.isRed()) {

                //       z           y
                //      / \         / \
                //     x   D  ==>  /   \
                //    / \         x     z
                //   A   y       / \   / \
                //      / \     A   B C   D
                //     B   C
                return balance(left, left.right, this,             // x,y,z
                               left.right.left, left.right.right); // B,C
                
            } else if (right.isRed() && right.left.isRed()) {

                //     x             y
                //    / \           / \
                //   A   z    ==>  /   \
                //      / \       x     z
                //     y   D     / \   / \
                //    / \       A   B C   D
                //   B   C
                return balance(this, right.left, right,            // x,y,z
                               right.left.left, right.left.right); // B,C
                
            } else if (right.isRed() && right.right.isRed()) {
                //   x               y
                //  / \             / \
                // A   y      ==>  /   \
                //    / \         x     z
                //   B   z       / \   / \
                //      / \     A   B C   D
                //     C   D
                return balance(this, right, right.right,           // x,y,z
                               right.left, right.right.left);      // B,C
            }

            return this;
        }


        /** Returns the node for this key, or null. */
        public Node getNode(Key k) {
            switch (compareElements(k, key)) {
            case LESS:
                return left.getNode(k);

            case GREATER:
                return right.getNode(k);

            default: // EQUAL
                return this;
            }
        }

    }
       
    /** The empty node used at leaves */
    private class Empty extends Node {

        public Empty() {
            color = Color.BLACK;
            assert EMPTY == null : "Should only make one empty node instance!";
        }

        /** Always make a new node, since this one is empty */
        public Node add(Key k) {
            return new Node(k);
        }

        public Node getNode(Key key) {
            return null;
        }
    }

    /**
      Rearrange/recolor the tree as

      <pre>
             y      <== red
            / \
           /   \
          x     z   <== both black
         / \   / \
        A   B C   D
      </pre> 
      Note: A and D are not passed in because already in the right place
    */
    private Node balance(Node x, Node y, Node z, Node B, Node C) {
        
        x.right = B;
        y.left  = x;
        y.right = z;
        z.left  = C;
        x.color = Color.BLACK;
        y.color = Color.RED;
        z.color = Color.BLACK;
        return y;
    }

	
    @Override
	public Key first() {
		if (root == EMPTY) {
			throw new NoSuchElementException("Set is empty");
		}
		Node tmp = root;
		while (tmp.left != EMPTY) {
			tmp = tmp.left;
		}
		return (Key)tmp.key;
	}

	@Override
	public Key last() {
		if (root == EMPTY) {
			throw new NoSuchElementException("Set is empty");
		}
		Node tmp = root;
		while (tmp.right != EMPTY) {
			tmp = tmp.right;
		}
		return (Key)tmp.key;
	}

	@Override
	public Iterator<Key> iterator() {
		return new BSTIterator<>(root);
	}

}
