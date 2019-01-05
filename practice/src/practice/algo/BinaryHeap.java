package practice.algo;

import java.util.Hashtable;

public class BinaryHeap {
	Integer[] hArr;
	int indx = -1;
	public BinaryHeap(int size) {
		this.hArr = new Integer[size];
	}
	
	public boolean isEmpty() {
		return indx >=0;
	}
	
	public boolean isFull() {
		return hArr == null? false : indx == hArr.length-1 ? true : false;
	}
	
	public int size() {
		return indx+1;
	}
	
	private Integer getRoot() {
		return hArr[0];
	}
	
	public void insert(Integer data) {
		if(!isFull()) {
			hArr[++indx] = data;
			fixUp(indx);
		} else {
			throw new RuntimeException("Heap Full !!");
		}
	}
	
	public Integer deleteRoot() {
		int root = hArr[0];
		hArr[0] = hArr[indx];
		hArr[indx] = null;
		indx--;
		buildHeap(0,indx,hArr);
		return root;
	}
	// time complexity of heapify is O(n) worst case
	private void heapify(Integer[] data, int len) {
		for(int i=len/2-1; i>=0; i--) {
			buildHeap(i,len,data);
		}
		if(hArr == null || hArr.length < len)
			hArr = new Integer[data.length];
		hArr = data;
		indx = len-1;
	}
	
	public void heapify(Integer[] data) {
		heapify(data,data.length);
	}
	
	public void heapify() {
		heapify(hArr, size());
	}
	// worst case complexity of buildHeap O(log(n))
	private void buildHeap(int i, int length, Integer data[]) {
		int leftChild = 2*i+1;
		int rtChild = 2*i+2;
		int currentParent = i;
		//check if leftChild exists and is larger
		if(leftChild < length && data[leftChild] > data[currentParent]) 
			currentParent = leftChild;
		if(rtChild < length && data[rtChild] > data[currentParent])
			currentParent = rtChild;
		if(currentParent != i) {
			// swap i and current parent
			int temp = data[i];
			data[i] = data[currentParent];
			data[currentParent] = temp;
			
			// recursively build the sub tree upwards
			buildHeap(currentParent, length, data);
		}
		
	}
	// O(nLog(n)) worst case
	public void heapSort() {
		int tindx = indx;
		while(tindx >= 0) {
			Integer temp = hArr[tindx];
			hArr[tindx] = hArr[0];
			hArr[0] = temp;
			tindx--;
			buildHeap(0, tindx, hArr);
		}
		
	}

	private void fixUp(int curIndx) {
		// get parent index of new inserted item
		int parntIndx = (curIndx-1)/2;
		// if item is larger then the parent swap
		while(parntIndx >=0 && hArr[curIndx] > hArr[parntIndx]) {
			// swap parent and child
			int temp = hArr[curIndx];
			hArr[curIndx] = hArr[parntIndx];
			hArr[parntIndx] = temp;
			curIndx = parntIndx;
			parntIndx = (parntIndx-1)/2;
		}
		
	}
	
	public void printHeap() {
		for(int i=0; i<=indx; i++)
			System.out.print(hArr[i]+" ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		BinaryHeap bh = new BinaryHeap(10);
		/*bh.insert(10);
		bh.insert(15);
		bh.insert(27);
		bh.insert(5);
		bh.insert(82);
		bh.insert(21);*/
		bh.heapify(new Integer[] {10,15,27,5,82,21,60,12,100,-1,56,88,9,98,100,1000,987,987,5,6576,76,4,76,876,342,54,5456,8});
		System.out.print("Heap: \n");
		bh.printHeap();
		System.out.print("after deleting: "+bh.getRoot()+" \n");
		bh.deleteRoot();
		bh.printHeap();
		System.out.print("Sorted Heap: \n");
		bh.heapSort();
		bh.printHeap();
		bh.insert(67);
		System.out.print("Heapifyed again: \n");
		bh.heapify();
		bh.printHeap();
		Hashtable<String, String> s = new Hashtable<>();
		System.out.println(Integer.parseInt("7FFFFFFF", 16) );
		System.out.println(0b1010 & 2147483647);
		
	}

}
