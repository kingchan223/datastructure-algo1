package q5;

public class MyDoubleLinekList1 {

	Node first, last;
	int numOfNode;

	public MyDoubleLinekList1() {
		first = null;
		last = null;
		numOfNode = 0;
	}

	private boolean validIndex(int index) {
		if (index < sizeOf(first) && index >= 0)
			return true;
		else
			return false;
	}

	public String get(int index) {
		if (!validIndex(index))
			return null;
		Node temp = first;
		int i = 0;
		while (i < index) {// temp의 링크를 index번만큼 타고간다.
			temp = temp.next;
			i++;
		}
		return temp.data;
	}

	public void set(int index, String data) {
		if (!validIndex(index))
			return;
		Node temp = first;
		int i = 0;
		while (i < index) {
			temp = temp.next;
			i++;
		}
		temp.data = data;
	}

	public String removeFirst() {
		// first, last가 null이 아니고, 노드의 개수가 0보다 크다는 것은 삭제할 노드가 있다는 것임.
		// 세가지 경우가 다 된다는 것을 인식하기 위해 3가지의 조건을 and로 연결
		if (first != null && numOfNode > 0 && last != null) {
			String retVal = first.data;
			first = first.next;
			if (first != null) {// 노드를 삭제해도 다른 노드가 남았다면
				first.prev = null;// 이제 첫번째 된 노드의 prev가 노드가 아닌 null을 보게 한다.
			} else {// 만약 삭제할 노드가 하나남은 노드라면
				last = null;// 노드가 없으므로 last가 null을 보게 한다.
			}
			numOfNode--;
			return retVal;
		} else {// 삭제할 노드가 없다면
			return null;
		}
	}

	public String removeLast() {
		if (first != null && numOfNode > 0 && last != null) {
			String retVal = last.data;
			last = last.prev;
			if (last != null) {
				last.next = null;// 마지막 노드의 next는 null을 보게한다.
			} else {//노드를 삭제햤더니 List가 비었다면
				first = null;//first도 null을 보게한다.
			}
			return retVal;
		} else {
			return null;
		}

	}

	public String remove(int index){
		if (!validIndex(index))
			return null;
		if (numOfNode == 0 || index == 0)// 처음으로 노드를 추가하나느 경우이거나, 맨 앞에 추가하는 경우
			removeFirst();// addFirst호출
		if (index == numOfNode - 1)// 맨 마지막에 추가하려는 경우
			removeLast();// addLast호출
		int i = 0;
		Node temp = first;
		while(i<index-1) {
			temp = temp.next;
			i++;
		}
		//while문을 마치고 나면 temp는 삭제하려는 노드의 바로 앞 노드를 가리키고 있다.
		String retVal = temp.data;
		temp.next = temp.next.next;//temp의 next는 삭제하려는 노드를 건너뛰게 한다.
		temp.next.prev = temp;// 삭제하려는 노드를 건너뛴 노드가 temp를 가리키게 한다.
		return retVal;
	}
	// addLast
//	public void addLast(String data){
//		if(numOfNode == 0) {
//			addFirst(data);
//			return;
//		}
//		Node newNode = new Node(data, null, null);
//		last.next = newNode;
//		newNode.prev = last;
//		last = newNode;
//		numOfNode++;
//	}

	public void addLast(String data) {
		Node newNode = new Node(data, last, null);// 추가될 노드를 만들면서 newNode의 prev가 현재 last노드를 참조하게 한다.
		last = newNode;// 이제 새로 추가된 노드가 last이므로 last가 newNode를 참조하게 한다.
		if (first == null || numOfNode == 0)// 만약 처음으로 추가된 노드라면 first도 newNode를 참조하게 한다.
			first = newNode;
		else// 기존에 다른 노드가 있었다면
			newNode.prev.next = newNode;// newNode추가 이전의 last였던 노드의 next가 newNode를 참조하게 한다.
		numOfNode++;
	}
	// addfirst
//	public void addFirst(String data) {
//		Node newNode = new Node(data, null, null);
//		newNode.next = first;
//		
//		if(first != null)
//			first.prev = newNode;
//		
//		first = newNode;
//		//노드가 처음 추가됐을 떄는 first나 last이나 같은 포인터.
//		//이미 노드가 있었다면 last을 건드리지 않은면 된다.
//		if(first.next == null)
//			last = first;
//		numOfNode++;
//	}

	// 노드를 맨 앞에 추가하기
	public void addFirst(String data) {
		Node newNode = new Node(data, null, first);// 애초에 생성할 때 newNode가 first가 원래 가리키고 있던 첫 번째 노드를 가리키게 하고,
		first = newNode;// 맨 앞에 추가하는 것이므로 first가 newNode를 보게 하고

		if (last == null || numOfNode == 0)// 만약 처음으로 추가된 노드라면 last마저 매 앞의 노드를 가리키게 하고
			last = newNode;
		else // 기본에 다른 노드가 있었다면
			newNode.next.prev = newNode;// 두번째 노드의 prev가 첫번째 노드(newNode)를 가리키게 한다.

		numOfNode++;
	}

	// add
//	public void add(int index, String data){
//		if(!validIndex(index))
//			return;
//			
//		if(numOfNode==0 || index==0){//처음으로 노드를 추가하나느 경우이거나, 맨 앞에 추가하는 경우
//			addFirst(data);//addFirst호출
//		}
//		if(index == numOfNode-1){//맨 마지막에 추가하려는 경우
//			addLast(data);//addLast호출
//		}    
//		Node prevNode = search(index-1);    
//		Node nextNode = prevNode.next;
//		Node newNode = new Node(data, null, null);
//		
//		//연결을 미리 끊고.
//		prevNode.next = null;
//		nextNode.prev = null;
//		//연결하기
//		newNode.next = nextNode;
//		newNode.prev = prevNode;
//		nextNode.prev = newNode;
//		prevNode.next = newNode;
//		numOfNode++;
//	}

	public void add(int index, String data) {
		if(index < 0) {
			return;
		}
		if (numOfNode == 0 || index == 0)// 처음으로 노드를 추가하나느 경우이거나, 맨 앞에 추가하는 경우
			addFirst(data);// addFirst호출
		if (index == numOfNode)// 맨 마지막에 추가하려는 경우
			addLast(data);// addLast호출
		int i = 0;
		Node temp = first;
		while (i < index - 1) {// temp가 newNode가 위피하게 될 index의 바로 앞에까지 가리키게 한다.
			temp = temp.next;// link를 타고 가기
			i++;
		}
		Node newNode = new Node(data, temp, temp.next);
		newNode.prev.next = newNode;
		newNode.next.prev = newNode;
		numOfNode++;
	}

	public Node search(int index) {
		if (index < 0 || index >= numOfNode) {
			return null;
		}
		if (index > numOfNode / 2) {
			Node temp = last;
			for (int i = 0; i < (numOfNode - index - 1); i++) {
				temp = temp.prev;
			}
			return temp;
		} else {
			Node temp = first;
			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}
			return temp;
		}
	}
	
	public int sizeOf() {
		return sizeOf(first);
	}

	private int sizeOf(Node link) {
		if (link == null)
			return 0;
		else
			return 1 + sizeOf(link.next);
	}

	public String toString() {
		Node temp = first;
		String retVal = "";
		while (temp != null) {
			retVal = retVal + "/" + temp.data.toString();
			temp = temp.next;
		}
		return retVal;
	}

	public static void main(String[] args) {
		MyDoubleLinekList1 list = new MyDoubleLinekList1();
		list.addLast("lee");
		System.out.println(list.toString());
		list.addFirst("kim");
		System.out.println(list.toString());
		list.addLast("park");
		System.out.println(list.toString());
		list.add(1, "choi");
		System.out.println(list.toString());
		list.addFirst("jung");
		System.out.println(list.toString());
		list.add(0, "hong");
		System.out.println(list.toString());
	}

	private class Node {
		String data;
		Node prev;
		Node next;

		private Node(String input, Node f, Node n) {
			data = input;
			prev = f;
			next = n;
		}
	}
}
