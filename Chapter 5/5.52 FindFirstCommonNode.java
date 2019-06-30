
public class CommonNode {
	class ListNode{
		int val;
		ListNode next = null;
		public ListNode() {
			// TODO Auto-generated constructor stub
		}
		public ListNode(int val) {
			this.val = val;
		}
	}
	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		if(pHead1==null || pHead2==null)
			return null;
		ListNode node1 = pHead1;
		ListNode node2 = pHead2;
		int length1 = 0, length2 = 0;
		while(node1 != null) {
			length1 += 1;
			node1 = node1.next;
		}
		while(node2 != null) {
			length2 += 1;
			node2 = node2.next;
		}
		if(length1 > length2) {
			int k = length1-length2;
			while(k != 0) {
				pHead1 = pHead1.next;
				k--;
			}
		}
		else {
			int k = length2 - length1;
			while(k != 0) {
				pHead2 = pHead2.next;
				k--;
			}
		}
		while(pHead1 != pHead2) {
			pHead1 = pHead1.next;
			pHead2 = pHead2.next;
		}
		return pHead1;
	}

}
