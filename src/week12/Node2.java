package week12;

public class Node2{
	char data;
	Node2 left;
	Node2 right;
	
	public Node2(char data){
		this.data = data;
		this.left = null;
		this.right = null;
	}
	
	public Node2(char data, Node2 left, Node2 right){
		this.data = data;
		this.left = left;
		this.right = right;
	}
}
