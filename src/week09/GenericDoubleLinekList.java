package week09;

public class GenericDoubleLinekList<T extends Comparable<T>>{

	private Node<T> head;
	private Node<T> last;
	private int numOfData;

	public GenericDoubleLinekList(){
		this.head = null;
		this.last = null;
		this.numOfData = 0;
	}

	private class Node<E>{
		E data;
		Node<E> prev;
		Node<E> next;

		private Node(E data, Node<E> prev, Node<E> next){
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}

	public T removeLast(){
		if (numOfData == 0 || head == null || last == null)
			return null;

		if (head == last)
			return removeFirst();

		Node<T> temp = last;
		T retVal = temp.data;

		last = temp.prev;
		last.next = null;
		numOfData--;
		return retVal;
	}

	public T remove(int index){
		if (index < 0 || numOfData == 0 || head == null || last == null)
			return null;
		if (index == 0)
			return removeFirst();
		if (index == numOfData - 1)
			return removeLast();
		Node<T> temp = head;
		int i = 0;
		while (i < index - 1){
			temp = temp.next;
			i++;
		}
		T retVal = temp.data;
		temp.next = temp.next.next;

		temp.next.prev = temp;
		numOfData--;
		return retVal;
	}

	public T removeFirst(){
		// 삭제할게 없는 경우
		if (numOfData == 0 || head == null || last == null)
			return null;
		T retVal = head.data;
		// last와 head가 같다는 것은
		// 남은 노드가 한개인 것.
		if (last == head) {
			// last, head모두 null을 참조시켜 노드를 삭제
			last = null;
			head = null;
		} else {// 남은 노드가 2개이상인 경우
			head.next.prev = null;
			head = head.next;
		}
		numOfData--;
		return retVal;
	}
	
	public void remove(T data){
		int index = indexOf(data);
		remove(index);
	}
	
	public void add(int index, T data) {
		// 0이하의 인덱스라면 add불가
		if (index < 0)
			return;
		// 맨 앞에 추가하려 하거나, 현재 노드가 0개라면 addFirst호출하기
		if (index == 0 || numOfData == 0) {
			addFirst(data);
			return;
		}
		// 맨마지막 인덱스에 추가는 addLast와 같음
		if (index == numOfData - 1) {
			addLast(data);
			return;
		}
		// newNode 생성
		Node<T> temp = head;
		int i = 0;
		// 추가될 인덱스의 직전 노드를 가리킬때까지 temp를 next노드를참조하도록한다.
		while (i < index - 1) {
			temp = temp.next;
			i++;
		}
		// temp는 현재 추가하려는 노드 앞에 위치하므로
		// newNode의 prev는 temp를 가리키도록하고, next는 temp의 다음다음을 가리키게 한다.
		Node<T> newNode = new Node<T>(data, temp, temp.next);
		// 전 노드가 newNode가리키게 하기
		newNode.prev.next = newNode;
		// 앞 노드가 newNode가리키게 하기
		newNode.next.prev = newNode;
		numOfData++;
	}

	public void addLast(T data) {
		// newNode가 last가 가리키고 있던 노드를 가리키게한다.
		Node<T> newNode = new Node<T>(data, last, null);
		last = newNode;
		// numOfNode가 0이거나 head가 null이라는 것은
		// 기존에 노드가 없었다는 것임
		if (numOfData == 0 || head == null) {
			head = newNode;
			// 이전에 노드가 있었다면 기존의 last가 newNode를 가리키도록 해야한다.
		} else {
			newNode.prev.next = newNode;
		}
		numOfData++;
	}

	public void addFirst(T data) {
		// newNode의 next는 원래 있던 노드를 가리키게 한다.
		// (비어있었다면 null을 가리키게 될 것임)
		Node<T> newNode = new Node<T>(data, null, head);
		// 이제 newNode가 head가 된다.
		head = newNode;
		// numOfNode가 0이거나 last가 null이라는 것은
		// 기존에 노드가 없었다느 것임
		if (numOfData == 0 || last == null) {
			last = newNode;
			// 이전에 노드가 있었다면 기존의 head인 newNode.next의 prev가
			// newNode를 가리키게 한다.
		} else {
			newNode.next.prev = newNode;
		}
		numOfData++;
	}


	
	private int indexOf(T data) {
		Node<T> temp = head;
		int i = 0;
		while (temp != null) {
			if (temp.data.compareTo(data) == 0)
				return i;
			temp = temp.next;
			i++;
		}
		return -1;
	}

	public void sort() {
		if (numOfData <= 1)
			return;
		Node<T> temp = head;
		Node<T> max;
		//새로운 리스트를 만들어 이것을 최종적으로 참조하게 한다.
		GenericDoubleLinekList<T> sorted = new GenericDoubleLinekList<T>();
		while (temp != null) {
			max = retMaxNode(temp);
			sorted.addFirst(max.data);//가장 큰값을 찾아 sorted에 하나씩 넣어주기
			remove(max.data);//가장 큰 값을 제외시키기위해기존의 list에서 삭제
			temp = head;
		}
		head = sorted.head;
		last = sorted.last;
		numOfData = sorted.numOfData;

	}


	public Node<T> retMaxNode(Node<T> temp){
		Node<T> maxNode = head;
		while (temp.next != null) {
			temp = temp.next;
			if (temp.data.compareTo(maxNode.data) > 0) {
				maxNode = temp;
			}
		}
		return maxNode;
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

	public int RecurIndexOf(GenericDoubleLinekList<T> list, T data){
		Node<T> temp = list.head;
		return ReCurIndexOf(0, temp, data);
	}

	private int ReCurIndexOf(int i, Node<T> head, T data) {
		if (i == numOfData)
			return -1;
		if (head.data.compareTo(data) == 0)
			return i;
		else
			return ReCurIndexOf(i + 1, head.next, data);

	}

	public T get(int index){
		Node<T> temp = head;
		int i = 0;
		while (i < index){
			temp = temp.next;
			i++;
		}
		return temp.data;
	}

	private int sizeOf(){
		return numOfData;
	}

	public static void main(String[] args){
		GenericDoubleLinekList<String> gdl = new GenericDoubleLinekList<String>();

		// -- add테스트 --
		gdl.addFirst("a");

		gdl.addFirst("b");

		gdl.addFirst("g");

		gdl.addLast("c");

		gdl.addLast("d");

		gdl.addLast("h");

		gdl.add(1, "e");

		gdl.add(2, "f");

		gdl.add(2, "z");

		// sort 테스트
		gdl.sort();
		System.out.println("sort: "+gdl.toString());

		// --remove 테스트 --

		gdl.remove("d");
		gdl.removeFirst();
		gdl.removeLast();
		gdl.remove(2);

		System.out.println("after remove: "+gdl.toString());

		// RecurIndexOf 테스트
		for (int i = 0; i < gdl.sizeOf(); i++)
			System.out.println(gdl.RecurIndexOf(gdl, gdl.get(i)));
		System.out.println(gdl.toString());

	}
}
