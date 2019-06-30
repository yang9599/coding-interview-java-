
public class GetBinaryTree {
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
	//中序遍历二叉树并查找
	public BinaryTreeNode KthNode(BinaryTreeNode pRoot, int k) {
		if(pRoot==null || k==0)
			return null;
		return KthNodeCore(pRoot, k);
	}
	public BinaryTreeNode KthNodeCore(BinaryTreeNode pRoot, int k) {
		BinaryTreeNode target = null;
		if(pRoot.m_pLeft != null)
			target = KthNodeCore(pRoot.m_pLeft, k);
		if(target == null) {
			if(k==1)
				target = pRoot;
			k--;
		}
		if(target==null && pRoot.m_pRight!=null)
			target = KthNodeCore(pRoot.m_pRight, k);
		return target;
	}

}
