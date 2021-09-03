package finalexam;

import java.util.ArrayDeque;
import java.util.Deque;

public class Max3Tree {
	M3Node root;

	public Max3Tree() {
		root = makeSampleTree(root);
	}

	public void postOrderTraverse() {
		System.out.println("\n<< Max-3-Tree : Post-Order Traverse >>>");
		postOrderTraverse(root);
	}

	// 5-1
	private void postOrderTraverse(M3Node node) {
		if (node != null) {
			int lLen = node.link.length;
			for (int i = 0; i < lLen; i++) {
				postOrderTraverse(node.link[i]);
			}
			System.out.println(node.toString());
		}
	}

	public void inOrderTraverse() {
		System.out.println("\n<< Max-3-Tree : In-Order Traverse >>>");
		inOrderTraverse(root);
	}

	// 5-2
	private void inOrderTraverse(M3Node node) {
		if (node != null) {
			int lLen = node.link.length;
			for (int i = 0; i < lLen; i++) {
				inOrderTraverse(node.link[i]);
				if(i<lLen-1) {
					if(node.key[i]==0)
						break;
					System.out.println(node.key[i]);
				}
			}
		}
	}

	private M3Node makeSampleTree(M3Node root2) {

		M3Node t1 = makeTerminalDouble(1, 3);
		System.out.println(">> " + t1.toString());
		M3Node t2 = makeTerminalSingle(6);
		System.out.println(">> " + t2.toString());
		M3Node t3 = makeTerminalSingle(9);
		System.out.println(">> " + t3.toString());
		M3Node t4 = makeTerminalDouble(11, 12);
		System.out.println(">> " + t4.toString());
		M3Node t5 = makeTerminalSingle(14);
		System.out.println(">> " + t5.toString());
		M3Node t6 = makeTerminalDouble(17, 18);
		System.out.println(">> " + t6.toString());
		M3Node t7 = makeTerminalDouble(21, 23);
		System.out.println(">> " + t7.toString());
		M3Node t8 = makeTerminalSingle(25);
		System.out.println(">> " + t8.toString());
		M3Node t9 = makeTernaryNode(5, 7, t1, t2, t3);
		System.out.println(">> " + t9.toString());
		M3Node t10 = makeBinaryNode(13, t4, t5);
		System.out.println(">> " + t10.toString());
		M3Node t11 = makeTernaryNode(20, 24, t6, t7, t8);
		System.out.println(">> " + t11.toString());
		M3Node t12 = makeTernaryNode(10, 15, t9, t10, t11);
		System.out.println(">> " + t12.toString());

		return t12;
	}

	private M3Node makeTernaryNode(int i, int j, M3Node t1, M3Node t2, M3Node t3) {
		int[] keys = { i, j, 0 };
		M3Node[] links = { t1, t2, t3, null };
		return new M3Node(2, keys, links);
	}

	private M3Node makeBinaryNode(int i, M3Node t1, M3Node t2) {
		int[] keys = { i, 0, 0 };
		M3Node[] links = { t1, t2, null, null };
		return new M3Node(1, keys, links);
	}

	private M3Node makeTerminalSingle(int i) {

		int[] keys = { i, 0, 0 };
		M3Node[] links = { null, null, null, null };
		return new M3Node(1, keys, links);
	}

	private M3Node makeTerminalDouble(int i, int j) {

		int[] keys = { i, j, 0 };
		M3Node[] links = { null, null, null, null };
		return new M3Node(2, keys, links);
	}

	public static void main(String[] args) {
		Max3Tree t = new Max3Tree();

		t.postOrderTraverse();
		System.out.println();
		t.inOrderTraverse();
		System.out.println();

	}

	private class M3Node {
		int elementNumber; // 저장된 key의 개수
		int[] key;
		M3Node[] link;

		public M3Node(int eNum, int[] keys, M3Node[] links) {
			elementNumber = eNum;
			key = keys;
			link = links;
		}

		public String toString() {
			String retVal = "";
			if (elementNumber > 0) {
				retVal = retVal + "[" + key[0];
				for (int i = 1; i < elementNumber; i++)
					retVal = retVal + "," + key[i];
				retVal += "]";
			}
			return retVal;
		}
	}
}
