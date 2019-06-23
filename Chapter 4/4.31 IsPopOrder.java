import java.util.Stack;

public class PushPopOrder {
	public boolean IsPopOrder(int[] pushA, int[] popA) {
		if(pushA == null || popA == null)
			return false;
		Stack<Integer> stack = new Stack<Integer>();
		int index = 0;
		
		for(int i=0; i<pushA.length; i++) {
			stack.push(pushA[i]);
			while(!stack.isEmpty() && stack.peek() == popA[index]) {
				stack.pop();
				index++;
			}
		}
		return stack.isEmpty();
		
	}

}
