package practice.algo;

/* 
 * Binary Tree: Which has only two children a left child and a right child
 * Root Node: head Node
 * Leaf Node: A node with 0 children
 * Depth: Depth of a node is length of path from root to that node. Depth of root node is 0. The max depth of the tree is equal to height of the tree
 * Level: Nodes at same depth can be called as nodes at same level
 * Height of a Node: height of a node is number of edges in the longest path from that node to any of the leaf nodes. Height of the tree is height from the root node 
 * Strict/Proper binary tree: A tree where each node can have either 0 or 2 children
 * Complete Binary Tree: If all levels except the last level of the tree are completely filled.  If the last level isn't completely filled nodes should be far left as possible
 * Perfect Binary tree: If all levels are completely filled the its perfect BT
 * properties:
 * 	1.	Max number of nodes a binary tree can have at level i is 2^i
 * 	2.	Max number of nodes with height h  = 2^(h+1) - 1 or 2^(no. of levels) -1
 * 	3.	The height of a perfect binary tree with n nodes is log(n+1) - 1  or floor(log(n))  [derived from above eq: n = 2^(h+1) - 1)] // log with base 2
 * 	4.	With n nodes min height possible is floor(log(n))  [perfect binary tree] max height is n-1. Always try for min height
 * 	5.	Balanced Binary Tree: A binary tree is a balanced if difference between height of left and right subtree for every node is not more than k (K usually is 1). Diff = abs(h of lftSubTree - h of rgtSubTree)
 * 	6.	Height of an empty tree is -1. Height of a node with no children is 0
 *	7.	For a complete binary tree, nodes can also be stored in an array, for such an array root node is at 0th index and for every node at index i,
 *			left child index is 2i + 1, right child index is 2i + 2 [This structure also called heap]
 * */

public class BinaryTree<T> {
	TreeNode<T> root;
	int levels = -1;
	public void insertData(T data){
		TreeNode<T> newNode = new TreeNode<>(data);
		if(root == null) {
			root = newNode;
			++levels;
		} else {
			levels += root.insertNode(data);
		}
	}
	public TreeNode<T> getRootNode(){
		return root;
	}
	public void print() {
		TreeNode<T> temp = root;
		System.out.printf("%37d",temp.getData());
		int l = 30 , r = 10;
		while(temp != null) {
			T ld = temp.getLeftChild() != null ? temp.getLeftChild().getData()  : null;
			T rd = temp.getRightChild() != null ? temp.getRightChild().getData()  : null;
			System.out.format("%n%"+l+"s%-"+r+"d%d","", ld,rd);
			System.out.println();
			l = l-5;
			
			temp = temp.getLeftChild();
		}
	}
	public static void main(String[] args) {
		BinaryTree<Integer> bt = new BinaryTree<>();
		bt.insertData(10);
		bt.insertData(20);
		bt.insertData(30);
		//bt.insertData(40);
		//bt.insertData(50);
		//bt.insertData(60);
		//bt.insertData(70);
		//bt.insertData(80);
		System.out.println(bt.levels);
		System.out.println(bt.root.getLeftChild().height);
	
		//System.out.println(bt.root.getLeftChild());
		/*System.out.printf("%-40");
		System.out.print("A");
		System.out.printf("%-40s%s","A","S");*/
		bt.print();
	}

}

class TreeNode<T>{
	int height;
	private T data;
	private TreeNode<T> leftChild;
	private TreeNode<T> rightChild;
	public TreeNode(T data) {
		this.data = data;
	}
	public T getData() {
		return data;
	}
	public TreeNode<T> getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(TreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}
	public TreeNode<T> getRightChild() {
		return rightChild;
	}
	public void setRightChild(TreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}
	public int insertNode(T data) {
		TreeNode<T> temp = this;
		TreeNode<T> newNode = new TreeNode<>(data);
		while(temp != null) {
			if(temp.leftChild == null) {
				temp.leftChild = newNode;
				temp.height++;
				break;
			} else if(temp.getRightChild() == null){
				temp.rightChild = newNode;
				break;
			}
			temp = temp.getLeftChild();
		}
		return temp.height;
	}
	
	@Override
	public String toString() {
		return "[ Data: "+this.data+" Left: "+leftChild+" Right: "+rightChild+" ]";
	}
	
}
