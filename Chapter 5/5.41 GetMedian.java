import java.util.*;

public class Median {
	int count = 0;
	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(16, new Comparator<Integer>() { 
		public int compare(Integer o1, Integer o2){
			return o2.compareTo(o1);
		}
	});
	public void Insert(Integer num) {
		count++;
		//当数据的数目为奇数时，进入大根堆
		if((count&1) == 1) {
			minHeap.offer(num);
			maxHeap.offer(minHeap.poll());
		}
		else {
			maxHeap.offer(num);
			minHeap.offer(maxHeap.poll());
		}
	}
	public Double GetMedian() {
		if(count == 0)
			return null;
		if((count&1)==1)
			return Double.valueOf(maxHeap.peek());
		else {
			return Double.valueOf((minHeap.peek()+maxHeap.peek()))/2;
		}
	}

}
