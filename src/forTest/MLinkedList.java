package forTest;

// MyLinkedList는 compareTo를 가지고 있는데
// 제네릭으로 모든 타입을 받을 수 있다. 그런데 모든 타입을 받다가
// 받은 타입이 compare가 불가능한 타입이라면 이는 런타임시에 오류가 날 수 있다.
// 그래서 애초에 선언할 때 <T extends Comparable<T>>로 받을 수 있는
// 타입을 제한하여 런타임시에 일어날 수 있는 오류를 사전에 방지한다.
public class MLinkedList<T extends Comparable<T>> {

	Node<T> head;
	int numOfNode;

	public MLinkedList(){
		head =null; 
		numOfNode=0;
	}

	//return the node value (node value type is T)
	public T get(int index){
		if (!validIndex(index))
			return null;
		Node<T> temp = head;
		for (int i=0; i<index;i++){
			temp =temp.next;
		}
		return temp.data;
	}
	private boolean validIndex(int index){
		if (index<numOfNode && index>=0)
			return true;
		else
			return false;
	}

	public void set(int index, T data){
		if (!validIndex(index))
			return;
		Node<T> temp = head;
		for (int i=0; i<index;i++){
			temp =temp.next;
		}
		temp.data=data;
	}
	
	public void addFirst(T data) {
		//System.out.println(">> AddFirst "+data.toString());
		Node<T> newNode = new Node<T>(data, head);
		head = newNode;
		numOfNode++;	
	}
	
	public void addLast(T data) {
		//System.out.println(">> AddLast "+data.toString());
		if (numOfNode==0)
			addFirst(data);
		else {
			Node<T> temp= head;
			while (temp.next!=null)
				temp=temp.next;
			temp.next = new Node<>(data, null);
			numOfNode++;	
		}
	}
	
	public void add(int index, T data) {
		//System.out.println(">> Add "+index+"  "+data.toString());
		if (index==0)
			addFirst(data);
		else if (index==numOfNode)
			addLast(data);
		else {
			if (!validIndex(index))
				return;
			Node<T> temp = head;
			int i = 0;
			while (i < index-1) {
				temp = temp.next;
				i++;
			}
			temp.next = new Node<>(data, temp.next);
			numOfNode++;
		}
	}
	
	public T removeFirst() {
		System.out.println(">> RemoveFirst ");

		if (head!=null) {
			T retVal=head.data;
			head=head.next;
			numOfNode--;
			return retVal;
		}
		else
			return null;
	}
	public T removeLast() {
		System.out.println(">> RemoveLast ");

		if (numOfNode==0)
			return removeFirst();
		else {
			Node<T> temp= head;
			Node<T> tempNext = temp.next;
			while (tempNext.next!=null) {
				temp=tempNext;
				tempNext=tempNext.next;
			}
			temp.next = null;
			numOfNode--;
			return tempNext.data;
		}
	}

	public T remove(int index) {
		System.out.println(">> Remove with index : "+index);

		if (!validIndex(index))
			return null;
		if (index==0)
			return removeFirst();
		else if (index==numOfNode-1)
			return removeLast();
		else {
			Node<T> temp= head;
			Node<T> tempNext = temp.next;
			int i=1;
			while (i<index) {
				temp=tempNext;
				tempNext=tempNext.next;
				i++;
			}
			temp.next = tempNext.next;;
			numOfNode--;
			return tempNext.data;
		}
	}
	
	public int remove(T data) {
		System.out.println(">> Remove with value : "+data);

		Node<T> temp = head;
		if (compare(temp.data, data)==0) {
			head=temp.next;
			numOfNode--;
			return 0;
		}
		Node<T> tempNext=temp.next;
		int i=1;
		while(tempNext!=null) {
			if (compare(tempNext.data, data)==0) {
				temp.next=tempNext.next;
				numOfNode--;
				return i;
			}
			else {
				i++;
				temp=tempNext;
				tempNext=tempNext.next;
			}
		}
		return -1;

	}
	
	public int indexOf(T data) {
		Node<T> temp = head;
		for(int i=0; i<numOfNode;i++) {
			if (compare(temp.data, data)==0)
				return i;
			temp=temp.next;
		}
		return -1;
	}

	public int sizeOf() {
		
		return numOfNode;
	}

	public int compare(T o1, T o2) {
		if (o1 instanceof Comparable && o2 instanceof Comparable ) {
			Comparable c1 =(Comparable) o1;
			Comparable c2 =(Comparable) o2;
			return c1.compareTo(c2);
		}
		else 
			return -1;
	}

	public void sort() { 
		if (numOfNode<=1)
			return;
		Node<T> temp = head;
		Node<T> min;
		MLinkedList<T> sortedList = new MLinkedList<T>();
		while(temp!=null) {
			min=findMin(temp);
			sortedList.addFirst(min.data);
			remove(min.data);
			temp=head;			
		}
		head=sortedList.head;
		numOfNode=sortedList.numOfNode;
	}

	private Node<T> findMin(Node<T> temp) {
		Node<T> min = temp;
		while(temp.next!=null) {
			temp=temp.next;
			if(compare(min.data, temp.data)>0)
				min=temp;
		}
		return min;
	}

	public String toString() {
		Node<T> temp = head;
		String retVal="";
		int index=0;
		while(temp!=null) {
			retVal=retVal+"/"+temp.data.toString();
			temp=temp.next;
			index++;
		}
		return retVal;
	}
	
	public static void main(String[] args) {

		MLinkedList<Student> list = new MLinkedList<>();
		int idNum=0;
		System.out.println(list.toString());
		list.addLast(new Student(idNum++, "lee"));
		System.out.println(list.toString());
		list.addLast(new Student(idNum++, "kim"));
		System.out.println(list.toString());
		list.addLast(new Student(idNum++, "park"));
		System.out.println(list.toString());
		list.addLast(new Student(idNum++, "choi"));
		System.out.println(list.toString());
		list.addLast(new Student(idNum++, "jung"));
		System.out.println(list.toString());
		list.addLast(new Student(idNum++, "song"));
		System.out.println(list.toString());
		System.out.println(list.get(3).toString()+list.indexOf(list.get(3)));
		list.set(3, new Student(30,"hwang"));
		System.out.println(list.get(3).toString());
		
		
		System.out.println("Current number of data : "+list.sizeOf());
		
		System.out.println(list.remove(new Student(1, "kim")));
		System.out.println(list.toString());
		System.out.println("Data removed : "+list.remove(1).toString());
		System.out.println(list.toString());
		System.out.println(list.get(2).toString());
		System.out.println(list.toString());
		
		System.out.println("\n<< Sorting >>");
		System.out.println("Before: "+list.toString());
		list.sort();
		System.out.println("After : "+list.toString());
	}
	
	private class Node<E>{
		E data;
		Node<E> next;
		private Node(E input, Node<E> next) {
			data=input;
			this.next=next;
		}
	}
}