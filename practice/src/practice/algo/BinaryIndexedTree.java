package practice.algo;

// stores the ranges of sums

public class BinaryIndexedTree {
	int bit[] = null;
	
	public BinaryIndexedTree(int size) {
		bit = new int[size+1];
	}
	
	public BinaryIndexedTree() {
		
	}

	public int[] createBIT(int[] arr) {
		if(this.bit == null || this.bit.length < arr.length)
			this.bit = new int[arr.length+1];
		for(int i=0; i<arr.length; i++)
			addElemBIT(i,arr[i]);
		return bit;
		
	}

	public void addElemBIT(int indx, int ele) {
		int i = indx+1;
		while(i < bit.length) {
			bit[i] += ele;
			i = getNextIndex(i);
		}
	}
	
	public void updateBIT(int indx, int ele) {
		int i = indx+1;
		int diff = bit[i] - ele;
		if(i < bit.length)
			bit[i] = ele;
		i = getNextIndex(i);
		while(i < bit.length) {
			bit[i] += diff;
			i = getNextIndex(i);
		}
	}
	
	public int getSumTill(int indx) {
		int sum = 0;
		int i = indx+1;
		while(i > 0) {
			sum += bit[i];
			i = getParentIndex(i);
		}
		return sum;
	}
	
	public int getRangeSum(int l, int r) {
		int rSum = getSumTill(r);
		int lSum = getSumTill(l-1);
		return rSum-lSum;
	}

	private int getParentIndex(int i) {
		i = i - (i & -i);
		return i;
	}

	private int getNextIndex(int i) {
		i = i + (i & -i);
		return i;
	}
	
	public static void main(String[] args) {
		BinaryIndexedTree bit = new BinaryIndexedTree();
		int[] arr = new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		int bi[] = bit.createBIT(arr);
		
		for(int i : bi)
			System.out.print(i+" ");
		System.out.println();
		bit.updateBIT(2, 1);
		for(int i : bi)
			System.out.print(i+" ");
		System.out.println();
		System.out.println(bit.getSumTill(8));
		System.out.println(bit.getRangeSum(6, 7));
	}
}
