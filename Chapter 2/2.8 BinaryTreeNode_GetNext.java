public class BinaryTreeNode_GetNext {
	public static class Node{
		int val;
		Node left;
		Node right;
		Node parent;
		public Node() {
			
		}
		public Node(int val) {
			this.val = val;
		}
		public Node(int val, Node parent) {
			this.val = val;
			this.parent = parent;
		}
	}
	public static Node getNext(Node pNode) {
		if(pNode==null)
			return(null);
		Node pNext = null;
		if(pNode.right != null) {
			Node pRight = pNode.right;
			while(pRight != null)
				pRight = pRight.left;
			pNext = pRight;
		}
		else if(pNode.parent != null){
			Node pCurrent = pNode;
			Node pParent = pNode.parent;
			while(pParent!=null && pCurrent==pParent.right) {
				pCurrent = pParent;
				pParent = pParent.parent;
			}
			pNext = pParent;
		}
		return(pNext);
	}
}
