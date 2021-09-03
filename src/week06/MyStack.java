package week06;


public class MyStack {

	MyArrayList stack = new MyArrayList(3);
	int top = 0;

	// 스택은 배열의 가장 끝단에만 데이터를 삽입할 수 있음
	public void push(String data) {
		stack.addLast(data);
		top = stack.sizeOf();
//		System.out.println(top);
	}

	// 스택의 삭제는 가장 끝단의 데이터만 삭제 가능함.
	public String pop() {
		if (stack.sizeOf() == 0) {
			return null;
		} else {
			// 마지막 요소를 삭제해야하므로
			// 데이터의 개수를 나타내는 top-1을 remove 메소드의 인자로 넣어준다.
			String retVal = stack.remove(top - 1);
			top = stack.sizeOf();
			return retVal;
		}
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

