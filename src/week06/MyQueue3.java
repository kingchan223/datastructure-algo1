package week06;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//선형 큐의 문제점을 보안한 원형 큐
public class MyQueue3<T> {
	int qSize = 5;
	T[] queue = (T[])new Object[qSize];
	int front = 0;
	int rear = 0;
	
	public boolean enqueue(T data) {
		if((rear+1)%qSize==front)
			return false;
		else {
			queue[rear] = data;    
			rear = (rear+1)%qSize;
			return true;
		}
	}
	
	public T dequeue() {
		//front와 rear가 같다는 것은 큐가 비어 있다는 것. 이것은 즉 큐에 삭제할 데이터가 없다는 것임.
		if(front == rear)
			return null;
		else {
			T retVal = queue[front];
			queue[front] = null;
			front = (front+1)%qSize;
			return retVal;
		}
	}

	public int sizeOf() {
		System.out.println("Array Size :"+queue.length);
		return (rear>=front)? (rear-front) : (rear-front+qSize);
	}
	
	public void showQueue() {
		for(int i=0; i<qSize; i++) {
			System.out.print(queue[i]+" - ");
		}
		System.out.println();
	}
	
	public int getRear(){
		return rear;
	}

	public static void main(String[] args) {	
		
//---------------<MyClass타입 Queue>------------------------
		
		MyQueue3<MyClass> q = new MyQueue3<MyClass>();
		q.enqueue(new MyClass(1, "Lee"));
		q.enqueue(new MyClass(2, "kim"));
		q.enqueue(new MyClass(3, "kang"));
		q.enqueue(new MyClass(4, "park"));
		q.showQueue();
		System.out.println("q.sizeOf:"+q.sizeOf());
		System.out.println("q.dequeue(): "+q.dequeue());
		System.out.println("q.dequeue(): "+q.dequeue());
		System.out.println("q.dequeue(): "+q.dequeue());
		q.showQueue();
		q.enqueue(new MyClass(1, "Lee"));
		q.enqueue(new MyClass(2, "kim"));
		q.enqueue(new MyClass(3, "kang"));
		q.enqueue(new MyClass(4, "park"));
		q.showQueue();
		System.out.println("q.sizeOf:"+q.sizeOf());
		System.out.println();
		System.out.println();
		System.out.println();
		
		
//---------------<String타입 Queue>------------------------
		
		
		MyQueue3<String> sq = new MyQueue3<String>();
		sq.enqueue("Lee");
		sq.enqueue("kang");
		sq.enqueue("kim");
		sq.enqueue("park");
		sq.showQueue();
		System.out.println("sq.sizeOf:"+sq.sizeOf());
		System.out.println("sq.dequeue(): "+sq.dequeue());
		System.out.println("sq.dequeue(): "+sq.dequeue());
		System.out.println("sq.dequeue(): "+sq.dequeue());
		sq.showQueue();
		sq.enqueue("Lee");
		sq.enqueue("kang");
		sq.enqueue("kim");
		sq.enqueue("park");
		sq.showQueue();
		System.out.println("sq.sizeOf:"+sq.sizeOf());
	}
}
