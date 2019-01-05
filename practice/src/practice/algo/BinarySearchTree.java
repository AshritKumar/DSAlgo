package practice.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> {
	int height = -1;
	int lvl = -1;
	BSTNode<T> root;
	// if converted to list head and tail variables
	BSTNode<T> head,tail;
	public void insertDataItr(T data) {
		BSTNode<T> newNode = new BSTNode<>(data);
		if(root == null) {
			root = newNode;
			this.height = newNode.height;
		} else {
			BSTNode<T> temp = root;
			while(temp != null) {
				if(data.compareTo(temp.getData()) <= 0 ) {
					if(temp.leftChild == null) {
						temp.leftChild = newNode;
						// code to calculate level, working
						//new height of the left node created will be the parent nodes level + 1
						temp.leftChild.lvl = temp.lvl +1;
						lvl = temp.leftChild.lvl;
						break;
					}
					temp = temp.leftChild;
				} else {
					if(temp.rightChild == null) {
						temp.rightChild = newNode;
						// code to calculate levele, working
						temp.rightChild.lvl = temp.lvl +1;
						lvl = temp.rightChild.lvl;
						break;
					}
					temp = temp.rightChild;
				}
			}
			// code to calculate height, not working
			/*if(temp.leftChild == null || temp.rightChild == null) {
				temp.height = Math.max(temp.leftChild!= null ? temp.leftChild.height: -1, temp.rightChild!= null ? temp.rightChild.height: -1)+1;
				this.height += temp.height;
			}*/
		}
	}
	
	public void insertData(T data) {
		if(root == null) {
			root = new BSTNode<>(data);
			lvl = root.lvl;
			++height;
		}else {
			 //BSTNode<T> nod = recursiveInsert(data, root);
			 //System.out.println(nod);
			insertDataItr(data);
		}
	}
	
	@SuppressWarnings("unused")
	public void delete(T data) {
		BSTNode<T> cur = this.root;
		BSTNode<T> parent = null;
		boolean isRight = false;
		boolean isRoot = false;
		while(cur != null) {
			if(data.compareTo(cur.getData()) < 0) {
				parent = cur;
				cur = cur.leftChild;
				isRight = false;
			} else if(data.compareTo(cur.getData()) > 0) {
				parent = cur;
				cur = cur.rightChild;
				isRight = true;
			} else {
				break;
			}
		}
		if(cur == null) {
			System.out.println("Node not Found ! ");
		//	return;
		} else {
			if(cur == root)
				isRoot = true;
			// if node is found we have 3 cases, 
			//1. if its a lead node
			if(cur.leftChild == null && cur.rightChild == null) {
				// if its root node we are deleting
				if(isRoot) {
					root = null;
					this.lvl --;
				} else {
					if(isRight)
						parent.rightChild = null;
					else
						parent.leftChild = null;
				}
			}
			// 2. If node has only one child
			if(cur.leftChild == null || cur.rightChild == null) {
				BSTNode<T> nxtNode = null;
				if(cur.rightChild != null) {
					nxtNode = cur.rightChild;
				} else {
					nxtNode = cur.leftChild;
				}
				// If root is being deleted
				if(isRoot) 
					root = nxtNode;
				else {
					if(isRight)
						parent.rightChild = nxtNode;
					else
						parent.leftChild = nxtNode;
				}
				// decrease level of nxtNode if its not null
				if(nxtNode != null)
					nxtNode.lvl--;
			} else {
				//3. if node has both left and right node. 
				//Find the minimum in right sub tree, copy the min value to the cur node. Delete the min node
				// min node in right sub tree will not have a left child, so the deletion is reduced to case 1 or case 2
				
				// find the min node in right sub tree
				BSTNode<T> minNode = cur.rightChild;
				BSTNode<T> minNodeParent = cur;
				while(minNode.leftChild != null) {
					minNodeParent = minNode;
					minNode = minNode.leftChild;
				}
				System.out.println("Found min node "+minNode.getData()+" Parent of min node "+minNodeParent.getData());
				// copy the minNode data to cur node
				cur.setDate(minNode.getData());
				// there can be two cases, 1. if the minNode is immediate right child of cur node
				//2. minNode is the let child(or grand child) of minNode.rightChild

				// case 1.
				if(cur.rightChild == minNode) {
					cur.rightChild = minNode.rightChild;
					
				} else {
					// case 2. if minNode is left child of cur.rightChild, set minNodeParent.leftChild = minNode.rightChild
					minNodeParent.leftChild = minNode.rightChild;
				}
			}
			System.out.println(data+" deleted...");
		}
	}
	
	private BSTNode<T> deleteNodeRec(BSTNode<T> cur, T data){
		if(cur == null)
			return cur;
		// search for node
		if(data.compareTo(cur.getData()) < 0)
			cur.leftChild = deleteNodeRec(cur.leftChild, data);
		else if(data.compareTo(cur.getData()) > 0)
			cur.rightChild = deleteNodeRec(cur.rightChild, data);
		else {
			System.out.println("Found !"+cur.getData());
			// If node found, and is a leaf node
			if(cur.leftChild == null && cur.rightChild == null)
				cur = null;
			// only one child
			else if(cur.leftChild == null || cur.rightChild == null) {
				BSTNode<T> temp = cur.rightChild != null ? cur.rightChild : cur.leftChild;
				cur = temp;
			}
			// two children
			else {
				BSTNode<T> minNode = findMinNodeRight(cur.rightChild);
				cur.setDate(minNode.getData());
				cur.rightChild = deleteNodeRec(cur.rightChild, minNode.getData());
			}
			
		}
		return cur;
	}
	
	public void deleteRec(T data) {
		deleteNodeRec(this.root, data);
	}
	private BSTNode<T> findMinNodeRight(BSTNode<T> rc) {
		while(rc.leftChild != null)
			rc = rc.leftChild;
		return rc;
	}

	private BSTNode<T> recursiveInsert(T data, BSTNode<T> node) {
		if(node == null)
			node = new BSTNode<>(data);
		else if(data.compareTo(node.getData()) <=0) {
			node.leftChild = recursiveInsert(data,node.leftChild);
		}
		else {
			node.rightChild = recursiveInsert(data, node.rightChild);
		}
		return node;
	}

	private boolean search(BSTNode<T> node, T data) {
		if(node == null)
			return false;
		if(node.getData().equals(data))
			return true;
		if(data.compareTo(node.getData()) <= 0)
			return search(node.leftChild, data);
		if(data.compareTo(node.getData()) > 0)
			return search(node.rightChild, data);
		return false;
	}
	
	public boolean  search(T data) {
		return search(root, data);
	}
	
	public int findHeight(BSTNode<T> node) {
		if(node == null)
			return -1;
		else {
			int lh = findHeight(node.leftChild);
			int rh = findHeight(node.rightChild);
			return Math.max(lh,rh) +1;
		}
	}
	//traverses level by level
	public void levlOrderTraversal() {
		// no of levels is lvl+1
		int level = this.lvl+1;
		for(int i=1; i<=level; i++)
			levlOrderTraversal(root,i);
					
	}
	private void levlOrderTraversal(BSTNode<T> node, int lvl) {
		if(node == null)
			return;
		if(lvl == 1)
			System.out.print(node.getData()+" ");
		else if(lvl > 1) {
			levlOrderTraversal(node.leftChild, lvl-1);
			levlOrderTraversal(node.rightChild, lvl-1);
		}
		
	}
	// this level order traversal function uses queue
	public void recLevelTraverseQ() {
		if(root == null)
			return;
		BSTNode<T> current = root;
		LinkedList<BSTNode> que = new LinkedList<>();
		que.add(current);
		while(!que.isEmpty()) {
			System.out.print(" "+que.getFirst().getData());
			if(que.getFirst().leftChild !=null)
				que.add(que.getFirst().leftChild);
			if(que.getFirst().rightChild !=null)
				que.add(que.getFirst().rightChild);
			que.remove();
			
		}
	}
	
	// recursive in order traversal
	public void inOrderRec(BSTNode<T> node) {
		if(node == null)
			return;
		inOrderRec(node.leftChild);
		System.out.print(node.getData()+" ");
		inOrderRec(node.rightChild);
	}
	public void inOrderRec() {
		inOrderRec(root);
	}
	
	// iterative pre order traversal Root, Left, Right
	
	public void iterPreOrder() {
		if(root == null)
			return;
		Stack<BSTNode<T>> st = new Stack<>();
		st.push(root);
		while(st.empty() == false) {
			BSTNode<T> nd = st.pop();
			System.out.print(nd.getData()+" ");
			if(nd.rightChild != null)
				st.push(nd.rightChild);
			if(nd.leftChild != null)
				st.push(nd.leftChild);
		}
	}
	
	// POst order iterative Left, Right, Root
	public void itrPostOrder() {
		if(root == null)
			return;
		Stack<BSTNode<T>> st = new Stack<>();
		BSTNode<T> current = root;
		while(current != null || ! st.isEmpty()) {
			if(current != null) {
				st.push(current);
				current = current.leftChild;
			} else {
				BSTNode<T>temp = st.peek().rightChild;
				if(temp != null) {
					current = temp;
				} else {
					temp = st.pop();
					System.out.print(temp.getData()+" ");
					while(!st.isEmpty() && (st.peek().rightChild == null || temp.getData().equals(st.peek().rightChild.getData()))) {
						temp = st.pop();
						System.out.print(temp.getData()+" ");
					}
				}
			}
		}
		
	}
	
// recursive Post order traversal
	public void postOrderRec(BSTNode<T> node) {
		if(node == null)
			return;
		postOrderRec(node.leftChild);
		postOrderRec(node.rightChild);
		System.out.print(node.getData()+" ");
	}
	public void postOrderRec() {
		postOrderRec(root);
  }
	// Traverse in order fashion and make a list
	public BSTNode<T> asList(){
		// define few variables required for converting tree to list
		BSTNode<T> prv = null;
		BSTNode<T> curr = root;
		Stack<BSTNode<T>> st = new Stack<>();
		while(curr != null || !st.isEmpty()) {
			// traverse till left most node
			while(curr != null) {
				st.push(curr);
				curr = curr.leftChild;
			}
			// curr is null at this point, so pop the top
			curr = st.pop();
			// create a new node, do not change the curr node, since it will change the binary tree structure
			BSTNode<T> currNew = new BSTNode<>(curr.getData());
			// If prv and head are null, current node will be head node since we have reached the left most node of tree
			if(prv == null && head == null) {
				head = currNew;
				head.leftChild = null;
			} 
			// if prv is not null, make the current new node's left point to prv and prv's right to current new nodes left
			if(prv != null) {
				//BSTNode<T> currNew = new BSTNode<>(curr.getData());
				prv.rightChild = currNew;
				currNew.leftChild = prv;
			}
			// make curr as prv node and move curr to right child
			prv = currNew;
			curr = curr.rightChild;
			
		}
		// at the prv will be at the end of the list ie, tail of list. So mark it as tail
		tail = prv;
		return head;
	}
	
	public void printList() {
		if(head == null)
			return;
		BSTNode<T> temp = head;
		StringBuilder sb = new StringBuilder();
		sb.append("In Order List View: [");
		while(temp!= null) {
			sb.append(temp.getData());
			temp = temp.rightChild;
			if(temp != null)
				sb.append(" , ");
		}
		sb.append("]");
		System.out.println(sb.toString());
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insertData(55);
		bst.insertData(66);
		bst.insertData(56);
		bst.insertData(60);
		bst.insertData(80);
		bst.insertData(73);
		bst.insertData(78);
		bst.insertData(68);
		bst.insertData(11);
		bst.insertData(13);
		bst.insertData(12);
		bst.insertData(14);
		bst.insertData(1);
		bst.insertData(91);
		bst.insertData(131);
		bst.insertData(9);
		bst.insertData(99);
		bst.insertData(6);
		bst.insertData(71);
		bst.insertData(70);
		bst.insertData(72);
		//System.out.println(bst.root);
		//System.out.println(bst.root.getLeftChild().getRightChild().getLeftChild());
		//System.out.println(bst.search(12));
		BTreePrinter.printNode(bst.root);
		System.out.println(bst.lvl);
		/*System.out.println("Height: "+ bst.findHeight(bst.root));
		System.out.println("Levels : "+(bst.lvl+1));
		System.out.println("Level Order traversal ");
		bst.recLevelTraverseQ();
		System.out.println("\n\nIn order Traversal \n");
		bst.inOrderRec();
		System.out.println("\n Pre Order Traversal: \n");
		bst.iterPreOrder();
		System.out.println("\n Post Order Traversal: \n");
		bst.itrPostOrder();
		System.out.println("**");
		bst.postOrderRec();
		bst.asList();
		System.out.println();
		bst.printList();*/
		//bst.deleteRec(99);
		//bst.deleteRec(91);
		bst.deleteRec(11);
		//bst.delete(9);
		//bst.delete(73);
		//bst.delete(78);
		//bst.delete(68);
		//bst.delete(66);
		BTreePrinter.printNode(bst.root);
		
	}
}
class BSTNode<T extends Comparable<T>> implements Comparable<T>{
	int height = 0;
	int lvl = 0;
	private T data;
	BSTNode<T> leftChild;
	BSTNode<T> rightChild;
	public BSTNode(T data) {
		this.data = data;
	}
	public T getData() {
		return this.data;
	}
	public void setDate(T data) {
		this.data = data;
	}
	public BSTNode<T> getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(BSTNode<T> leftChild) {
		this.leftChild = leftChild;
	}
	public BSTNode<T> getRightChild() {
		return rightChild;
	}
	public void setRightChild(BSTNode<T> rightChild) {
		this.rightChild = rightChild;
	}
	
	@Override
	public String toString() {
		return "[ D: "+this.data+" L: "+leftChild+" R: "+rightChild+" ]";
	}
	@Override
	public int compareTo(T o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

//------------------------------------------------------//

class BTreePrinter<T extends Comparable<T>> {

    public static <T extends Comparable<?>> void printNode(BSTNode root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<BSTNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<BSTNode> newNodes = new ArrayList<BSTNode>();
        for (BSTNode node : nodes) {
            if (node != null) {
                System.out.print(node.getData());
                newNodes.add(node.leftChild);
                newNodes.add(node.rightChild);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).leftChild != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).rightChild != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<T>> int maxLevel(BSTNode<T> node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.leftChild), BTreePrinter.maxLevel(node.rightChild)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}
