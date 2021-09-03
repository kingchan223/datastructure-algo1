package week07;

public class MyLinkedList1 {
	// first가 Node를 가리킬 수 있다.
	Node first; // == head
	int numOfNode;

	public MyLinkedList1() {
		first = null;
		numOfNode = 0;
	}

	public String get(int index) {
		if (!validIndex(index))
			return null;
		Node temp = first;
		for (int i = 0; i < index; i++)
			temp = temp.link;

		return temp.data;
	}

	public void set(int index, String data) {
		if (!validIndex(index))
			return;
		Node temp = first;
		for (int i = 0; i < index; i++) {
			temp = temp.link;
		}
		temp.data = data;
	}

	// 클래스 내부에서 인덱스가 유효한지의 여부를 알려주는 validIndex
	private boolean validIndex(int index){
		if (index < numOfNode && index >= 0)
			return true;
		else
			return false;
	}

	
	public void addFirst(String data){
		// Node newNode = new Node(data, first);
		// first = newNode;
		first = new Node(data, first);
		numOfNode++;
	}

	public void addFirst2(String data){
		Node newNode = new Node(data, first);
		first = newNode;
		numOfNode++;
	}

	public void addLast(String data) {
		if (numOfNode == 0)
			addFirst(data);
		else {
			Node temp = first;
			for (int i = 0; i < numOfNode-1; i++) {
				temp = temp.link;
			}
			temp.link = new Node(data, null);
			numOfNode++;
		}
	}
	public void addLast2(String data) {
		if (numOfNode == 0)
			addFirst(data);
		else {
			Node temp = first;
			while(temp.link != null)//temp.link != null은 temp가 마지막 노드를 가리킬때까지이다.
				temp = temp.link;
			temp.link = new Node(data, null);
			numOfNode++;
		}
	}

	public void add(int index, String data) {
		//유효성 검사
		if(index<0 || index > numOfNode)
			return;
		//첫번째 추가는 addFirst
		if(index == 0)
			addFirst(data);
		//마지막 추가는 addLast
		else if(index == numOfNode)
			addLast(data);
		
		else {
			Node prev = first;
			for(int i=0; i<index-1;i++) {
				prev = prev.link;
			}
			Node after = prev.link;
			prev = new Node(data, after);
			numOfNode++;
		}
	}
	public void add2(int index, String data) {
		if(index == 0)
			addFirst2(data);
		//마지막 추가는 addLast
		else if(index == numOfNode)
			addLast2(data);
		else {
			if(!validIndex(index))
				return;
			Node temp = first;
			int i=0;
			while(i<index-1) {
				temp = temp.link;
				i++;
			}
			temp.link = new Node(data, temp.link);
			numOfNode++;
			
		}
	}
	
	public String removeFirst() {
		
		if(first != null) {
			String retVal = first.data;
			first = first.link;
			numOfNode--;
			return retVal;
		}
		else {
			return null;
		}
	}
	
//	public String removelast() {
//		if(numOfNode==1)
//			return removeFirst();
//		else if(numOfNode <= 0)
//			return null;
//		int i=0;
//		Node temp = first;
//		while(i < numOfNode-1) {
//			temp = temp.link;
//			i++;
//		}
//		String retVal = temp.data;
//		temp = null;
//		numOfNode--;
//		return retVal;
//	}
	
	public String removelast() {
		if(first != null) {
			Node temp = first;
			Node tempNext = temp.link;
			while(tempNext.link != null) {
				temp = tempNext;
				tempNext = tempNext.link;
			}
			temp.link = null;
			numOfNode--;
			return tempNext.data;
		}
		else
			return null;
	}

//	public String remove(int index) {
//		if(!validIndex(index))
//			return null;
//		if(index==0)
//			return removeFirst();
//		if(index == numOfNode-1)
//			return removelast();
//		
//		Node prev = first;
//		int i = 0;
//		while(i < index-1) {
//			prev = prev.link;
//		}
//		Node removeNode = prev.link;
//		String retVal = removeNode.data;
//		prev.link = removeNode.link;
//		numOfNode--;
//		return retVal;
//	}
	
	public String remove(int index) {
		if(!validIndex(index))
			return null;
		if(index==0)
			return removeFirst();
		if(index == numOfNode-1) {
			return removelast();
		}
		
		Node temp = first;
		Node tempNext = temp.link;
		int i = 1;
		while(i<index) {
			temp = tempNext;
			tempNext = tempNext.link;
			i++;
		}
		temp.link = tempNext.link;//
		numOfNode--;
		return tempNext.data;
	}
	
	// 아래 remove의 문제점: 
	// indexOf 메소드에서 처음부터 탐색하고,  
	// remove(index)메소드에서도 처음부터 탐색한다.
	// 그래서 최악의 경우 2n의 시간이 걸림.
	//그래서 한번만 탐색하고 바로 삭제하는 remove2(data)정의할 것임 
	public int remove1(String data) {
		int index = indexOf(data);
		remove(index);
		return index;
	}
	
	public int remove(String data) {
		Node temp = first;
		//삭제할 데이터가 맨 앞에 있는 경우
		if(temp.data.compareTo(data)==0) {
			//first가 두번째 node를 참조하게 하고 끝낸다.
			first = temp.link;
			numOfNode--;
			return 0;
		}
		Node tempNext = temp.link;
		int i=1;
		while(tempNext!=null) {
			if(tempNext.data.compareTo(data)==0) {
				temp.link = tempNext.link;
				numOfNode--;
				return i;
			}else {
				i++;
				temp = tempNext;
				tempNext = tempNext.link;
			}
		}
		return -1;
	}

	// 내부에서만 쓰이는 private메소드. 찾고자하는 데이터의 index반환
	private int indexOf(String data) {
		Node temp = first;
		for(int i=0; i<numOfNode; i++) {
			if(temp.data.compareTo(data)==0)
				return i;
			temp = temp.link;
		}
		return -1;
	}

	public int sizeOf() {
		return numOfNode;
	}

	
	// 링크드 리스트는 어레이 구조가 아니므로 arrSize는 필요없다.

	
	public void sort(){
		if(numOfNode<=1)
			return;
		Node temp = first;
		Node max;
		MyLinkedList1 sortedList = new MyLinkedList1();
		while(temp!=null) {
			max = findMax(temp);
			sortedList.addFirst(max.data);
			remove(max.data);//remove할때마다 원래 배열의 원소수가 줄어든다.
			temp = first;
		}
		first = sortedList.first;
		numOfNode = sortedList.numOfNode;//그래서 마지막에 원래 배열의 원소수를 알려줘야한다.
	}
	
	private Node findMax(Node temp) {
		Node max = first;
		while(temp.link!=null) {
			temp = temp.link;
			if(temp.data.compareTo(max.data) > 0)
				max = temp;
		}
		return max;
	}

	public String toString() {
		Node temp = first;
		String retVal = "";
		for(int i=0; i<numOfNode; i++) {
			retVal = retVal+" "+temp.data;
			temp = temp.link;
		}
		return retVal;
	}

	// 구현에 상관없이 사용자는 같은 매서듸름으로 사용할 수 있어야한다.
	public static void main(String[] args) {
		MyLinkedList1 list = new MyLinkedList1();
		//System.out.println(list.first);
		//System.out.println(list.first.link);

		list.addFirst( "c");
		System.out.println(list);

		System.out.println("numOfData:" + list.sizeOf());
		System.out.println();
		list.addFirst( "a");
		System.out.println(list);

		System.out.println("numOfData:" + list.sizeOf());
		System.out.println();
		list.addFirst( "d");
		System.out.println(list);

		System.out.println("numOfData:" + list.sizeOf());
		System.out.println();
		list.addFirst( "b");
		System.out.println(list);

		System.out.println("numOfData:" + list.sizeOf());
		System.out.println();
		list.addFirst("e");
		System.out.println(list);



		System.out.println("numOfData:" + list.sizeOf());
		System.out.println();
		list.remove(4);
		System.out.println(list);
		System.out.println();
		System.out.println("sort:");
		list.sort();
		System.out.println(list.toString());
		System.out.println();
		System.out.println("get(1):");
		System.out.println(list.get(1));
	}

	private class Node {
		String data;
		Node link;

		private Node(String input, Node next) {
			data = input;
			link = next;
		}
	}
}
