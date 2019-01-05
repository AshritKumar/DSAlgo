package practice.algo;

import java.util.Random;

// Follow implementation custom of LinkedList is double ended and doubly connected linked list
/* 
 * This has following methods
 * insertHead(): inserts at head position, updates the next link of new node to current head, prv link to NULL (done by default) and marks new node as head
 * insertTail(): inserts at tail position, updates the prv link of new node to current tail, next link to NULL (done by default) and marks new node as tail
 * insert(data,index) : inserts at any given pos
 * deleteAtHead(): Makes the current head as currentHead.nextNode and discards current head
 * deleteAtTail(): Makes the current tail as tail.prvNode and discards the current tail
 * delete(index): deletes at any given pos
 * reverseList(): Reverses the links in linked List
 * printReverse(): prints the list in reverse order, dosen't change links*/

public class LinkedList<T extends Comparable<T>> {
	
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	public void insertAtHead(T data) {
		Node<T> newNode = new Node<>();
		newNode.setData(data);
		newNode.nextNode = head;
		// update previous Link of current head to new node
		if(this.head != null) {
			this.head.prvNode = newNode;
		}
		this.head = newNode;
		if(tail == null) {
			// If this s the first node head and tail are same
			this.tail = newNode;
		}
		++size;
	}
	
	public void insertAtTail(T data) {
		Node<T> newNode = new Node<>();
		newNode.setData(data);
		// if this is a new node
		if(this.head == null) {
			this.head = newNode;
			this.tail = newNode;
		} if(tail != null) {
			//update prv and next links
			newNode.prvNode = tail;
			this.tail.nextNode = newNode;
			this.tail = newNode;
		}
		++size;
		
	}
	
	// insert at index given
	public void insert(T data, int index) {
		if(index > size || index < 0) {
			System.err.println("Invalid index !!! size - "+size+", index "+index);
			throw new IndexOutOfBoundsException();
		}
		if(index == 0) {
			insertAtHead(data);
			return;
		}
		if(index == size) {
			insertAtTail(data);
			return;
		}
		Node<T> newNode = new Node<>();
		newNode.setData(data);
		Node<T> temp = this.head;
		// loop till n-1th node
		for(int k=0; k<index-1; k++) {
			temp = temp.nextNode;
		}
		newNode.nextNode = temp.nextNode;
		temp.nextNode.prvNode = newNode;
		newNode.prvNode = temp;
		temp.nextNode = newNode;
		++size;
		
	}
	// has a long code for better understanding, use sortedInsert instead
	private void dn_sortedInsert(T data) {
		//int i = 0;
		Node<T> newNode = new Node<>();
		newNode.setData(data);
		Node<T> curr = this.head;
		// if list is null or if newNode data is less than current head
		if(curr == null || curr.data.compareTo(data) > 0) {
			insertAtHead(data);
			//this.head = curr;
			return;
		}
		Node<T> prv = curr.prvNode;
		//-------------------------------------------------//
		//-------This approach also works, but below code is bit cleaner. Both the loops dose same thing--------//
		/*while(curr != null) {
			if(curr.data.compareTo(data)>0) {
				newNode.nextNode = curr;
				curr.prvNode = newNode;
				newNode.prvNode = prv;
				prv.nextNode = newNode;
				break;
			} 
			prv = curr;
			curr = curr.nextNode;
			//if(curr != null)
				//prv = curr.prvNode;
		}
		if(curr == null)
			insertAtTail(data);;*/
		
		//-------------------------------------------------------//
		//Loop until a position where node data is greater than element in list
		while(curr != null && curr.data.compareTo(data) < 0) {
			prv = curr;
			curr = curr.nextNode;
		}
		// if such position is not there curr will be null ie, newNode data is greatest so insert at tail
		if(curr == null)
			insertAtTail(data);
		else {
			//else insert data at position before the curr node and after prv node
			prv.nextNode = newNode;
			newNode.prvNode = prv;
			newNode.nextNode = curr;
			curr.prvNode = newNode;
		}
	}
	
	public void sortedInsert(T data) {
		int i = 0;
		Node<T> newNode = new Node<>();
		newNode.setData(data);
		Node<T> curr = this.head;
		// if list is null
		if(curr == null || curr.data.compareTo(data) > 0) {
			insertAtHead(data);
			return;
		}
		while(curr != null && curr.data.compareTo(data) < 0) {
			i++;
			curr = curr.nextNode;
		}
		if(i == size)
			insertAtTail(data);
		else {
			insert(data, i);
		}
	}
	
	public void sort() {
		Node<T> curr = this.head;
		this.head = null;
		this.tail = null;
		this.size = 0;
		while(curr != null) {
			Node<T> temp = curr.nextNode;
			curr.nextNode = null; curr.prvNode = null;
			sortedInsert(curr.data);
			curr = temp;
		}
	}
	
	private Node<T> mergeSort(Node<T> curr) {
		if(curr == null || curr.nextNode == null)
			return curr;
		// get mid node
		Node<T> mid = getMidNode(curr);
		// get next node of mid node, which will be start of next half of list.
		Node<T> nxtOfMid = mid.nextNode;
		// set mid.next to null to remove connection between to lists
		mid.nextNode = null;
		nxtOfMid.prvNode = null;
		Node<T> left = mergeSort(curr);
		Node<T> right = mergeSort(nxtOfMid);
		Node<T> sortedList = mergeLists(left,right);
		return sortedList;
	}
	
	private Node<T> mergeLists(Node<T> left, Node<T> right) {
		if(left == null)
			return right;
		if(right == null)
			return left;
		Node<T> sl = null,h = null;
		if(left != null && right != null) {
			if(left.data.compareTo(right.data) <= 0) {
				sl = left;
				left = sl.nextNode;
			} else {
				sl = right;
				right = sl.nextNode;
			}
			h = sl;
		}
		
		while(left != null && right != null) {
			if(left.data.compareTo(right.data) <= 0) {
				sl.nextNode = left;
				left.prvNode = sl;
				sl = left;
				left = sl.nextNode;
			} else {
				sl.nextNode = right;
				right.prvNode = sl;
				sl = right;
				right = sl.nextNode;
			}
		}
		if(left == null) {
			sl.nextNode = right;
			right.prvNode = sl;
		}
		if(right == null) {
			sl.nextNode = left;
			left.prvNode = sl;
		}
		Node<T> tail = sl;
		while(tail.nextNode != null)
			tail = tail.nextNode;
		this.tail = tail;
		return h;
	}
	
	public Node<T> mergeSort() {
		head = this.mergeSort(head);
		return head;
	}

	private Node<T> getMidNode(Node<T> curr) {
		if(curr == null)
			return curr;
// define two node refs slow pointer sp and fast pointer fp, move if curr is not null, move fp by twp positions and sp by one position, until fp is null. Once fp reached end sp will be in mid of list
		Node<T> sp = curr;
		Node<T> fp = curr.nextNode;
		while(fp != null) {
			fp  = fp.nextNode;
			if(fp != null) {
				fp = fp.nextNode;
				sp = sp.nextNode;
			}
		}
		return sp;
	}

	public T deleteAtHead(){
		Node<T> curr = this.head;
		T data = curr.data;
		this.head = curr.nextNode;
		// to help GC make nxt and data null
		curr.nextNode = null;
		curr.data = null;
		--size;
		return data;
	}
	
	public T deleteAtTail() {
		Node<T> last = this.tail;
		T data = last.data;
		this.tail = last.prvNode;
		last.data = null;
		last.nextNode = null;
		last.prvNode = null;
		--size;
		return data;
	}
	
	public T delete(int index) {
		if(index > size-1 || index < 0) {
			System.err.println("Invalid index");
			throw new IndexOutOfBoundsException("Invalid Index List size "+size);
		}
		if(index == 0)
			return deleteAtHead();
		if(index == size-1)
			return deleteAtTail();
		Node<T> temp = this.head;
		//loop till index-1 th pos
		for(int i=0; i<index-1; i++) {
			temp = temp.nextNode;
		}
		// nd is node to be deleted
		Node<T> nd = temp.nextNode;
		temp.nextNode = nd.nextNode;
		nd.nextNode.prvNode = temp;
		T data= nd.data;
		// help GC
		nd.data = null;
		nd.nextNode = null;
		nd.prvNode = null;
		--size;
		return data;
	}
	
	public Node<T> getHead(){
		return this.head;
	}
	
	public Node<T> getTail(){
		return this.tail;
	}
	
	public T get(int index) {
		if(index > size-1 || index < 0) {
			System.out.println("Invalid index !!!");
			return null;
		}
		Node<T> curr = this.head;
		
		for(int i=0; i<index; i++) {
			if(curr != null)
				curr = curr.nextNode;
		}
		return curr.data;
	}
	
	public T searchElement(T item) {
		int index = 0;
		Node<T> curr = this.head;
		for(Node<T> i = curr; i != null; i = i.nextNode) {
			if(item.equals(i.data)) {
				System.out.println("Found @ index "+ index);
				return i.data;
			}	
			index++;
		}
		return null;
	}
	// reverses the links in the list
	public void reverseList() {
		Node<T> currNode = this.head;
		Node<T> prev = null;
		Node<T> nxt = null;
		this.tail = currNode;
		while(currNode != null) {
			// store next node ref in nxt
			nxt = currNode.nextNode;
			// update current nodes next and prv links to point to nxt and prev temp variables
			currNode.nextNode = prev;
			currNode.prvNode = nxt;
			//make prev to point to current node and move current node 
			prev = currNode;
			currNode = nxt;
		}
		this.head = prev;
	}
	// just print reverse without actually reversing links
	private void reversePrint(Node<T> h) {
		if(h ==  null)
			return;
		reversePrint(h.nextNode);	
		if(h!= null)
		System.out.print(h.data+" ");
	}
	
	public void printReverse() {
		reversePrint(this.head);
	}
	
	// prints in backward order from tail
	public void printFromTail() {
		Node<T> tail = this.tail;
		while(tail != null) {
			System.out.print(tail.data+" ");
			tail = tail.prvNode;
		}
	}

	
	public int getSize() {
		return size;
	}
	
	 static class Node<T> {
		private T data;
		private Node<T> nextNode;
		private Node<T> prvNode;
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		public Node<T> getNextNode() {
			return nextNode;
		}
		public void setNextNode(Node<T> nextNode) {
			this.nextNode = nextNode;
		}
		@Override
		public String toString() {
			return this.getData().toString();
		}
		public Node<T> getPrvNode() {
			return prvNode;
		}
		public void setPrvNode(Node<T> prvNode) {
			this.prvNode = prvNode;
		}
	}
	
	@Override
	public String toString() {
		StringBuffer res = new StringBuffer(); 
		res.append("{ ");
		Node<T> curr = this.head;
		while(true) {
			if(curr != null) {
				res.append(curr);
				if(curr.nextNode != null) {
					res.append(", ");
					curr = curr.getNextNode();
				}	
				else
					return res.append(" }").toString();
			} else return "{ }";
		}	
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		java.util.LinkedList<String> l = new java.util.LinkedList<>();
		l.addLast("");
		//l.get(9898);
		list.insert(111, 0);
		list.insertAtTail(67);
		list.insertAtHead(12);
		list.insertAtTail(8);
		//list.insertAtTail(3);
		//list.insertAtTail(88);
		//list.insertAtTail(5);
		/*list.insertAtHead(10);
		list.insertAtHead(20);
		list.insertAtHead(30);
		list.insertAtHead(40);
		list.insertAtHead(50);
		list.insertAtHead(60);*/
		System.out.println("Head element : "+list.getHead());
		System.out.println(list);
		System.out.println("List size: "+list.size);
		//System.out.println("Deleating "+list.deleteAtHead());
		System.out.println("Searchig "+ list.searchElement(30));
		//System.out.println(list.getHead()+" : "+list.getTail());
		//list.reverseList();
		//list.deleteAtTail();
		
		//list.insert(18, 7);
		System.out.println(list);
		System.out.println(list.get(1));
		System.out.println("Head - "+list.getHead()+" : Tail - "+list.getTail());
		//list.printFromTail();
		//list.printReverse();
		//System.out.println("Dleting.. "+ list.delete(1));
		//list.sort();
		//list.sortedInsert(66);
		//list.insert(45, 7);
		list.insertAtTail(89);
		System.out.println("---------------------------------------");
		list.mergeSort();
		System.out.println(list);
		list.printFromTail();
		
		
	}

}
