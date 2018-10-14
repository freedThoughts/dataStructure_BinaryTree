package problemSet_1;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeOperations implements IOperations{
	
	public BinaryTreeNode<Integer> constructBinaryTree(){
		BinaryTreeNode<Integer> node7 = new BinaryTreeNode<Integer>(7, null, null);
		BinaryTreeNode<Integer> node6 = new BinaryTreeNode<Integer>(6, null, null);
		BinaryTreeNode<Integer> node5 = new BinaryTreeNode<Integer>(5, null, null);
		BinaryTreeNode<Integer> node4 = new BinaryTreeNode<Integer>(4, null, null);
		BinaryTreeNode<Integer> node3 = new BinaryTreeNode<Integer>(3, node6, node7);
		BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(2, node4, node5);
		BinaryTreeNode<Integer> node1 = new BinaryTreeNode<Integer>(1, node2, node3);
		return node1;
	}
	
	public void preOrderTraversalRecursive(BinaryTreeNode<Integer> node){
		if(node == null)
			return;
		
		if(node.getData() != null)
			System.out.println(node.getData());
		
		preOrderTraversalRecursive(node.getLeftNode());
		preOrderTraversalRecursive(node.getRightNode());
	}
	
	public void preOrderTraversal(BinaryTreeNode<Integer> node){
		Stack<BinaryTreeNode<Integer>> stack = new Stack<BinaryTreeNode<Integer>>();
		while(node != null || !stack.isEmpty()){
			if(node != null){
				System.out.println(node.getData());
				stack.push(node);
				node = node.getLeftNode();
				continue;
			}
			
			node = stack.pop();
			node = node.getRightNode();
		}
	}
	
	public void inOrderTraversalRecursive(BinaryTreeNode<Integer> node){
		if(node == null)
			return;

		inOrderTraversalRecursive(node.getLeftNode());
		System.out.println(node.getData());
		inOrderTraversalRecursive(node.getRightNode());
	}
	
	public void inOrderTraversal(BinaryTreeNode<Integer> node){
		Stack<BinaryTreeNode<Integer>> stack = new Stack<BinaryTreeNode<Integer>>();
		while(node != null || !stack.isEmpty()){
			if(node != null){
				stack.push(node);
				node = node.getLeftNode();
				continue;
			}
		
			node = stack.pop();
			System.out.println(node.getData());
			node = node.getRightNode();
		}
	}
	
	public void postOrderTraversalRecursive(BinaryTreeNode<Integer> node){
		if(node == null)
			return;
		
		postOrderTraversalRecursive(node.getLeftNode());
		postOrderTraversalRecursive(node.getRightNode());
		System.out.println(node.getData());
	}
	
	//TODO
/*	public void postOrderTraversal(BinaryTreeNode<Integer> node){
		Stack<BinaryTreeNode<Integer>> stack = new Stack<BinaryTreeNode<Integer>>();
		BinaryTreeNode<Integer> lastPopNode = null;
		while(node != null || !stack.isEmpty()){
			if(node != null){
				stack.push(node);
				node = node.getLeftNode();
				continue;
			}
		
			if(lastPopNode != null)
				System.out.println(lastPopNode.getData());
			node = stack.pop();

			
			lastPopNode = node;
			node = node.getRightNode();
		}
	}*/
	
	public void levelOrderTraversal(BinaryTreeNode<Integer> node){
		if(node == null)
			return;
		
		Queue<BinaryTreeNode<Integer>> queue = new LinkedList<BinaryTreeNode<Integer>>();
		queue.add(node);
		while(!queue.isEmpty()){
			BinaryTreeNode<Integer> currentNode = queue.poll();
			System.out.println(currentNode.getData());
			if(currentNode.getLeftNode() != null)
				queue.add(currentNode.getLeftNode());
			if(currentNode.getRightNode() != null)
				queue.add(currentNode.getRightNode());
		}
	}
	
	public int getHeightRecursive(BinaryTreeNode<Integer> node){
		if(node == null)
			return 0;
		
		return 1 + Math.max(getHeightRecursive(node.getLeftNode()), getHeightRecursive(node.getRightNode()));
	}
	
	public int getHeight(BinaryTreeNode<Integer> node){
		if (node == null)
			return 0;
		int level = 1;
		Queue<BinaryTreeNode<Integer>> queue = new LinkedList<BinaryTreeNode<Integer>>();
		queue.add(node);
		queue.add(null);
		while(!queue.isEmpty()){
			node = (BinaryTreeNode<Integer>) queue.poll();
			if(node != null){
				if(node.leftNode != null)
					queue.add(node.leftNode);
				if(node.rightNode != null)
					queue.add(node.rightNode);
			} else {
				// End of Previous Level
				if(!queue.isEmpty()){ // Ensure, Not adding marker "NULL" after all element. i.e, when queue is empty
					level++;
					queue.add(null);
				}
			}
		}
		return level;
	}
	
	static int diameter;
	public int getDiameterOfBinaryTree(BinaryTreeNode<Integer> root){
		return Math.max(diameter, getHeightForDiameter(root));
		// The reason why we are calculating height is, for sub-tree at bottom, maximum node is 3 - root, left, right
		// diameter of bottom sub-tree <= height of sub-tree
	}
	
	private int getHeightForDiameter(BinaryTreeNode<Integer> node){
		if(node == null)
			return 0;
		
		int leftHeight = getHeightForDiameter(node.getLeftNode());
		int rightHeight = getHeightForDiameter(node.getRightNode());
		
		// Check for diameter at each node 
		if(diameter < leftHeight + rightHeight +1)
			diameter = leftHeight + rightHeight +1;
		
		return 1 + Math.max(leftHeight, rightHeight);
	}
	
	public int levelWithMaxDataSum(BinaryTreeNode<Integer> node){
		int maxDataLevel = 0;
		int level = 1;
		int levelDataSum = 0;
		int maxDataSum = 0;
		Queue<BinaryTreeNode<Integer>> q = new LinkedList<BinaryTreeNode<Integer>>();
		q.add(node);
		q.add(null);
		while(!q.isEmpty()){
			node = q.poll();
			if(node == null){
				// Level ends here
				if(levelDataSum > maxDataSum){
					maxDataSum = levelDataSum;
					maxDataLevel = level;
				}
				if(!q.isEmpty()){ // Ensure - Not adding in empty queue
					level++;
					levelDataSum = 0;
					q.add(null); // Marker for nextLevel end
				}
				continue;
			}
			
			levelDataSum += node.getData();
			if(node.getLeftNode() != null)
				q.add(node.getLeftNode());
			if(node.getRightNode()!= null)
				q.add(node.getRightNode());
		}
		System.out.println("Max Sum : " + maxDataSum);
		return maxDataLevel;
	}
	
	public void printAllPaths(BinaryTreeNode<Integer> root){
		int maxLengthOfPath = getHeightRecursive(root);
		BinaryTreeNode<Integer>[] path = new BinaryTreeNode[maxLengthOfPath];
		getPaths(root, path, 0);
	}
	
	// PreOrderTransversal approach
	private void getPaths(BinaryTreeNode<Integer> node, BinaryTreeNode<Integer>[] path, int lengthOfPath){
		if(node == null)
			return;

		path[lengthOfPath] = node;
		lengthOfPath ++;
		
		// If condition is essential to avoid duplicate when node have no children
		if(node.leftNode == null && node.rightNode == null)
			printPath(path, lengthOfPath);
		else{
			if(node.getLeftNode() != null)
				getPaths(node.getLeftNode(), path, lengthOfPath);
			if(node.getRightNode() != null)
				getPaths(node.getRightNode(), path, lengthOfPath);
		} 
	}
	
	private void printPath(BinaryTreeNode<Integer>[] path, int length){
		for(int i = 0; i< length; i ++)
			System.out.print(path[i].getData() + ", ");
		
		System.out.println();
	}

	@Override
	public boolean hasPathSum(BinaryTreeNode<Integer> node, int sum){
		if(sum == 0)
			return true;
		if(node == null)
			return sum == 0;
		
		int subSum = sum - node.getData(); 
		return hasPathSum(node.getLeftNode(), subSum) || hasPathSum(node.getRightNode(), subSum);
	}
	
	public void mirroringOfBinaryTree(BinaryTreeNode<Integer> node){
		if(node == null)
			return;
		if(node.leftNode != null || node.rightNode != null){
			BinaryTreeNode<Integer> temp = node.leftNode;
			node.setLeftNode(node.rightNode);
			node.setRightNode(temp);
		}
		
		mirroringOfBinaryTree(node.leftNode);
		mirroringOfBinaryTree(node.rightNode);
	}
	
	@Override
	public void deleteNodeGivenData(int dataToDelete, BinaryTreeNode<Integer> node){
		if(node.getData() == dataToDelete){
			node = null;
			return;
		}
		
		BinaryTreeNode<Integer> parentOfNodeToDelete = null;
		BinaryTreeNode<Integer> nodeToDelete = null;
		boolean isLeftChildToDelete = false;
		
		BinaryTreeNode<Integer> parentOfLastNode = null;
		BinaryTreeNode<Integer> lastNode = null;
		boolean isLeftChildLastNode = false;
		
		Queue<BinaryTreeNode<Integer>> q = new LinkedList<BinaryTreeNode<Integer>>();
		q.offer(node);
		
		while(!q.isEmpty()){
			BinaryTreeNode<Integer> currentNode = q.poll();
			if(currentNode.leftNode != null){
				if(currentNode.leftNode.data == dataToDelete){
					parentOfNodeToDelete = currentNode;
					nodeToDelete = currentNode.leftNode;
					isLeftChildToDelete = true;
				}
				q.offer(currentNode.leftNode);
			}
			
			if(currentNode.rightNode != null){
				if(currentNode.rightNode.data == dataToDelete){
					parentOfNodeToDelete = currentNode;
					nodeToDelete = currentNode.rightNode;
					isLeftChildToDelete = false;
				}
				q.offer(currentNode.rightNode);
			}
			
			if(currentNode.rightNode == null && currentNode.leftNode != null && currentNode.leftNode.leftNode == null
					&& currentNode.leftNode.rightNode == null){
				parentOfLastNode = currentNode;
				lastNode = currentNode.leftNode;
				isLeftChildLastNode = true;
			}
			
			if(currentNode.rightNode != null && currentNode.rightNode.leftNode == null 
					&& currentNode.rightNode.rightNode == null){
				parentOfLastNode = currentNode;
				lastNode = currentNode.rightNode;
				isLeftChildLastNode = false;
			}
		}
		
		if(parentOfNodeToDelete == null){
			System.out.println(" Element not found");
			return;
		}

		if(parentOfNodeToDelete == parentOfLastNode && isLeftChildLastNode == isLeftChildToDelete){
			parentOfNodeToDelete = null;
			return;
		}
		
		lastNode.setLeftNode(nodeToDelete.leftNode);
		lastNode.setRightNode(nodeToDelete.rightNode);
		
		if(isLeftChildToDelete)
			parentOfNodeToDelete.setLeftNode(lastNode);
		else
			parentOfNodeToDelete.setRightNode(lastNode);
		
		if(isLeftChildLastNode)
			parentOfLastNode.setLeftNode(null);
		else
			parentOfLastNode.setRightNode(null);

	}

	@Override
	public int DiameterOfTree(BinaryTreeNode<Integer> root){
		return DiameterOfTreeRecursively(root)[1];
	}
	
/*	Returns an array which stores
		At index 0 -- height of Node
		At index 1 -- Maximum diameter possible within sub-tree root as present node*/
	private int[] DiameterOfTreeRecursively(BinaryTreeNode<Integer> node){
		if(node == null)
			return new int[]{0, 0};
		
		int[] leftChildResult = DiameterOfTreeRecursively(node.leftNode);
		int[] rightChildResult = DiameterOfTreeRecursively(node.rightNode);
		
		int[] result = new int[2];
		result[0] = 1 + Math.max(leftChildResult[0], rightChildResult[0]);
		result[1] = Math.max(1+leftChildResult[0]+rightChildResult[0], 
				Math.max(leftChildResult[1], rightChildResult[1]));
		
		return result;
	}
	
	public static void main(String[] args) {
		
//
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(Integer.MAX_VALUE +1 );
//		
//		System.out.println(Integer.MIN_VALUE);
//		System.out.println(Integer.MIN_VALUE -1);
		
		BinaryTreeOperations object = new BinaryTreeOperations();
		BinaryTreeNode<Integer> root = object.constructBinaryTree();
		object.deleteNodeGivenData(2, root);
		object.levelOrderTraversal(root);
		
		//System.out.println(object.hasPathSum(root, 8));
		//object.mirroringOfBinaryTree(root);
		//object.levelOrderTraversal(root);
	}

}
