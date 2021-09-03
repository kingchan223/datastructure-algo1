package week06;

public class MyQueue2 {
	//앞의 널이 낭비되는 큐
	MyArrayList queue = new MyArrayList(3);
	int front = 0;
	int rear = 0;

	public void enqueue(String data) {
		queue.add(rear, data);
		rear = queue.sizeOf();
	}

	public String dequeue() {
		if (front == rear)// 큐가 비어있을
			return null;
		//앞 부분이 지나치게 늘어나는 문제
		else {
			String retVal = queue.get(front);
			queue.set(front, null);
			front++;
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

		MyQueue2 q = new MyQueue2();

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
