package Practice;

public class GenericDoubleLinkedList <T extends Comparable<T>>{

	public Node<T> head;
	public Node<T> last;
	public int numOfData;
	
	public GenericDoubleLinkedList(){
		this.head = null;
		this.last = null;
		this.numOfData = 0;
	}
	
	public T removeFirst(){
		if(head==null) return null;
		T retVal = head.data;
		//노드가 1개인 경우와 2개 이상인 경우를 나눌 수 밖에 없다.
		//NPE가 발생할 수 있기 때문.
		if(numOfData==1){
			head = null;
			last = null;
		}else{
			head.next.prev = null;
			head = head.next;
		}
		numOfData--;
		return retVal;
	}
	
	public T removeLast(){
		if(last == null) return null;
		T retVal = last.data;
		
		if(numOfData == 1 || head == last){
			head = null;
			last = null;
		}else {
			last.prev.next = null;
			last = last.prev;
		}
		numOfData--;
		return retVal;
	}
	
	/*여기부터*/
	public T remove(int index){
		if(index<0) return null;
		if(index==0) return removeFirst();
		if(index==(numOfData-1)) return removeLast();
		int cnt = 0;
		Node<T> temp = head;
		Node<T> dNode =  remove(index, cnt, temp);
		T retVal = dNode.data;
		dNode.prev.next = dNode.next;
		dNode.next.prev = dNode.prev;
		numOfData--;
		return retVal;
	}
	
	private Node<T> remove(int index, int cnt, Node<T> temp){
		if(index == cnt)
			return temp;
		return remove(index, cnt+=1, temp.next);
	}
	/*여기까지 한 세트*/
	
	public T remove(T data){
		if(numOfData<=1) return removeFirst();
		Node<T> temp = head;
		while(temp.data.compareTo(data) != 0){
			temp = temp.next;
		}
		T retVal = temp.data;
		temp.prev.next = temp.next;
		temp.next.prev = temp.prev;
		numOfData--;
		return retVal;
	}
	
	public void addFirst(T data){
		Node<T> newNode = new Node<T>(data, null, head);
		head = newNode;
		if(last == null){
			last = newNode;
		}else {
			newNode.next.prev = newNode;			
		}
		numOfData++;
	}
	
	public void addLast(T data){
		Node<T> newNode = new Node<T>(data, last, null);
		last = newNode;
		if(head == null){
			head = newNode;
		}else{
			newNode.prev.next = newNode;
		}
		numOfData++;
	}
	
	public void add(int index, T data){
		if(index<0) 
			return;
		if(index==0){
			addFirst(data);
			return;
		}
		if(index==(numOfData-1)){
			addLast(data);
			return;
		}
		Node<T> temp = head;
		int cnt = 0;
		while(cnt<index-1){
			temp = temp.next;
			cnt++;
		}
		Node<T> newNode = new Node<T>(data, temp, temp.next);
		newNode.prev.next = newNode;
		newNode.next.prev = newNode;
		numOfData++;
	}
	
	public int indexOf(T data) {
		return -1;
	}
	

	
	public String toString(){
		String ret = "";
		int i = 0;
		Node<T> temp = head;
		while (i < numOfData) {
			ret += temp.data + " / ";
			temp = temp.next;
			i++;
		}
		return ret;
	}

	public static void main(String[] args) {
		GenericDoubleLinkedList<String> gdl = new GenericDoubleLinkedList<>();
		gdl.addLast("aaa");
		gdl.addLast("ccc");
		gdl.addLast("eee");
		gdl.addLast("fff");
		gdl.addLast("bbb");
		gdl.addLast("ggg");
		gdl.addLast("ddd");
		gdl.add(2,"222");
		gdl.add(3,"333");
		System.out.println(gdl.numOfData);
		System.out.println(gdl.toString());
		
		System.out.println();
		gdl.removeFirst();
		gdl.removeLast();
		gdl.remove("bbb");
		System.out.println(gdl.toString());
		System.out.println("head.data: "+gdl.head.data);
		System.out.println("last.data: "+gdl.last.data);
		gdl.remove(2);
		System.out.println(gdl.toString());
		System.out.println("head.data: "+gdl.head.data);
		System.out.println("last.data: "+gdl.last.data);
	}



	private class Node<E>{
		E data;
		Node<E> next;
		Node<E> prev;
		
		private Node(E data, Node<E> prev, Node<E> next){
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
}
