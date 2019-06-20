class ListNode {
	int val;
	ListNode next = null;
	ListNode(){
		
	}
	ListNode(int val) {
		this.val = val;
	}
}

public class Node {
	//在O(1)时间内删除链表节点
	public void deletNode(ListNode head, ListNode deleted) {
		if(head == null || deleted==null)
			return;
		
		//如果链表中只有一个节点，删除后，头节点设置为null
		if(head == deleted)
			head = null;
		else {
			//如果删除的节点是尾节点，从头节点开始遍历
			if(deleted.next == null) {
				ListNode pointListNode = head;
				while(pointListNode.next.next != null) {
					pointListNode = pointListNode.next;
				}
				pointListNode.next = null;
			}
			else {
				deleted.val = deleted.next.val;
				deleted.next = deleted.next.next;
			}
		}
	}
	
	//删除链表中的重复节点
	public ListNode deleteDuplication(ListNode pHead) {
		if(pHead == null )
			return null;
		
		//新建一个节点，防止头节点被删除
		ListNode first = new ListNode(-1);
		first.next = pHead;
		ListNode p = pHead;
		ListNode preNode = first;
		
		while(p != null && p.next != null) {
			if(p.val == p.next.val) {
				int val = p.val;
				while(p != null && p.val == val)
					p = p.next;
				preNode.next = p;
			}
			else {
				preNode = p;
				p = p.next;
			}
		}
		return first.next;
	}
}
