// Implements a BST with BinaryNode nodes

public class MyBST<E extends Comparable<E>> {

	private BinaryNode<E> root; // holds the root of this BST

	// Constructor: creates an empty BST.
	public MyBST() {
		root = null;
	}

	public BinaryNode<E> getRoot() {
		return root;
	}

	public int getHeight() {
		return getHighest(root);
	}

	private int getHighest(BinaryNode<E> node) {
		if (node == null) {
			return 0;
		}
		if (node.isLeaf()) {
			return node.getHeight();
		}
		int left = 0, right = 0;
		if (node.getLeft() != null) {
			left = getHighest(node.getLeft());
		}
		if (node.getRight() != null) {
			right = getHighest(node.getRight());
		}
		return left > right ? left : right;
	}

	// Returns true if this BST contains value; otherwise returns false.
	public boolean contains(E value) {
		BinaryNode<E> currNode = root;
		while (currNode != null) {
			if (currNode.getValue().equals(value)) {
				return true;
			} else if (value.compareTo(currNode.getValue()) < 0) {
				currNode = currNode.getLeft();
			} else {
				currNode = currNode.getRight();
			}
		}
		return false;
	}

	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(E value) {
		if (root == null) {
			root = new BinaryNode<E>(value);
			return true;
		}
		if (contains(value)) {
			return false;
		}
		BinaryNode<E> currNode = root;
		BinaryNode<E> lastNode = root;
		while (currNode != null) {
			if (currNode.getValue().equals(value)) {
				return true;
			} else if (value.compareTo(currNode.getValue()) < 0) {
				lastNode = currNode;
				currNode = currNode.getLeft();
			} else {
				lastNode = currNode;
				currNode = currNode.getRight();
			}
		}
		if (value.compareTo(lastNode.getValue()) < 0) {
			lastNode.setLeft(new BinaryNode<E>(value, lastNode));
		} else {
			lastNode.setRight(new BinaryNode<E>(value, lastNode));
		}
		return true;
	}

	// Removes value from this BST. Returns true if value has been
	// found and removed; otherwise returns false.
	// If removing a node with two children: replace it with the
	// largest node in the right subtree
	public boolean remove(E value) {
		BinaryNode<E> currNode = root;
		while (currNode != null) {
			if (currNode.getValue().equals(value)) {
				BinaryNode<E> replacementNode = null;
				if (currNode.hasRight()) {
					replacementNode = currNode.getRight();
					while (replacementNode.hasLeft()) {
						replacementNode = replacementNode.getLeft();
					}
					detachFromParent(replacementNode);
				} else if (currNode.hasLeft()) {
					replacementNode = currNode.getLeft();
					while (replacementNode.hasRight()) {
						replacementNode = replacementNode.getRight();
					}
					detachFromParent(replacementNode);
				} else {
					if (currNode.equals(root)) {
						root = null;
					} else if (currNode.getParent().getLeft().equals(currNode)) {
						currNode.getParent().setLeft(null);
					} else {
						currNode.getParent().setRight(null);
					}
					return true;
				}
				if (currNode.equals(root)) {
					root = replacementNode;
					replacementNode.setParent(null);
				} else if (currNode.getParent().getLeft().equals(currNode)) {
					currNode.getParent().setLeft(replacementNode);
					replacementNode.setParent(currNode.getParent());
				} else {
					currNode.getParent().setRight(replacementNode);
					replacementNode.setParent(currNode.getParent());
				}
				if (currNode.hasLeft() && replacementNode != currNode.getLeft()) {
					replacementNode.setLeft(currNode.getLeft());
					currNode.getLeft().setParent(replacementNode);
				}
				if (currNode.hasRight() && replacementNode != currNode.getRight()) {
					replacementNode.setRight(currNode.getRight());
					currNode.getRight().setParent(replacementNode);
				}
				return true;
			}
			currNode = value.compareTo(currNode.getValue()) < 0 ? currNode.getLeft()
					: currNode.getRight();
		}
		return false;
	}

	private void detachFromParent(BinaryNode<E> node) {
		if (node.getParent() == null) {
			return;
		}
		if (node.getParent().getLeft() == node) {
			node.getParent().setLeft(null);
		} else {
			node.getParent().setRight(null);
		}
	}

	// Returns the minimum in the tree
	public E min() {
		if (root == null) {
			return null;
		}
		BinaryNode<E> currNode = root;
		while (currNode.getLeft() != null) {
			currNode = currNode.getLeft();
		}
		return currNode.getValue();
	}

	// Returns the maximum in the tree.
	public E max() {
		if (root == null) {
			return null;
		}
		BinaryNode<E> currNode = root;
		while (currNode.getRight() != null) {
			currNode = currNode.getRight();
		}
		return currNode.getValue();
	}

	// Returns a bracket-surrounded, comma separated list of the contents of the
	// nodes, in order
	// e.g. [Apple, Cranberry, Durian, Mango]
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		if (root == null) {
			str.append("]");
			return str.toString();
		}
		BinaryNode<E> currNode = root;
		if (currNode.getLeft() != null) {
			while (currNode.getLeft() != null) {
				currNode = currNode.getLeft();
			}
			while (!currNode.equals(root)) {
				str.append(currNode.getValue().toString());
				if (currNode.getRight() != null) {
					str.append(", ");
					str.append(currNode.getRight().getValue().toString());
				}
				str.append(", ");
				currNode = currNode.getParent();
			}
		}
		str.append(currNode.getValue().toString());
		str.append(", ");
		if (currNode.getRight() != null) {
			currNode = currNode.getRight();
			while (currNode.getLeft() != null) {
				currNode = currNode.getLeft();
			}
			while (!currNode.equals(root)) {
				str.append(currNode.getValue().toString());
				if (currNode.getRight() != null) {
					str.append(", ");
					str.append(currNode.getRight().getValue().toString());
				}
				str.append(", ");
				currNode = currNode.getParent();
			}
		}
		str.delete(str.length() - 2, str.length());
		str.append("]");
		return str.toString();
	}
}
