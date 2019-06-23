public class MinStack {
	//数据栈
	Stack<Integer> stack1 = new Stack<>();
	//辅助栈，存在每次push后的最小值
	Stack<Integer> stack2 = new Stack<>();
	public void push(int node) {
		stack1.push(node);
		if(stack2.isEmpty())
			stack2.push(node);
		else {
			if(stack2.peek() > node)
				stack2.push(node);
		}
	}
	public void pop() {
		if(stack1.pop() == stack2.peek())
			stack2.pop();
	}
	
	public int top() {
		return stack1.peek();
	}
	
	public int min() {
		return stack2.peek();
	}

}
