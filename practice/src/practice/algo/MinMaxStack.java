package practice.algo;

public class MinMaxStack {
	
	LinkedList stack = new LinkedList<>();
	int mi,mx;
	int head = -1;
	
	public void push(Element e) {
		if(head == -1) {
			this.mi = e.data;
			this.mx = e.data;
			e.min = mi;
			e.max = mx;
			stack.insertAtHead(e);
			++head;
		} else {
			mi = e.data < mi ? e.data : mi;
			mx = e.data > mx ? e.data : mx;
			e.min = mi;
			e.max = mx;
			stack.insertAtHead(e);
			++head;
		}
	}
	
	public Element pop() {
		if(head == -1) {
			System.err.println("Stack Empty !");
			return null;
		}
		Element e = (Element)stack.deleteAtHead();
		mi = headElement().min ;
		mx = headElement().max;
		return e;

	}
	public Element headElement() {
		if(head != -1)
		return (Element)stack.getHead().getData();
		return null;
	}
	
	public static void main(String[] args) {
		MinMaxStack mxs = new MinMaxStack();
		mxs.push(new Element(100));
		mxs.push(new Element(12));
		mxs.push(new Element(31));
		mxs.push(new Element(8));
		mxs.pop();
		System.out.println(mxs.headElement());
		System.out.println(mxs.mi +" "+mxs.mx);
		System.out.println(mxs.stack);
		//GCD
		int a=48, b=96;
		while(b > 0) {
			int t = b;
			b = a%b;
			a = t;
		}
		System.out.println(a);
	}

}

 class Element<T> implements Comparable<Integer>{
 	Integer data;
	Integer min;
	Integer max;
	public Element(Integer data) {
		this.data = data;
	}
	public int compareTo(Integer o) {
		return this.compareTo(o);
	}
	@Override
	public String toString() {
		return "[Element: "+this.data+", Min: "+this.min+", Max: "+this.max+"]";
	}
}
