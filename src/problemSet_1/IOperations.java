package problemSet_1;

public interface IOperations {
	BinaryTreeNode<Integer> constructBinaryTree();
	void preOrderTraversalRecursive(BinaryTreeNode<Integer> node);
	void preOrderTraversal(BinaryTreeNode<Integer> node);
	void inOrderTraversalRecursive(BinaryTreeNode<Integer> node);
	void inOrderTraversal(BinaryTreeNode<Integer> node);
	void postOrderTraversalRecursive(BinaryTreeNode<Integer> node);
	void levelOrderTraversal(BinaryTreeNode<Integer> node);
	int getHeightRecursive(BinaryTreeNode<Integer> node);
	int getHeight(BinaryTreeNode<Integer> node);
	int getDiameterOfBinaryTree(BinaryTreeNode<Integer> root);
	int levelWithMaxDataSum(BinaryTreeNode<Integer> node);
	void printAllPaths(BinaryTreeNode<Integer> root);
	boolean hasPathSum(BinaryTreeNode<Integer> node, int sum);
	void deleteNodeGivenData(int dataToDelete, BinaryTreeNode<Integer> root);
	int DiameterOfTree(BinaryTreeNode<Integer> root);
	BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> node, BinaryTreeNode<Integer> searchNode1, BinaryTreeNode<Integer> searchNode2);
	void zigZagTraversal(BinaryTreeNode<Integer> node);
	void fillSiblingsRecursively(BinaryTreeNode<Integer> node);
	void fillSiblingsIteratively(BinaryTreeNode<Integer> node);
	void mirroringOfBinaryTree(BinaryTreeNode<Integer> node);
}
