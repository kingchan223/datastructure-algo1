package week13;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreePlus {
	double PROBCHILD = 0.7;
	Node root;
	public TreePlus() {
		root = null;
	}
	
	public void makeTree(int[] input){
		if(input.length==0)
			return;
		Deque<Node> q = new ArrayDeque<Node>();
		root = new Node(input[0], null, null, null);
		q.add(root);
		int currentIndex = 1;
		maxMakeIteration(input, currentIndex, q);
	}
	
	//while문을 사용하여 랜덤한 트리를 만들기
	private void maxMakeIteration(int[] input, int currentIndex, Deque<Node> q) {
		//q에서 더는꺼낼 것이 없거나, input에서 더 꺼낼 데이터가 없다면 끝낸다.
		while(q.size()>0 && currentIndex < input.length-1){
//			if(q.size()<=0)//근데 사실 q는 빌 경우가 없다. 최소 left는 무조건 만들어지므로
//				System.out.println("ggg");
			Node node = q.poll();
			double LeftPROB = Math.random();
			double RightPROB = Math.random();
			//left를 만드는 상황
			if(LeftPROB<PROBCHILD || (LeftPROB>PROBCHILD && RightPROB>PROBCHILD)){// or뒤에는 left, right모두 생성되지 얺는 경우에는 left에 붙이겠다는 것임
				node.left = new Node(input[currentIndex++], null,null,node);
				q.add(node.left);
			}
			//right를 만드는 상황
			if(RightPROB<PROBCHILD){
				node.right = new Node(input[currentIndex++], null,null,node);
				q.add(node.right);
			}
		}
	}
	
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
		TreePlus t = new TreePlus();
		t.makeTree(data);
		t.showTree();
		System.out.println("\nMax Depth :"+t.maxDepth());
		System.out.println("\nMax Depth :"+t.maxPath());
	}
}
