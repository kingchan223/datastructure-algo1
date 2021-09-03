package week13;

import java.util.ArrayDeque;
import java.util.Deque;
/*
 * 최대, 최소값을 찾기 위한 tree
 * 중간이 비어있을 수 없음
 * 같은 key를 가진 노드가 있을 수 있음
 * */

/*
 * 만약 last가 없다면 levelOrderTravse로  last를 찾는다.
 * 사이즈를 저장하는 필드를 만들고, 
 * 만약 그 사이즈일 때 나오는 노드는 last이다.
 * */
public class HeapTree2{
	//root
	Node heap;
	//Node last;
	int size;
	public HeapTree2(){
		heap = null;
		//last = null;
		size = 0;
		
	}

	public void heapSort(char[] input){
		buildHeap(input);
		sortOut();
	}
	
	private void buildHeap(char[] input){
		System.out.println("<< Heap implemented in Linked-Tree >>");
		for (int i = 0; i < input.length; i++) {
			insertHeap(input[i]);
		}
	}
	
	private void insertHeap(char c){
		size += 1;
		//첫 원소를 집어 넣는 경우
		if(heap==null){
			heap = new Node(c, null, null, null);
			//last = heap;
			return;
		}
		Node last = findlast();
		
		//getParentOfNext는 새로운 last가 추가될 위치의 부모를 찾아준다.
		Node pNext = getParentOfNext(last);
		//이제 새로 추가된 노드가 last이고, 위에서 찾아낸 pNext가 부모가 된다.
		last = new Node(c, null, null, pNext);
		//last를 부모 아래에 붙이기
		if(pNext.left == null)
			pNext.left = last;
		else
			pNext.right = last;

		heapifyUpward(last.parent);
		System.out.println(toString());
	}

	private Node getParentOfNext(Node node){//여기서의 node는 현재 last임
		if(node==null || node==heap)
			return node;
		//1번 경우(현재 last가 left에 붙어있는 경우)\
		if(node.parent.right==null)
			return node.parent;
		Node p = node;                                       
		//p가 right면 계속 본인이 right가 아닐 때까지 올러간다.(2번 경우)
		while((p.parent!=null)&&(p==p.parent.right))           
			p = p.parent;                                   			
		// p.parent가 null이아니면 쭉 내려와야한다.                       
		if(p.parent!=null)                                          
			p = p.parent.right;
		while(p.left!=null)
			p = p.left;
		//위 while을 마치면 자리를 찾은 것.
		return p;
	}
	
//	private Node getParentOfNext2(Node node) {
//		if(node == null || node == heap)
//			return node;
//		if(node.parent.right==null)
//			return node.parent;
//		Node p = node;
//		while((p==p.parent.right) && (p.parent!=null)){
//			p = p.parent;
//		}
//		if(p.parent!=null)
//			p = p.parent.right;
//		while(p.left!=null)
//			p = p.left;
//		return p;
//	}

	private void heapifyUpward(Node node){
		//node가 null이거나 node의 left자식이 null이면(right도 null임)끝내기
		//heap(=root)까지 올라왔거나, 자식 노드가 없다면
		if (node==null || node.left==null)
			return;
		//위의 if문에서 필터링되지 않았다면 최소 left노드는 가지고 있는 것임
		Node larger = node.left;
		//right로 가질지 검사
		if (node.right!=null && node.right.key > node.left.key)
			larger = node.right;

		if (larger.key > node.key){
			swap(larger, node);
			heapifyUpward(node.parent);
		}
	}                                                                  

	private void sortOut(){
		System.out.println("< Max Heap> ");
		while (heap!=null) {
			System.out.println(deleteHeap() + "  " + toString());
		}
	}

	private Character deleteHeap(){
		Node last = findlast();
		//heap이 비어있음
		if (heap==null)
			return null;
		char retVal = heap.key;
		//node가 한개인 경우
		if(heap.right==null && heap.left==null){
			heap = null;
			last = null;
		}else {
			//구조유지를 위해 최상단에 last의 키를 넣고, 
			heap.key = last.key;
			//새로운 last를 받아온다.
			Node prev = getPrev(last);
			//last의 자리를 없앤다.
			if(last == last.parent.left)
				last.parent.left = null;
			else
				last.parent.right = null;
			last = prev;
			//제일 큰 root를 삭제하고, last를 root자리에 두었으니,
			//root부터 아래로 heapify를 한다.
			heapifyDownward(heap);
		}
		return retVal;
	}
	
	//얘는 last를 반환하고, 기존의 last를 반환했으므로 새로운 last를 찾아 떠난다.
	private Node getPrev(Node node){
		//right라면 이전 자식은 left에 있음
		if(node == node.parent.right)
			return node.parent.left;
		Node p = node;
		while(p.parent!=null && p==p.parent.left)
			p = p.parent;
		if(p.parent!=null)
			p = p.parent.left;
		while(p.right!=null)
			p = p.right;
		return p;
	}
	
	private void heapifyDownward(Node node){
		//node가 null이거나, 자식이 없으면 heapify를 할 필요 없는 경우
		if(node==null || node.left==null)
			return;
		Node larger = node.left;
		
		if(node.right!=null && node.right.key > node.left.key)
			larger = node.right;
		
		if(larger.key > node.key) {
			swap(larger, node);
			heapifyDownward(larger);
		}
	}

	private void swap(Node a, Node b){
		char temp = a.key;
		a.key = b.key;
		b.key = temp;
	}
	
	/*------------Level Order Traverse------------*/
	public String toString(){
		if(heap==null)
			return null;
		Deque<Node> q = new ArrayDeque<Node>();
		q.add(heap);
		return levelOrderTraverse(q, "");//이게 ppt에서 본 while문과 같음
	}
	
	private String levelOrderTraverse(Deque<Node> q, String retString){
		Node node = q.poll();
		if(node == null)
			return retString;
		retString = retString+"    "+node.key;
		
		if(node.left!=null) {
			q.add(node.left);
			if(node.right!=null)
				q.add(node.right);
		}
		return levelOrderTraverse(q, retString);
	}
	/*--------------------------------------------*/
	
	//last 포인터가 없을 때 last노드를 반환하기 위해 levelOrderTraverse를 활용한 메소드
	private Node findlast(){
		if(heap == null)
			return null;
		Deque<Node> q = new ArrayDeque<Node>();
		q.add(heap);
		return findlast(q, 0);
	}
	
	private Node findlast(Deque<Node> q, int cnt){
		if(heap == null)
			return null;
		Node now = q.poll();
		cnt++;
		if(cnt == size-1)
			return now;
		if(now.left!=null)
			q.add(now.left);
			if(now.right!=null)
				q.add(now.right);
		
		return findlast(q, cnt);
	}
	
	class Node{
		char key;
		Node left;
		Node right;
		Node parent;
		public Node(char key, Node left, Node right, Node parent){
			this.key = key;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
		
		public String toString(){
			return ""+key;
		}
	}
	
	public static void main(String[] args) {
		char[] data = { 'M', 'Y', 'U', 'N', 'G', 'I', 'S', 'W' };
		HeapTree2 h = new HeapTree2();
		h.heapSort(data);
	}
}
