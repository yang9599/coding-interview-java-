class ListNode {
	int val;
	ListNode next = null;
	ListNode(){
		
	}
	ListNode(int val) {
		this.val = val;
	}
}


import java.util.*;

public class PrintListReversingly_Iteratively {
	public static ArrayList<Integer> printList(ListNode node){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(node==null)
			return(list);
		Stack<ListNode> stack = new Stack<ListNode>();
		while(node != null) {
			stack.push(node);
			node = node.next;
		}
		while(!stack.isEmpty()) {
			list.add(stack.pop().val);
		}
		System.out.println(list);
		return(list);
	}
	
	public static void main(String[] args) {
		System.out.println("请输入节点值以-1结束");
		Scanner scan = new Scanner(System.in);
		ListNode pHead = new ListNode();
		int temp;
		//头结点
		if((temp=scan.nextInt())==-1) {
			System.out.println("该链表为空");
		}
		else
			pHead.val = temp;
		ListNode p = pHead;
		while((temp=scan.nextInt())!=-1) {
			ListNode q = new ListNode(temp);
			p.next = q;
			p = p.next;
		}
		scan.close();
		p.next = null;
		printList(pHead);
	}
}

/*运行结果
请输入节点值以-1结束
1 2 3 4 5 6 -1
[6, 5, 4, 3, 2, 1]
*/
