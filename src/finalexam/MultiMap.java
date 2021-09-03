package finalexam;

import java.util.ArrayList;
import java.util.LinkedList;

public class MultiMap {
	ArrayList<LinkedList<Node>> map;
	
	public MultiMap(){
		map = new ArrayList<LinkedList<Node>>();
	}
	
	public static void main(String[] args) {
		MultiMap mm = new MultiMap();
//		mm.add();
//		mm.add();
		
	}
	
	class Node{
		Node next;
		int key;
		public Node(Node next, int key) {
			this.next = next;
			this.key = key;
		}
	}

}
