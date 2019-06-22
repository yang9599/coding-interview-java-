
public class TreeSubStructure {
	public static class BinaryTreeNode{
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
	
	public static boolean Equal(double num1, double num2) {
		if(Math.abs(num1-num2)<0.0000001)
			return true;
		else {
			return false;
		}
	}
	
	public static boolean DoesTree1HaveTree2(BinaryTreeNode pRoot1, BinaryTreeNode pRoot2) {
		//约定空树是任意一个树的子结构
		if(pRoot2 == null) 
			return true;
		if(pRoot1 == null)
			return false;
		if(!Equal(pRoot1.m_dbValue, pRoot2.m_dbValue))
			return false;
		return(DoesTree1HaveTree2(pRoot1.m_pLeft, pRoot2.m_pLeft) && DoesTree1HaveTree2(pRoot1.m_pRight, pRoot2.m_pRight));
	}
	
	public static boolean HasSubtree(BinaryTreeNode pRoot1, BinaryTreeNode pRoot2) {
		boolean result = false;
		if(pRoot1 != null && pRoot2 != null) {
			if(Equal(pRoot1.m_dbValue, pRoot2.m_dbValue))
				result = DoesTree1HaveTree2(pRoot1, pRoot2);
			if(!result)
				result = HasSubtree(pRoot1.m_pLeft, pRoot2);
			if(!result)
				result = HasSubtree(pRoot1.m_pRight, pRoot2);
		}
		return result;
	}

}
