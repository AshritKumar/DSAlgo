package practice.algo;

public class Node1<T> {
	private T data;
	private Node1<T> nextNode;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Node1<T> getNextNode() {
		return nextNode;
	}
	public void setNextNode(Node1<T> nextNode) {
		this.nextNode = nextNode;
	}
	@Override
	public String toString() {
		return (String) this.getData();
	}
}
