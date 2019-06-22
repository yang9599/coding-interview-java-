
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
		//得到环中节点的数量
		int nodesInLoop = 1;
		ListNode pNode1 = meetingNode;
		while(pNode1.next != meetingNode) {
			pNode1 = pNode1.next;
			nodesInLoop++;
		}
		//先移动pNode1，次数为环中节点的数目
		pNode1 = pHead;
		for(int i=0; i<nodesInLoop; i++) {
			pNode1 = pNode1.next;
		}
		ListNode pNode2 = pHead;
		while(pNode1 != pNode2) {
			pNode1 = pNode1.next;
			pNode2 = pNode2.next;
		}
		return pNode1;
	}

}
