package problemSet_1;

public class BinaryTreeNode<T> {
	
	T data;
	BinaryTreeNode<T> leftNode;
	BinaryTreeNode<T> rightNode;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BinaryTreeNode<T> getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(BinaryTreeNode<T> leftNode) {
		this.leftNode = leftNode;
	}
	public BinaryTreeNode<T> getRightNode() {
		return rightNode;
	}
	public void setRightNode(BinaryTreeNode<T> rightNode) {
		this.rightNode = rightNode;
	}
	
	public BinaryTreeNode(T data, BinaryTreeNode<T> leftNode,
			BinaryTreeNode<T> rightNode) {
		super();
		this.data = data;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}
	
	public BinaryTreeNode(){};

}
