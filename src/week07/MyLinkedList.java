package week07;

//MyLinkedList는 compareTo를 가지고 있는데
//제네릭으로 모든 타입을 받을 수 있다. 그런데 모든 타입을 받다가
//받은 타입이 compare가 불가능한 타입이라면 이는 런타임시에 오류가 날 수 있다.
//그래서 애초에 선언할 때 <T extends Comparable<T>>로 받을 수 있는
//타입을 제한하여 런타임시에 일어날 수 있는 오류를 사전에 방지한다.
public class MyLinkedList<T extends Comparable<T>> {

	Node<T> first;
	int numOfNode;

	public MyLinkedList() {
		first =null; 
		numOfNode=0;
	}

	//return the node value (node value type is T)
	public T get(int index) {
		if (!validIndex(index))
			return null;
		Node<T> temp = first;
		for (int i=0; i<index;i++) {
			temp =temp.link;
		}
		return temp.data;
	}
	private boolean validIndex(int index){
		if (index<numOfNode && index>=0)
			return true;
		else
			return false;
	}

	public void set(int index, T data) {
		if (!validIndex(index))
			return;
		Node<T> temp = first;
		for (int i=0; i<index;i++) {
			temp =temp.link;
		}
		temp.data=data;
	}
	
	public void addFirst(T data) {
		System.out.println(">> AddFirst "+data.toString());
		Node<T> newNode = new Node<T>(data, first);
		first = newNode;
		numOfNode++;	
	}
	
	public void addLast(T data) {
		System.out.println(">> AddLast "+data.toString());
		if (numOfNode==0)
			addFirst(data);
		else {
			Node<T> temp= first;
			while (temp.link!=null)
				temp=temp.link;
			temp.link = new Node<>(data, null);
			numOfNode++;	
		}
	}
	
	public void add(int index, T data) {
		System.out.println(">> Add "+index+"  "+data.toString());
		if (index==0)
			addFirst(data);
		else if (index==numOfNode)
			addLast(data);
		else {
			if (!validIndex(index))
				return;
			Node<T> temp= first;
			int i=0;
			while (i<index-1) {
				temp=temp.link;
				i++;
			}
			temp.link = new Node<>(data, temp.link);
			numOfNode++;
		}
	}
	public T removeFirst() {
		System.out.println(">> RemoveFirst ");

		if (first!=null) {
			T retVal=first.data;
			first=first.link;
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
			Node<T> temp= first;
			Node<T> tempNext = temp.link;
			while (tempNext.link!=null) {
				temp=tempNext;
				tempNext=tempNext.link;
			}
			temp.link = null;
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
			Node<T> temp= first;
			Node<T> tempNext = temp.link;
			int i=1;
			while (i<index) {
				temp=tempNext;
				tempNext=tempNext.link;
				i++;
			}
			temp.link = tempNext.link;;
			numOfNode--;
			return tempNext.data;
		}
	}
	
	public int remove(T data) {
		System.out.println(">> Remove with value : "+data);

		Node<T> temp = first;
		if (compare(temp.data, data)==0) {
			first=temp.link;
			numOfNode--;
			return 0;
		}
		Node<T> tempNext=temp.link;
		int i=1;
		while(tempNext!=null) {
			if (compare(tempNext.data, data)==0) {
				temp.link=tempNext.link;
				numOfNode--;
				return i;
			}
			else {
				i++;
				temp=tempNext;
				tempNext=tempNext.link;
			}
		}
		return -1;

	}
	
	public int indexOf(T data) {
		Node<T> temp = first;
		for(int i=0; i<numOfNode;i++) {
			if (compare(temp.data, data)==0)
				return i;
			temp=temp.link;
		}
		return -1;
	}
	
	public int indexOf(Node<T> list, T data) {
		
		return indexOf(first, data, 0);
		
	}
	
	public int indexOf(Node<T> list, T data, int index) {
		if(index == sizeOf())
			return -1;
		if(compare(list.data, data)==0)
			return index;
		else 
			return indexOf(list.link, data, index+1);
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
		Node<T> temp = first;
		Node<T> min;
		MyLinkedList<T> sortedList = new MyLinkedList<T>();
		while(temp!=null) {
			min=findMin(temp);
			sortedList.addFirst(min.data);
			remove(min.data);
			temp=first;			
		}
		first=sortedList.first;
		numOfNode=sortedList.numOfNode;
	}

	private Node<T> findMin(Node<T> temp) {
		Node<T> min = temp;
		while(temp.link!=null) {
			temp=temp.link;
			if(compare(min.data, temp.data)>0)
				min=temp;
		}
		return min;
	}

	public String toString() {
		Node<T> temp = first;
		String retVal="";
		int index=0;
		while(temp!=null) {
			retVal=retVal+"/"+temp.data.toString();
			temp=temp.link;
			index++;
		}
		return retVal;
	}
	
	public static void main(String[] args) {

		MyLinkedList<MyClass> list = new MyLinkedList<>();
		int idNum=0;
		System.out.println(list.toString());
		list.addLast(new MyClass(idNum++, "lee"));
		System.out.println(list.toString());
		list.addLast(new MyClass(idNum++, "kim"));
		System.out.println(list.toString());
		list.addLast(new MyClass(idNum++, "park"));
		System.out.println(list.toString());
		list.addLast(new MyClass(idNum++, "choi"));
		System.out.println(list.toString());
		list.addLast(new MyClass(idNum++, "jung"));
		System.out.println(list.toString());
		list.addLast(new MyClass(idNum++, "song"));
		System.out.println(list.toString());
		System.out.println(list.get(3).toString()+list.indexOf(list.get(3)));
		list.set(3, new MyClass(30,"hwang"));
		System.out.println(list.get(3).toString());
		
		
		System.out.println("Current number of data : "+list.sizeOf());
		
		System.out.println(list.remove(new MyClass(1, "kim")));
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
		Node<E> link;
		private Node(E input, Node<E> next) {
			data=input;
			link=next;
		}
	}
}