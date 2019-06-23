public class MirrorRecursively {
	public class BinaryTreeNode{
		double m_dbValue;
		BinaryTreeNode m_pLeft;
		BinaryTreeNode m_pRight;
		public BinaryTreeNode() {
			
		}
		public BinaryTreeNode(double m_dbValue) {
			this.m_dbValue = m_dbValue;
		}
		public BinaryTreeNode(double m_dbValue, BinaryTreeNode m_pLeft) {
			this.m_dbValue = m_dbValue;
			this.m_pLeft = m_pLeft;
		}
		public BinaryTreeNode(double m_dbValue, BinaryTreeNode m_pLeft, BinaryTreeNode m_pRight) {
			this.m_dbValue = m_dbValue;
			this.m_pLeft = m_pLeft;
			this.m_pRight = m_pRight;
		}
	}
	public void mirror(BinaryTreeNode pNode) {
		if(pNode == null)
			return;
		if(pNode.m_pLeft == null && pNode.m_pRight == null)
			return;
		
		BinaryTreeNode pTemp = pNode.m_pLeft;
		pNode.m_pLeft = pNode.m_pRight;
		pNode.m_pRight = pTemp;
		
		if(pNode.m_pLeft != null)
			mirror(pNode.m_pLeft);
		if(pNode.m_pRight != null)
			mirror(pNode.m_pRight);
	}

}
