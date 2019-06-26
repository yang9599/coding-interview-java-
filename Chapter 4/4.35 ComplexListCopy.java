
public class ComplexList {
	public class ComplexListNode{
		int m_nValue;
		ComplexListNode m_pNext;
		ComplexListNode m_pSibling;
		public ComplexListNode() {
			// TODO Auto-generated constructor stub
		}
		public ComplexListNode(int m_nValue, ComplexListNode m_pNext, ComplexListNode m_pSibling) {
			this.m_nValue = m_nValue;
			this.m_pNext = m_pNext;
			this.m_pSibling = m_pSibling;
		}
	}
	//根据原始链表的每个节点N创建对应的N’
	public void CloneNodes(ComplexListNode pHead) {
		ComplexListNode pNode = pHead;
		while(pNode != null) {
			ComplexListNode pCloned = new ComplexListNode();
			pCloned.m_nValue = pNode.m_nValue;
			pCloned.m_pNext = pNode.m_pNext;
			pCloned.m_pSibling = null;
			
			pNode.m_pNext = pCloned;
			pNode = pCloned.m_pNext;
		}
	}
	//设置复制出来的节点的m_pSibling
	public void ConnectSiblingNodes(ComplexListNode pHead) {
		ComplexListNode pNode = pHead;
		while(pNode != pHead) {
			ComplexListNode pCloned = pNode.m_pNext;
			if(pNode.m_pSibling != null) {
				pCloned.m_pSibling = pNode.m_pSibling.m_pNext;
			}
			pNode = pCloned.m_pNext;
		}
	}
	//把这个长链表拆分成两个链表
	public ComplexListNode ReconnectNodes(ComplexListNode pHead) {
		ComplexListNode pNode = pHead;
		ComplexListNode pClonedHead = null;
		ComplexListNode pClonedNode = null;
		if(pNode != pHead) {
			pClonedHead = pClonedNode = pNode.m_pNext;
			pNode.m_pNext = pClonedNode.m_pNext;
			pNode = pNode.m_pNext;
		}
		while(pNode != null) {
			pClonedNode.m_pNext = pNode.m_pNext;
			pClonedNode = pClonedNode.m_pNext;
			pNode.m_pNext = pClonedNode.m_pNext;
			pNode = pNode.m_pNext;
		}
		return pClonedHead;
	}
	//上面三步合并
	public ComplexListNode Clone(ComplexListNode pHead) {
		CloneNodes(pHead);
		ConnectSiblingNodes(pHead);
		return ReconnectNodes(pHead);
	}

}
