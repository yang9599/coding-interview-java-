class ListNode {
	int val;
	ListNode next = null;
	ListNode(){
		
	}
	ListNode(int val) {
		this.val = val;
	}
}

public class MergeList {
	public ListNode merge(ListNode pHead1, ListNode pHead2) {
		if(pHead1 == null)
			return pHead2;
		if(pHead2 == null)
			return pHead1;
		
		ListNode mergeNode = null;
		if(pHead1.val < pHead2.val) {
			mergeNode = pHead1;
			mergeNode.next = merge(pHead1.next, pHead2);
		}
		else {
			mergeNode = pHead2;
			mergeNode.next = merge(pHead1, pHead2.next);
		}
		return mergeNode;
	}

}
