/* Name: Lang, Jordan
 * Project Name: Project3Cmis350
 * Date: 04/13/2021
 * Description: This class is used to evaluate the users expression entry and 
 * show if the tree is Balance, Full, Proper, its Height, # of Nodes, and what it
 * would look like in-order
*/
package Project3Cmis350;

import java.util.*;

public class BinaryTree {

	public static class Node {
		private String inputChars;
		private Node leftNode;
		private Node rightNode;

		// Setters
		private void setleftNode(Node leftNode) {
			this.leftNode = leftNode;
		}

		private void setrightNode(Node rightNode) {
			this.rightNode = rightNode;
		}

		public Node(String inputChars) {
			this.inputChars = inputChars;
		}

		// Method to ensure each node only has 2 children
		private void children(Node child) throws InvalidTreeSyntax {
			if (leftNode == null) {
				setleftNode(child);
			} else if (rightNode == null) {
				setrightNode(child);
			} else {
				throw new InvalidTreeSyntax("Only 2 children per node.");
			}
		}

		private static String toString(Node root) {
			// use if else statement as base case in recursive method
			if (root == null) {
				return "";
			} else {
				return "(" + root.inputChars + toString(root.leftNode) + toString(root.rightNode) + ")";
			}
		}

		@Override // Ensure the "Make Tree" button prints as a string
		public String toString() {
			return toString(this);
		}
	}

	public Node parent, child;

	public BinaryTree(String treeInput) throws InvalidTreeSyntax {
		if (!treeInput.equals("")) { // error handling if empty
			Stack<Node> nodes = new Stack<>();
			// Create array from user input tree
			String[] treeArray = treeInput.substring(1, treeInput.length() - 1).split("");
			// +- to remove parenthesis

			// Create initial root
			parent = new Node(treeArray[0]);

			for (int i = 1; i < treeArray.length - 1; i++) {
				// create a child
				if (treeArray[i].equals("(")) {
					nodes.push(parent);
					if (child != null) {
						parent = child;
					}
					// convert the previous "child" into the parent
				} else if (treeArray[i].equals(")")) {
					try {
						child = parent;
						parent = nodes.pop();
					} catch (EmptyStackException emptyStack) {
						throw new InvalidTreeSyntax("Invalid Syntax");
					}
					// remaining node is a child
				} else {
					child = new Node(treeArray[i]);
					if (parent != null) {
						parent.children(child);
					}
				}
			} // check parenthesis syntax is correct
			if (this.nodes(parent) * 3 != treeInput.length())
				throw new InvalidTreeSyntax("Invalid Syntax, check parenthesis");
		} else {
			// ensure entry is not empty
			throw new InvalidTreeSyntax("Please enter an expression.");
		}
	}

	public int treeHeight() {
		// Need -1 because starting at 0
		return height(parent) - 1;
	}

	private int height(Node node) {
		// use if else statement as base case in recursive method
		if (node == null) {
			return 0;
		} else {
			// If tree is not empty then height = 1 + max of left height and right heights
			return 1 + Math.max(height(node.leftNode), height(node.rightNode));
		}
	}

	public boolean treeIsBalanced() {
		return isBalanced(parent);
	}

	private boolean isBalanced(Node node) {
		// use if else statement as base case in recursive method
		if (node == null) {
			return true;
		} else {
			/*
			 * use tree height to determine if it is balanced. Found similar solution from
			 * www.geeksforgeeks.org/how-to-determine-if-a-binary-tree-is-balanced/ Also
			 * used this to help with height method
			 */
			return (Math.abs(height(node.leftNode) - height(node.rightNode)) <= 1)
					&& (isBalanced(node.leftNode) && isBalanced(node.rightNode));
		}
	}

	public boolean treeIsFull() {
		return isFull(parent, height(parent), 0);
	}

	private boolean isFull(Node node, int height, int i) {

		// use if else statement as base case in recursive method
		if (node == null) {
			return true;
		}
		// if height is the same
		if (node.leftNode == null && node.rightNode == null) {
			return (height == i + 1);
		}
		// if either child is not null and the other one is null, then BT is not full
		if (node.leftNode == null || node.rightNode == null) {
			return false;
		}

		return isFull(node.leftNode, height, i + 1) && isFull(node.rightNode, height, i + 1);
	}

	public boolean treeIsProper() {
		return isProper(parent);
	}

	private boolean isProper(Node node) {
		// use if else statement as base case in recursive method
		if (node == null) {
			return true;
		}
		// determine if every node has either 0 or 2 children
		return ((node.leftNode != null || node.rightNode == null) && (node.leftNode == null || node.rightNode != null))
				&& (isProper(node.leftNode) && isProper(node.rightNode));
	}

	public int treeNodes() {
		return nodes(parent);
	}

	private int nodes(Node node) {
		// use if else statement as base case in recursive method
		if (node == null) {
			return 0;
		} else {
			// add up nodes in the tree
			return 1 + nodes(node.leftNode) + nodes(node.rightNode);
		}
	}

	public String treeInOrder() {
		return inOrder(parent);
	}

	private String inOrder(Node node) {
		// use if else statement as base case in recursive method
		if (node == null) {
			return "";
		} else {
			return "(" + inOrder(node.leftNode) + node.inputChars + inOrder(node.rightNode) + ")";
		}
	}

	@Override // Ensure the "Make Tree" button prints as a string
	public String toString() {
		return parent.toString();
	}

}
