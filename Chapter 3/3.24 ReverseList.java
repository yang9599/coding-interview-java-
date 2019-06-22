class ListNode {
	int val;
	ListNode next = null;
	ListNode(){
		
	}
	ListNode(int val) {
		this.val = val;
	}
}

public class ReverseList {
	public ListNode reverse(ListNode pHead) {
		if(pHead == null)
			return null;
		ListNode pReverseHead = null;
		ListNode pNode = pHead;
		ListNode pPrev = null;
		while(pNode != null) {
			ListNode pNext = pNode.next;
			if(pNext == null) 
				pReverseHead = pNode;
			pNode.next = pPrev;
			pPrev = pNode;
			pNode = pNext;
		}
		return pReverseHead;
	}

}
