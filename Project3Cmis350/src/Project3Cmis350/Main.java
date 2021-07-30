/* Name: Lang, Jordan
 * Project Name: Project3Cmis350
 * Date: 04/13/2021
 * Description: This class creates the GUI for the user to enter an
 * expression and select what information they would like to see about the
 * tree such as Balance?, Full?, Proper?, Height?, Nodes, and in-order?. It
 * implements the BinaryTree class to show these outputs.
*/
package Project3Cmis350;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {

	private JButton makeTree, isBalanced, isFull, isProper, height, nodes, inOrder;
	private JLabel treeLabel;
	private JLabel outputLabel;
	private JTextField enteredTree;
	private JTextField outputTree;

	public Main() {

		setTitle("Binary Tree Categorizer");
		setSize(700, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		add(mainPanel);

		// create buttons for the panel
		this.makeTree = new JButton("Make Tree");
		this.isBalanced = new JButton("Is Balanced?");
		this.isFull = new JButton("Is Full?");
		this.isProper = new JButton("Is Proper?");
		this.height = new JButton("Height");
		this.nodes = new JButton("Nodes");
		this.inOrder = new JButton("In-Order");

		this.treeLabel = new JLabel("Enter Tree:");
		this.outputLabel = new JLabel("Output:");
		this.enteredTree = new JTextField(40);
		this.outputTree = new JTextField(40);

		mainPanel.add(northPanel(), BorderLayout.NORTH);
		mainPanel.add(centerPanel(), BorderLayout.CENTER);
		mainPanel.add(southPanel(), BorderLayout.SOUTH);

	}

	// create JPanel upper layout
	private JPanel northPanel() {
		JPanel northPanel = new JPanel();
		northPanel.add(treeLabel);
		northPanel.add(enteredTree);
		return northPanel;
	}

	// create JPanel center layout
	private JPanel centerPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.add(makeTree);
		centerPanel.add(isBalanced);
		centerPanel.add(isFull);
		centerPanel.add(isProper);
		centerPanel.add(height);
		centerPanel.add(nodes);
		centerPanel.add(inOrder);
		// Add button to perform methods from BinaryTree class
		makeTree.addActionListener(this); 
		isBalanced.addActionListener(this);
		isFull.addActionListener(this);
		isProper.addActionListener(this);
		height.addActionListener(this);
		nodes.addActionListener(this);
		inOrder.addActionListener(this);
		return centerPanel;
	}

	// create JPanel lower layout
	private JPanel southPanel() {
		JPanel southPanel = new JPanel();
		southPanel.add(outputLabel);
		southPanel.add(outputTree);
		outputTree.setEditable(false);
		return southPanel;
	}

	private BinaryTree tree;

	// Set up ActionListeners to display outputs for each button
	public void actionPerformed(ActionEvent e) {
		try {
			String expressionEntered = enteredTree.getText();

			if (e.getSource() == makeTree) {
				tree = new BinaryTree(expressionEntered);
				outputTree.setText(tree.toString());
			}

			// Throw error for each button on first click if user does not enter a tree
			else if (e.getSource() == isBalanced) {
				if (tree == null) {
					throw new InvalidTreeSyntax("Please enter an expression.");
				} else {
					outputTree.setText(String.valueOf(tree.treeIsBalanced()));
				}
			}

			else if (e.getSource() == isFull) {
				if (tree == null) {
					throw new InvalidTreeSyntax("Please enter an expression.");
				} else {
					outputTree.setText(String.valueOf(tree.treeIsFull()));
				}
			}

			else if (e.getSource() == isProper) {
				if (tree == null) {
					throw new InvalidTreeSyntax("Please enter an expression.");
				} else {
					outputTree.setText(String.valueOf(tree.treeIsProper()));
				}
			}

			else if (e.getSource() == height) {
				if (tree == null) {
					throw new InvalidTreeSyntax("Please enter an expression.");
				} else {
					outputTree.setText(String.valueOf(tree.treeHeight()));
				}
			}

			else if (e.getSource() == nodes) {
				if (tree == null) {
					throw new InvalidTreeSyntax("Please enter an expression.");
				} else {
					outputTree.setText(String.valueOf(tree.treeNodes()));
				}
			}

			else if (e.getSource() == inOrder) {
				if (tree == null) {
					throw new InvalidTreeSyntax("Please enter an expression.");
				} else {
					outputTree.setText(String.valueOf(tree.treeInOrder()));
				}
			}
		}

		// display the exceptions in a new panel if they occur
		catch (InvalidTreeSyntax e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	public static void main(String[] args) {

		Main display = new Main();
		display.setVisible(true);
	}
}
