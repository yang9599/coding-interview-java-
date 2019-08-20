class ListNode {
	int val;
	ListNode next = null;
	ListNode(){
		
	}
	ListNode(int val) {
		this.val = val;
	}
}

public class EntryNodeOfLoop {
	public ListNode meeting(ListNode pHead) {
		if(pHead == null)
			return null;
		ListNode pSlow = pHead.next;
		if(pSlow == null)
			return null;
		ListNode pFast = pSlow.next;
		while(pFast != null && pSlow != null) {
			if(pFast == pSlow)
				return pFast;
			pSlow = pSlow.next;
			pFast = pFast.next;
			if(pFast != null)
				pFast = pFast.next;
		}
		return null;
	}
	public ListNode loop(ListNode pHead) {
		ListNode meetingNode = meeting(pHead);
		if(meetingNode == null)
			return null;
		ListNode pNode1 = meetingNode;
		ListNode pNode2 = pHead;
		while(pNode1 != pNode2) {
			pNode1 = pNode1.next;
			pNode2 = pNode2.next;
		}
		return pNode2;
	}

}
