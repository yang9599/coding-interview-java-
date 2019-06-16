
import java.util.*;

public class CQueue {
	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();
	public CQueue() {
		
	}
	public void push(int num) {
		stack1.push(num);
	}
	public int pop() throws Exception{
		if(stack1.isEmpty() && stack2.isEmpty()) {
			throw new Exception("栈为空！");
		}
		if(stack2.isEmpty()) {
			while(!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		return(stack2.pop());
	}
	public static void main(String[] args) throws Exception {
		CQueue q = new CQueue();
		q.push(5);
		q.push(9);
		System.out.println(q.pop());
		System.out.println(q.pop());
	}
}
