import java.util.ArrayList;
import java.util.LinkedList;

public class PrintTree {
	public class BinaryTreeNode{
		int m_dbValue;
		BinaryTreeNode m_pLeft;
		BinaryTreeNode m_pRight;
		public BinaryTreeNode() {
			
		}
		public BinaryTreeNode(int m_dbValue) {
			this.m_dbValue = m_dbValue;
		}
		public BinaryTreeNode(int m_dbValue, BinaryTreeNode m_pLeft) {
			this.m_dbValue = m_dbValue;
			this.m_pLeft = m_pLeft;
		}
		public BinaryTreeNode(int m_dbValue, BinaryTreeNode m_pLeft, BinaryTreeNode m_pRight) {
			this.m_dbValue = m_dbValue;
			this.m_pLeft = m_pLeft;
			this.m_pRight = m_pRight;
		}
	}
	public ArrayList<Integer> PrintFromTopToBottom(BinaryTreeNode root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(root == null)
			return list;
		LinkedList<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			BinaryTreeNode node = queue.poll();
			list.add(node.m_dbValue);
			if(node.m_pLeft != null) {
				queue.addLast(node.m_pLeft);
			}
			if(node.m_pRight != null)
				queue.addLast(node.m_pRight);
		}
		return list;
	}

}
