package week06;

public class MyQueue {
	//비효율적인 큐
	MyArrayList queue = new MyArrayList(3);
	final int front = 0;
	int rear = 0;

	public void enqueue(String data) {
		queue.add(rear, data);
		rear = queue.sizeOf();
	}

	public String dequeue() {
		if (front == rear)// 큐가 비어있을
			return null;
		else {
			String retVal = queue.remove(front);
			rear = queue.sizeOf();
			return retVal;
		}
	}
	
	public void showQueue() {
		System.out.println(queue.toString());
	}
	
	public int sizeOf() {
		return rear-front;
	}

	public static void main(String[] args) {

		MyQueue q = new MyQueue();

		q.enqueue("a");
		q.enqueue("b");
		q.enqueue("c");
		q.enqueue("d");
		q.enqueue("e");

		q.showQueue();
		System.out.println(q.sizeOf());
		
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.sizeOf());
		q.showQueue();
	}
}
