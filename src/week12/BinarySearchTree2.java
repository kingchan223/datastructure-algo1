package week12;

/*부모가 없는 노드를 사용하는 경우*/
public class BinarySearchTree2 {
	public Node2 root;

	public BinarySearchTree2() {
		this.root = null;
	}

	public void insert(char x) {
		//비어있는 트리라면 root로 생성
		if (root == null){
			root = new Node2(x);
			System.out.println(x+": "+root);
			System.out.println();
		}
		//이미 존재하면 삽입불가
		else if (search(root, x) != null)
			return;
		//root보다 작다면 왼쪽에 삽입
		else if (x < root.data)
			insert(root.left, root, x);
		//root보다 크다면 오른쪽에 삽입
		else
			insert(root.right, root, x);
	}

	// 재귀적 insert
	private void insert(Node2 node, Node2 parent, char x){
		if (node == null) {
			Node2 newNode = new Node2(x);
//			System.out.println(x+": "+newNode);
//			System.out.println();
			if (x < parent.data)
				parent.left = newNode;
			else
				parent.right = newNode;
		} else if (x < node.data)
			insert(node.left, node, x);
		else
			insert(node.right, node, x);
	}
	
	// 재귀적 search
	public Node2 search(Node2 startNode, char x){
		Node2 p = startNode;
		// p==null에 의해 반환된다면 x가 없다는 것.
		// p.data==x에 의해 반환된다면 데이터가 있다느 것임.
		if (p == null || p.data == x) {
			return p;
		}
		else if (x < p.data)
			return search(p.left, x);
		else
			return search(p.right, x);
	}
	
	public void delete(char x) {
		delete(root, x);
	}
	
//	public Node2 findParent(Node2 node) {
//		return findParent(root, node);
//	}
	
	private Node2 findParent(Node2 node){
		return findParent(root, node);
	}
	
	/* 노드의 포인터 주소로 부모를 찾아주는 findParent */
	private Node2 findParent(Node2 parent, Node2 node) {
		//널 익셉션을 위해 필요
		if(parent.left==null) {
			if(parent.right == node)
				return parent;
			else
				return findParent(parent.right, node);
		}
		//널 익셉션을 위해 필요
		else if(parent.right==null){ 
			if(parent.left == node)
				return parent;
			else
				return findParent(parent.left, node);
		}
		else{
			if(parent.left == node || parent.right == node){
				return parent;
			}
			//왼쪽으로 가서 찾아야하는 경우
			if(parent.data > node.data){
				return findParent(parent.left, node);
			}else {
				return findParent(parent.right, node);
			}			
		}
	}
	
	private Node2 search2(Node2 node) {
		return search2(root, node);
		
	}

	private Node2 search2(Node2 root, Node2 node){
		if(root == node || root == null) {
			return root;
		}
		else if(root.data > node.data) {
			return search2(root.left, root);
		}
		else if(root.data < node.data){
			return search2(root.right, root);
		}else {//바이너리트리에서 같은 데이터의 노드가 있다는 것은 delete연산을 할 때만 가능하다. 그럼 같은 노드 둘은 부모자식 관계일 것이다.
			if(root.left == node)
				return root.left;
			else
				return root.right;
		}
	}
	
	private Node2 findParent2(Node2 node){
		if(search2(node)!=null)
			return findParent2(root, node);
		else return null;
	}
	
	//노드가 분명히 있다는 전재하에 실행되눈 메소드
	private Node2 findParent2(Node2 root, Node2 node) {
		
		if(root.left == null && root.right == null) {
			return root;
		}
		//left가 비어있으면 right로 가라
		if(root.left==null) {
			if(root.right == node)
				return root;
			else
				return findParent2(root.right, node);
		}
		//right가 비어있으면 left로 가라
		else if(root.right==null) { 
			if(root.left == node)
				return root;
			else
				return findParent2(root.left, node);
		}
		else{
			//왼쪽으로 가서 찾아야하는 경우
			if(root.data > node.data){
				return findParent2(root.left, node);
			}else if(root.data < node.data){
				return findParent2(root.right, node);
			}	
			else {
				return root;
			}
		}
	}
	
	private void delete(Node2 startNode, char x){
		// 삭제할 노드찾기
		Node2 deleteN = search(startNode, x); // deleteN = (U), (W2)
		// 삭제할 노드가 없는 경우
		if (deleteN == null)
			return;
		//삭제할 노드의 부모를 찾아주는 메소드
		/* case1: no child */
		if (deleteN.left == null && deleteN.right == null) {
			Node2 parent = findParent(deleteN);
			
			if (deleteN == parent.left)             
				parent.left = null;
			else
				parent.right = null;
			return;
		}
		
		/* case2: degree==1 */
		if (deleteN.left == null || deleteN.right == null) {
			Node2 parent = findParent(deleteN);//findParent는 부모부터 찾기 떄문에 root로 시작하자
			// 삭제할 노드의 오른쪽 자식이 존재
			if (deleteN.right != null) {
				// 부모가 새로운 직손 자식을 보게 하기
				if (deleteN == parent.left) {
					parent.left = deleteN.right;
				} else {
					parent.right = deleteN.right;
				}
			// 삭제할 노드의 왼쪽이 존재
			} else {
				if (deleteN == parent.left) {
					parent.left = deleteN.left;
				} else {
					parent.right = deleteN.left;
				}
			}
			return;
		}
		
		/* case3: degree==2 */
		/* 방법1: 왼쪽 트리의 최대 노드로 매꿔주던가. predecessor */
		/* 방법2: 오른쪽 트리의 최소 노드로 매꿔주던가. succesor */
		// 삭제할 노드의 오른쪽 노드를 root로 하는 서브트리 중 제일 큰 값을 삭제할 노드 위체에 놓는 방식
		Node2 q = succesor(deleteN);//삭제할 노드의 오른쪽 노드를 root로 하는 서브트리 중 제일 큰 값을 삭제할 노드 위체에 놓는 방식
		deleteN.data = q.data;
		//System.out.println("deleteN.right:"+deleteN.right);
		delete(deleteN.right, q.data);
//		Node q = preDecessor(v);
//		v.data = q.data;
//		delete(v.left, q.data);
	}

	private Node2 succesor(Node2 v) {
		Node2 p = v.right;
		while (p.left != null)
			p = p.left;
		return p;
	}

//	private Node2 preDecessor(Node2 v) {
//		Node2 p = v.left;
//		while (p.right != null)
//			p = p.right;
//		return p;
//	}

	public void showTree() {
		System.out.println(toString(root));
	}

	private String toString(Node2 t) {
		return inorder(t);
	}

	private String inorder(Node2 t) {
		if (t == null)
			return "";
		else
			return inorder(t.left) + " " + t.data + " " + inorder(t.right);
	}


	public static void main(String[] args) {
		char[] data = { 'M', 'Y', 'U', 'N', 'G', 'I', 'S', 'W', 'X' , 'L', 'E', 'ㄱ', 'ㄷ'};
		BinarySearchTree2 bst2 = new BinarySearchTree2();

		// data insertion
		for (int i = 0; i < data.length; i++)
			bst2.insert(data[i]);
		// print
		System.out.println("\nTree created : ");
		bst2.showTree();

		// data delete
		bst2.delete('S');// case1 : 삭제할 노드가 단말노드인 경우
		System.out.println("\nAfter deleting 'S'");
		bst2.showTree();

		bst2.delete('G');// case2 : 삭제할 노드의 degree==1
		System.out.println("\nAfter deleting 'G'");
		bst2.showTree();

		bst2.delete('U');// case3 : 삭제할 노드의 degree==2
		System.out.println("\nAfter deleting 'U'");
		bst2.showTree();
		
		System.out.println(bst2.root.data);
	}
}
