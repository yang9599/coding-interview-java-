	public boolean symmetrical(BinaryTreeNode pRoot) {
		return symmetrical(pRoot, pRoot);
	}
	public boolean symmetrical(BinaryTreeNode pRoot1, BinaryTreeNode pRoot2) {
		if(pRoot1 == null && pRoot2 == null)
			return true;
		if(pRoot1 == null || pRoot2 == null)
			return false;
		if(pRoot1.m_dbValue != pRoot2.m_dbValue)
			return false;
		return symmetrical(pRoot1.m_pLeft, pRoot2.m_pRight) && symmetrical(pRoot1.m_pRight, pRoot2.m_pLeft);
		
	}
