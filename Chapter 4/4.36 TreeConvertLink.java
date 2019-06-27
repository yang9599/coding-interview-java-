
public class TreeConvertLink {
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
	public BinaryTreeNode Convert(BinaryTreeNode pRootOfTree) {
		BinaryTreeNode lastlist = ConvertNode(pRootOfTree, null);
		BinaryTreeNode pHead = lastlist;
		while(pHead!=null && pHead.m_pLeft!=null)
			pHead = pHead.m_pLeft;
		return pHead;
	}
	public BinaryTreeNode ConvertNode(BinaryTreeNode root, BinaryTreeNode lastlist) {
		if(root == null)
			return null;
		BinaryTreeNode cur = root;
		if(cur.m_pLeft != null) {
			lastlist = ConvertNode(cur.m_pLeft, lastlist);
		}
		cur.m_pLeft = lastlist;
		if(lastlist != null)
			lastlist.m_pRight = cur;
		lastlist = cur;
		if(cur.m_pRight != null)
			lastlist = ConvertNode(cur.m_pRight, lastlist);
		return lastlist;
	}

}
