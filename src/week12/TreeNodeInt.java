package week12;

public class TreeNodeInt {
	int data;
	TreeNodeInt left;
	TreeNodeInt right;
	public TreeNodeInt(TreeNodeInt l, int val, TreeNodeInt r) {
		this.data = val;
		this.left = l;
		this.right = r;
	}
	
	public String toString() {
		return ""+data;
	}
}
