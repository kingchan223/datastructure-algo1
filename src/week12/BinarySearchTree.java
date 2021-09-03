package week12;

/*
 * 이진 트리는 left < root < right 순이다.
 * 같은 key를 가진 노드가 있을 수 없다.
 * 중간이 비어 있을 수 있다.
 */
public class BinarySearchTree {

	Node root;
	public BinarySearchTree() {
		this.root = null;
	}

	public void insert(char x){
		// 비어있는 트리라면 root로 생성
		if (root == null)
			root = new Node(x);
		// 이미 존재하면 삽입불가 --> /* 여기서 같은 노드는 삽입 불가하다는 것이 나타난다. */
		else if (search(root, x) != null)
			return;
		// root보다 작다면 왼쪽에 삽입
		else if (x < root.data)
			insert(root.left, root, x);
		// root보다 크다면 오른쪽에 삽입
		else
			insert(root.right, root, x);
	}
	
	//재귀적 insert
	private void insert(Node node, Node parent, char x) {
		if (node == null) {
			Node temp = new Node(x);
			temp.parent = parent;
			if (x < parent.data)
				parent.left = temp;	
			else
				parent.right = temp;
		} else if (x < node.data)
			insert(node.left, node, x);
		else
			insert(node.right, node, x);
	}
	
	//반복적 insert
	private void insertIter(Node node, Node parent, char x){
		if(node == null) {
			Node newNode = new Node(x, null, null, parent);
			if(parent.data > x) {
				parent.left = newNode;
			}else {
				parent.right = newNode;
			}
			return;
		}
		Node p = parent;
		Node prev = null;
		while(p!=null) {
			prev = p;
			if(p.data > x){
				p = p.left;
			}else {
				p = p.right;
			}
		}
		Node newNode = new Node(x, null, null, prev);
		if(prev.data > x) {
			prev.left = newNode;
		}else {
			prev.right = newNode;
		}
	}
	
	//재귀적 search
	public Node search(Node startNode, char x) {
		Node p = startNode;
		// p==null에 의해 반환된다면 x가 없다는 것.
		// p.data==x에 의해 반환된다면 데이터가 있다느 것임.
		if (p == null || p.data == x)
			return p;
		else if (x < p.data)
			return search(p.left, x);
		else
			return search(p.right, x);
	}
	
	//반복적 search  null을 반환하면 데이터가 없는 거임
	public Node searchIter(Node startNode, char x) {
		Node p = startNode;
		while(p!=null&&p.data!=x) {
			if(p.data < x) {
				p = p.right;
			}else {
				p = p.left;
			}			
		}
		return p; 
	}

	public void delete(char x) {
		delete(root, x);
	}

	private void delete(Node startNode, char x){
		// 삭제할 노드찾기
		Node deleteN = search(startNode, x);
		//System.out.println(deleteN);
		// 삭제할 노드가 없는 경우
		if (deleteN == null)
			return;

		/* case1: no child */ // 부모가 null을 보게 한다.
		if (deleteN.left == null && deleteN.right == null) {
			if (deleteN == deleteN.parent.left)
				deleteN.parent.left = null;
			else
				deleteN.parent.right = null;
			return;
		}
		
		/* case2: degree==1 */ //부모가 삭제할 노드의 자식을 보게한다.
		if (deleteN.left == null || deleteN.right == null){
			// 삭제할 노드의 오른쪽 자식이 존재
			if (deleteN.right != null) {
				//부모가 새로운 직손 자식을 보게 하기
				deleteN.right.parent = deleteN.parent;
				//자식이 부모를 보게 하기
				if (deleteN == deleteN.parent.left) {
					deleteN.parent.left = deleteN.right;
				} else {
					deleteN.parent.right = deleteN.right;
				}
			// 삭제할 노드의 왼쪽이 존재
			} else {
				deleteN.left.parent = deleteN.parent;
				if (deleteN == deleteN.parent.left) {
					deleteN.parent.left = deleteN.left;
				} else {
					deleteN.parent.right = deleteN.left;
				}
			}
			return;
		}
		//case 2의 아주 조금 다른 버전
//		if(deleteN.left==null || deleteN.right==null){
//			//삭제하려는 노드가 왼쪽노드임
//			if(deleteN == deleteN.parent.left) {
//				if(deleteN.right != null){
//					deleteN.parent.left = deleteN.right;
//					deleteN.right.parent = deleteN.parent;
//				}else {
//					deleteN.parent.left = deleteN.left;
//					deleteN.left.parent = deleteN.parent;
//				}
//			}else {
//				if(deleteN.right!=null) {
//					deleteN.parent.right = deleteN.right;
//					deleteN.right.parent = deleteN.parent;
//				}else{
//					deleteN.parent.right = deleteN.left;
//					deleteN.left.parent = deleteN.parent;
//				}
//			}
//			return;
//		}
		/* case3: degree==2 */ 
		/* 방법1: 왼쪽 트리의 최대 노드로 매꿔주던가. predecessor */
		/* 방법2: 오른쪽 트리의 최소 노드로 매꿔주던가. succesor */
		Node q = succesor(deleteN);//삭제할 노드의 오른쪽 노드를 root로 하는 서브트리 중 제일 큰 값을 삭제할 노드 위체에 놓는 방식
		deleteN.data = q.data;
		delete(deleteN.right, q.data);
//		Node q = preDecessor(v);
//		v.data = q.data;
//		delete(v.left, q.data);
	}
	
	private Node succesor(Node v){
		Node p = v.right;
		while (p.left != null)
			p = p.left;
		return p;
	}
	
	// 여기부터
	private Node succesorRecur1(Node v){
		return succesorRecur2(v.right);
	}

	private Node succesorRecur2(Node node) {
		if(node.left == null)
			return node;
		else
			return succesorRecur2(node.left);
	}
	//여기까지 recur한 succesor

	private Node preDecessor(Node v) {
		Node p = v.left;
		while (p.right != null)
			p = p.right;
		return p;
	}
	
	//여기부터
	private Node predecessorRecur1(Node v){
		return predecessorRecur2(v.left);
	}
	
	private Node predecessorRecur2(Node node) {
		if(node.right==null)
			return node;
		else
			return predecessorRecur2(node.right);
	}
	//여기까지 recur한 predeccesor

	public void delete2(char x){
		delete(root, x);
	}

	private void delete2(Node startNode, char x){

		// 삭제할 노드찾기
		Node deleteN = search(startNode, x);
		// 삭제할 노드가 없는 경우
		if (deleteN == null)
			return;

		// case1: no child
		if (deleteN.left == null && deleteN.right == null){
			if (deleteN == deleteN.parent.left)
				deleteN.parent.left = null;
			else
				deleteN.parent.right = null;
			return;
		}
		
		// case2: degree==1
		if (deleteN.left == null || deleteN.right == null){
			// 삭제할 노드의 오른쪽 자식이 존재
			if (deleteN.right != null) {
				//부모가 새로운 직손 자식을 보게 하기
				deleteN.right.parent = deleteN.parent;
				//자식이 부모를 보게 하기
				if (deleteN == deleteN.parent.left) {
					deleteN.parent.left = deleteN.right;
				} else {
					deleteN.parent.right = deleteN.right;
				}
			// 삭제할 노드의 왼쪽이 존재
			} else{
				deleteN.left.parent = deleteN.parent;
				if (deleteN == deleteN.parent.left) {
					deleteN.parent.left = deleteN.left;
				} else {
					deleteN.parent.right = deleteN.left;
				}
			}
			return;
		}
		// case3: degree==2
		char val = succesor2(deleteN);//삭제할 노드의 오른쪽 노드를 root로 하는 서브트리 중 제일 큰 값을 삭제할 노드 위체에 놓는 방식
		deleteN.data = val;
//		char val = preDecessor(v);
//		v.data = q.data;
//		delete(v.left, q.data);
	}
	
	private char succesor2(Node v){
		Node p = v.right;
		while (p.left != null)
			p = p.left;
		char retVal = p.data;
		Node pParent = p.parent;
		p.parent = null;
		pParent = null;
		return retVal;
	}

	private char preDecessor2(Node v) {
		Node p = v.left;
		while (p.right != null)
			p = p.right;
		char retVal = p.data;
		Node pParent = p.parent;
		p.parent = null;
		pParent = null;
		return retVal;
	}

	public void showTree(){
		System.out.println(toString(root));
	}

	private String toString(Node t) {
		return inorder(t);
	}

	private String inorder(Node t) {
		if (t == null)
			return "";
		else
			return inorder(t.left) + " " + t.data + " " + inorder(t.right);
	}

	public static void main(String[] args) {
		char[] data = { 'M', 'Y', 'U', 'N', 'G', 'I', 'S', 'W','P', 'A', 'B', 'K', 'Z', 'X'};
		BinarySearchTree bst = new BinarySearchTree();

		// data insertion
		for (int i = 0; i < data.length; i++)
			bst.insert(data[i]);

		// print
		System.out.println("\nTree created : ");
		bst.showTree();

		System.out.println();

		// data delete
		bst.delete('S');// case1 : 삭제할 노드가 단말노드인 경우
		System.out.println("\nAfter deleting 'S'");
		bst.showTree();

		bst.delete('G');// case2 : 삭제할 노드의 degree==1
		System.out.println("\nAfter deleting 'G'");
		bst.showTree();

		bst.delete('U');// case3 : 삭제할 노드의 degree==2
		System.out.println("\nAfter deleting 'U'");
		bst.showTree();
	}
}
