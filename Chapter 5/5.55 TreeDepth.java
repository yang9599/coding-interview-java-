
public class DepthOfBinaryTree {
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
	//二叉树的深度
	public int TreeDepth(BinaryTreeNode pRoot) {
		if(pRoot == null)
			return 0;
		int nLeft = TreeDepth(pRoot.m_pLeft);
		int nRight = TreeDepth(pRoot.m_pRight);
		return (nLeft>nRight)?(nLeft+1):(nRight+1);
	}
	public boolean IsBalanced(BinaryTreeNode pRoot) {
		if(pRoot==null || (pRoot.m_pLeft==null && pRoot.m_pRight==null))
			return true;
		int left = TreeDepth(pRoot.m_pLeft);
		int right = TreeDepth(pRoot.m_pRight);
		int dif = left - right;
		if(Math.abs(dif) <= 1)
			return true;
		return(IsBalanced(pRoot.m_pLeft) && IsBalanced(pRoot.m_pRight));
	}

}
