class ListNode {
	int val;
	ListNode next = null;
	ListNode(){
		
	}
	ListNode(int val) {
		this.val = val;
	}
}

public class FindKthToTail {
	public ListNode find(ListNode head, int k) {
		if(head == null || k <= 0)
			return null;
		ListNode fast = head;
		ListNode slow = head;
		while(k-- > 1) {
			if(fast.next != null)
				fast = fast.next;
			else {
				return null;
			}
		}
		while(fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}
		return slow;
	}

}
