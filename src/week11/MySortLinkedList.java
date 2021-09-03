package week11;

public class MySortLinkedList {
	Node first = null;
	int nOfCompare;
	
	//삽입 정렬 알고리즘을 사용하는 링크드리스트 메소드
	public void insertAscendingOrder(int input){
		nOfCompare = 0;
		//처음 추가된 data인 경우
		if(first == null){
			first = new Node(input);
			return;
		}
		//삽입하는 데이터가 맨앞의 데이터보다 작다면
		//삽입한 노드를 first로 만들어 준다.
		if(first.value > input) {
			Node temp = new Node(input);
			temp.next =  first;
			first = temp;
			return;
		}
		
		Node p = first;
		Node q = first.next;
		//삽입할 노드의 자리를 찾는다.
		while(q!=null && q.value<input){
			nOfCompare++;
			p = q;
			q = q.next;
		}
		//노드를 삽입
		Node temp = new Node(input);
		p.next = temp;
		temp.next = q;
	}
	
	public int getNofCompare() {
		return nOfCompare;
	}
	
	//int[]배열에 복사하기 위한 remove메서드
	public int removeFirst() {
		if(first!=null) {
			int retVal = first.value;
			first = first.next;
			return retVal; 			
		}
		else return -99999;
	}
	
	private class Node{
		int value;
		Node next;
		private Node(int input) {
			this.value = input;
			this.next = null;
		}
	}
}
