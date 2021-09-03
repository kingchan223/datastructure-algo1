package week13;

public class TreePlus2 {
	double PROBCHILD = 0.7;
	Node root;
	public TreePlus2() {
		root = null;
	}
	
	public void makeTree(int[] input){
		if(input.length==0)
			return;
		int currentIndex = 0;
		maxMakeIteration(input, currentIndex);
	}
	
	//while문을 사용하여 랜덤한 트리를 만들기
	private void maxMakeIteration(int[] input, int currentIndex) {
		//q에서 더는꺼낼 것이 없거나, input에서 더 꺼낼 데이터가 없다면 끝낸다.
		while(currentIndex < input.length-1){
			double nodePROB = Math.random();
			//left를 만드는 상황
			if(nodePROB<PROBCHILD){
				insert(input[currentIndex++]);
			}
		}
	}
	
	/* --------------search와 insert-------------------*/
	//재귀적 search
	public Node search(Node startNode, int x) {
		Node p = startNode;
		// p==null에 의해 반환된다면 x가 없다는 것.
		// p.data==x에 의해 반환된다면 데이터가 있다느 것임.
		if (p == null || p.key == x)
			return p;
		else if (x < p.key)
			return search(p.left, x);
		else
			return search(p.right, x);
	}
	
	public void insert(int x){
		if(root==null)
			root = new Node(x, null,null,null);
		// 이미 존재하면 삽입불가 --> /* 여기서 같은 노드는 삽입 불가하다는 것이 나타난다. */
		else if (search(root, x) != null)
			return;
		// root보다 작다면 왼쪽에 삽입
		else if (x < root.key)
			insert(root.left, root, x);
		// root보다 크다면 오른쪽에 삽입
		else
			insert(root.right, root, x);
	}
	
	//재귀적 insert
	private void insert(Node node, Node parent, int x) {
		if (node == null) {
			Node temp = new Node(x, null, null, null);
			temp.parent = parent;
			if (x < parent.key)
				parent.left = temp;
			else
				parent.right = temp;
		} else if (x < node.key)
			insert(node.left, node, x);
		else
			insert(node.right, node, x);
	}
	/*---------------------------------------------------*/
	public void showTree(){
		show(root);
	}
	
	private void show(Node node){
		if(node==null)
			System.out.print("/--");
		else {
			System.out.print("/"+node.key);
			show(node.left);
			show(node.right);
		}
	}
	
	public int maxDepth() {
		return maxDepth(root);
	}

	private int maxDepth(Node node){
		if(node==null)
			return 0;
		else {
			return Math.max(maxDepth(node.left), maxDepth(node.right))+1;
		}
	}

	public String maxPath() {
		return maxPath(root);
	}

	
	private String maxPath(Node node) {
		if(node==null)
			return null;
		else if(maxDepth(node.left)>=maxDepth(node.right))
			return node.key + " - " + maxPath(node.left);
		else
			return node.key + " - " + maxPath(node.right);
	}

	class Node{
		int key;
		Node left;
		Node right;
		Node parent;
		public Node(int key, Node left, Node right, Node parent){
			this.key = key;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}
		
		public String toString(){
			return ""+key;
		}
	}
	
	public static void main(String[] args){
		int inputSize = 30;
		int[] data = new int[inputSize];
		//길이 30의 데이터 미리만들기
		for(int i=0; i<inputSize; i++){
			data[i] = i;
		}
		TreePlus2 t = new TreePlus2();
		t.makeTree(data);
		t.showTree();
		System.out.println("\nMax Depth :"+t.maxDepth());
		System.out.println("\nMax Depth :"+t.maxPath());
	}
}