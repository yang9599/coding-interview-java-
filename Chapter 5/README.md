### 面试题39
> #### 题目：数组中出现次数超过一半的数字
> #### 思路：遍历数组，下一个数字与保存的数字相同则次数加一，否则减一。当最后次数为1时，则就是我们要找的结果
```java
	public int MoreThanHalfNum(int[] numbers) {
		if(numbers == null)
			return -9999;
		int result = numbers[0];
		int times = 1;
		for(int i=1; i<numbers.length; i++) {
			if(times == 0) {
				result = numbers[i];
				times = 1;
			}
			else if(numbers[i] == result)
				times++;
			else {
				times--;
			}
		}
		//验证出现次数最多的数是不是出现次数超过数组长度的一半
		int sum = 0;
		for(int j=0; j<numbers.length; j++) {
			if(numbers[j] == result)
				sum++;
		}
		if(sum*2 > numbers.length)
			return result;
		else 
			return -9999;
	}
```
### 面试题40
> #### 题目：最小的k个数
> #### 思路：先将前K个数放入数组，进行堆排序，若之后的数比它还小，将数组中的最大的数与之后比它小的数进行调整
```java
	public ArrayList<Integer> GetLeastNumbers(int[] input, int k) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(input == null || k<=0 || k>input.length)
			return null;
		
		int[] kArray = Arrays.copyOfRange(input, 0, k);
		//创建大根堆
		buildHeap(kArray);
		for(int i=k; i<input.length; i++) {
			if(input[i] < kArray[0]) {
				kArray[0] = input[i];
				maxHeap(kArray, 0);
			}
		}
		for(int i=kArray.length-1; i>=0; i--) {
			list.add(kArray[i]);
		}
		return list;
	}
	public void buildHeap(int[] input) {
		for(int i=input.length/2-1; i>=0; i--)
			maxHeap(input, i);
	}
	public void maxHeap(int[] input, int i) {
		int left = 2*i+1;
		int right = left+1;
		int max = 0;
		if(left < input.length && input[left] > input[i])
			max = left;
		else {
			max = i;
		}
		if(right < input.length && input[right] > input[max])
			max = right;
		if(max != i) {
			int temp = input[i];
			input[i] = input[max];
			input[max] = temp;
			maxHeap(input, max);
		}
	}
```
### 面试题41
> #### 题目：数据流中的中位数
> #### 思路：创建优先级队列维护大顶堆和小顶堆两个堆，并且小顶堆的值都大于大顶堆的值，2个堆个数的差值小于等于1，所以当插入个数为奇数时：大顶堆个数就比小顶堆多1，中位数就是大顶堆堆头；当插入个数为偶数时，使大顶堆个数跟小顶堆个数一样，中位数就是 2个堆堆头平均数。也可使用集合类的排序方法。
```java
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
```
