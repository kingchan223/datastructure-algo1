package week07;


public class MyStack {
	
	MyLinkedList1 stack = new MyLinkedList1();
	
	
	// 스택은 배열의 가장 끝단에만 데이터를 삽입할 수 있음
	public void push(String data) {
		stack.addFirst(data);
	}

	// 스택의 삭제는 가장 끝단의 데이터만 삭제 가능함.
	public String pop() {
		if(stack.sizeOf()==0)
			return null;
		return stack.removeFirst();
	}

	public int sizeOf() {
		return stack.sizeOf();
	}

	public void showStack() {
		System.out.println(stack.toString());
	}

	public static void main(String[] args) {

		MyStack st = new MyStack();

		st.push("a");
		st.push("b");
		st.push("c");
		st.push("d");
		st.push("e");

		st.showStack();
		System.out.println(st.sizeOf());
		System.out.println(st.pop());
		System.out.println(st.pop());
		System.out.println(st.sizeOf());
		st.showStack();
	}
	private class Node {
		String data;
		Node link;

	}
}

