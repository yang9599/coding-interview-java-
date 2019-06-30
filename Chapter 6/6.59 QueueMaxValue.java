import java.util.*;

public class QueueMaxValue {
	//滑动窗口的最大值
	public ArrayList<Integer> maxInWindows(int[] num, int size){
		ArrayList<Integer> arr = new ArrayList<Integer>();
		if(num == null)
			return arr;
		if(num.length<size || size<=0)
			return arr;
		Deque<Integer> queue = new LinkedList<Integer>();
		for(int i=0; i<num.length; i++) {
			while(!queue.isEmpty() && num[i]>=num[queue.getLast()])
				queue.pollLast();
			while(!queue.isEmpty() && queue.getFirst()<i-(size-1))
				queue.pollFirst();
			queue.offerLast(i);
			if(i+1 >= size)
				arr.add(num[queue.getFirst()]);
		}
		return arr;
	}
	//队列的最大值
	private ArrayDeque<InternalData>  data = new ArrayDeque<InternalData>();
	private ArrayDeque<InternalData> maximum = new ArrayDeque<InternalData>();
	private class InternalData{
		int number;
		int index;
		public InternalData(int number,int index) {
			this.number=number;
			this.index=index;
		}
	}
	private int curIndex;
	
	public void push_back(int number) {
		InternalData curData = new InternalData(number,curIndex);
		data.addLast(curData);
		
		while(!maximum.isEmpty() && maximum.getLast().number<number)
			maximum.removeLast();
		maximum.addLast(curData);
		
		curIndex++;
	}
	
	public void pop_front() {
		if(data.isEmpty()) {
			System.out.println("队列为空，无法删除！");
			return;
		}
		InternalData curData = data.removeFirst();
		if(curData.index==maximum.getFirst().index)
			maximum.removeFirst();
	}
	
	public int max() {
		if(maximum==null){
			System.out.println("队列为空，无法删除！");
			return 0;
		}
		return maximum.getFirst().number;
	}


}
