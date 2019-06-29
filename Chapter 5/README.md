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
### 面试题42
> #### 题目：连续子数组的最大和
> #### 思路：如果加到一个负数，看是否加负数后的下一个的和比没有加负数前的和大，如果更大，则继续加。
```java
	public int FindGreatestSumOfSubArray(int[] pData) {
		if(pData==null || pData.length<=0)
			return -9999;
		int cur = pData[0];
		int greate = pData[0];
		for(int i=1; i<pData.length; i++) {
			if(cur < 0)
				cur = pData[i];
			else {
				cur += pData[i];
			}
			if(cur > greate)
				greate = cur;
		}
		return greate;
		
	}
```
### 面试题43
> #### 题目：1~n整数中1出现的次数
> #### 思路：考虑将n的十进制的每一位单独拿出讨论，每一位的值记为weight。从1到n，每增加1，weight就会加1，当weight加到9时，再加1又会回到0重新开始。
```java
	public int NumberOf1Between1AndN(int n) {
		if(n <= 0)
			return -9999;
		int count = 0;
		int base = 1;
		int round = n;
		while(round > 0) {
			int weight = round % 10;
			round /= 10;
			count += round * base;
			if(weight == 1)
				count += (n%base)+1;
			else if(weight > 1)
				count += base;
			base *= 10;
		}
		return count;
	}
```
### 面试题44
> #### 题目：数字序列中某一位的数字
> #### 思路：序列的前10位是0~9这10个只有一位数的数字。接下来180位数字是90个10~99的两位数。接下来的2700位是900个100~999的三位数...
```java
	int digitAtIndex(int index) {
		if(index < 0)
			return -9999;
		int digits = 1;
		while(true) {
			int numbers = countOfInteger(digits);
			if(index < numbers*digits)
				return digitAtIndex(index, digits);
		}
	}
	//例如输入2，则返回两位数（10~99）的个数90
	int countOfInteger(int digits) {
		if(digits == 1)
			return 10;
		int count = (int)(Math.pow(10, digits-1));
		return 9*count;
	}
	//当知道要找的那一位数字位于某m位数之中后，就可以使用如下函数找出那一位数字
	int digitAtIndex(int index, int digits) {
		int number = beginNumber(digits) + index / digits;
		int indexFromRight = digits - index % digits;
		for(int i=1; i<indexFromRight; i++) {
			number /= 10;
		}
		return number%10;
	}
	int beginNumber(int digits) {
		if(digits == 1)
			return 0;
		return (int)(Math.pow(10, digits-1));
	}
```
### 面试题45
> #### 题目：把数组排成最小的数
> #### 思路：先将整型数组转换成String数组，然后将String数组排序，最后将排好序的字符串数组拼接出来。
```java
	public String PrintMinNumber(int[] numbers) {
		if(numbers==null || numbers.length==0)
			return "";
		int len = numbers.length;
		String[] str = new String[len];
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<len; i++)
			str[i] = String.valueOf(numbers[i]);
		Arrays.sort(str, new Comparator<String>() {
			public int compare(String str1, String str2) {
				String c1 = str1 + str2;
				String c2 = str2 + str1;
				return c1.compareTo(c2);
			}
		});
		for(int i=0; i<len; i++)
			sb.append(str[i]);
		return sb.toString();
	}
```
### 面试题46
> #### 题目：把数组翻译成字符串
> #### 思路：可以选一个数字或两个连续的数字（10~25）翻译成一个字符。 
```java
public class NumberToString {
	public int GetTranslationCount(int number) {
		if(number <= 0)
			return -9999;
		return GetTranslationCount(String.valueOf(number));
	}
	public int GetTranslationCount(String number) {
		int length = number.length();
		int[] countRecords = new int[length];
		countRecords[length-1] = 1;
		int count;
		for(int i=length-2;i>=0;i--) {
			count = countRecords[i+1];
			int digit1 = number.charAt(i)-'0';
			int digit2 = number.charAt(i+1)-'0';
			int connectedNumber = digit1*10+digit2;
			if(connectedNumber>=10 && connectedNumber <=25) {
				if(i < length-2) {
					count += countRecords[i+2];
				}
				else if(i == length-2){
					count += 1;
				}
			}
			countRecords[i] = count;
		}
		count = countRecords[0];
		return count;
	}

}
```
### 面试题47
> #### 题目：礼物的最大价值
> #### 思路：动态规划 f(i,j)=max(f(i-1,j),f(i,j-1)+gift[i,j])
```java
	public int getMaxValue(int[][] values) {
		if(values == null)
			return 0;
		int rows = values.length;
		int cols = values[0].length;
		if(rows<=0 || cols<=0)
			return 0;
		int[][] maxValues = new int[rows][cols];
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				int left = 0;
				int up = 0;
				if(i > 0)
					up = maxValues[i-1][j];
				if(j > 0)
					left = maxValues[i][j-1];
				maxValues[i][j] = Math.max(left, up) + values[i][j];
			}
		}
		return maxValues[rows-1][cols-1];
	}
```
