
public class SerializeTree {
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
	public String Serialize(BinaryTreeNode root) {
		StringBuffer sb = new StringBuffer();
		if(root == null) {
			sb.append("$,");
			return sb.toString();
		}
		sb.append(root.m_dbValue+",");
		sb.append(Serialize(root.m_pLeft));
		sb.append(Serialize(root.m_pRight));
		return sb.toString();
	}
	public int index = -1;
	public BinaryTreeNode Deserialize(String str) {
		index++;
		int len = str.length();
		String[] s = str.split(",");
		BinaryTreeNode node = null;
		
		if(index >= len)
			return null;
		if(!s[index].equals("$")) {
			node = new BinaryTreeNode(Integer.valueOf(s[index]));
			node.m_pLeft = Deserialize(str);
			node.m_pRight = Deserialize(str);
		}
		return node;
	}

}
