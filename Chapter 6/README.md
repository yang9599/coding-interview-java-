### 面试题53
> #### 题目1：数字在排序数组中出现的次数
> #### 思路：二分法遍历统计
```java
	public int GetNumberOfK(int[] data, int k) {
		int result = 0;
		int mid = data.length/2;
		if(data==null || data.length==0)
			return 0;
		if(data.length == 1) {
			if(data[0] == k)
				return 1;
			else {
				return 0;
			}
		}
		if(k < data[mid]) {
			result += GetNumberOfK(Arrays.copyOfRange(data, 0, mid), k);
		}
		else if(k > data[mid])
			result += GetNumberOfK(Arrays.copyOfRange(data, mid, data.length), k);
		else {
			for(int i=mid; i<data.length; i++) {
				if(data[i] == k)
					result++;
				else {
					break;
				}
			}
			for(int i=mid-1; i>=0; i--) {
				if(data[i] == k)
					result++;
				else {
					break;
				}
			}
		}
		return result;
	}
```
> #### 题目2：0~n-1中缺失的数字
> #### 思路：二分法遍历统计
```java
	public int GetMissingNumber(int[] numbers) {
		int length = numbers.length;
		if(numbers==null || length==0)
			return -1;
		int left = 0;
		int right = length-1;
		while(left <= right) {
			int middle = (left+right) >> 1;
			if(numbers[middle] != middle) {
				if(middle==0 || numbers[middle-1]==middle-1)
					return middle;
				right = middle-1;
			}
			else {
				left = middle+1;
			}
		}
		if(left == length)
			return length;
		//无效的输入
		return -1;
	}
```
### 面试题54
> #### 题目：二叉搜索树的第K大节点
> #### 思路：中序遍历
```java
	public BinaryTreeNode KthNode(BinaryTreeNode pRoot, int k) {
		if(pRoot==null || k==0)
			return null;
		return KthNodeCore(pRoot, k);
	}
	public BinaryTreeNode KthNodeCore(BinaryTreeNode pRoot, int k) {
		BinaryTreeNode target = null;
		if(pRoot.m_pLeft != null)
			target = KthNodeCore(pRoot.m_pLeft, k);
		if(target == null) {
			if(k==1)
				target = pRoot;
			k--;
		}
		if(target==null && pRoot.m_pRight!=null)
			target = KthNodeCore(pRoot.m_pRight, k);
		return target;
	}
```
### 面试题55
> #### 题目1：二叉树的深度
> #### 思路：递归遍历左右子树
```java
	public int TreeDepth(BinaryTreeNode pRoot) {
		if(pRoot == null)
			return 0;
		int nLeft = TreeDepth(pRoot.m_pLeft);
		int nRight = TreeDepth(pRoot.m_pRight);
		return (nLeft>nRight)?(nLeft+1):(nRight+1);
	}
```
> #### 题目2：平衡二叉树
> #### 思路：遍历左右子树的深度，如果两边深度相差小于等于1，则是平衡树。
```java
	public boolean IsBalanced(BinaryTreeNode pRoot) {
		if(pRoot==null || (pRoot.m_pLeft==null && pRoot.m_pRight==null))
			return true;
		int left = TreeDepth(pRoot.m_pLeft);
		int right = TreeDepth(pRoot.m_pRight);
		int dif = left - right;
		if(Math.abs(dif) <= 1)
			return true;
		return(IsBalanced(pRoot.m_pLeft) && IsBalanced(pRoot.m_pRight));
	}
```
### 面试题56
> #### 题目1：数组中只出现一次的两个数字
> #### 思路：异或运算
```java
	public void FindNumsAppearOnce(int[] array, int[] num1, int[] num2) {
		int length = array.length;
		if(length == 2) {
			num1[0] = array[0];
			num2[0] = array[1];
			return;
		}
		int bitResult = 0;
		for(int i=0; i<length; i++)
			bitResult ^= array[i];
		int index = findFirst(bitResult);
		for(int i=0; i<length; i++) {
			if(isBit(array[i], index)) {
				num1[0] ^= array[i];
			}
			else {
				num2[0] ^= array[i];
			}
		}
	}
	public int findFirst(int bitResult) {
		int index = 0;
		while(((bitResult & 1)==0) && index < 32) {
			bitResult >>= 1;
			index++;
		}
		return index;
	}
	public boolean isBit(int target, int index) {
		return ((target >> index)&1) == 1;
	}
```
> #### 题目2：数组中唯一只出现一次的两个数字
> #### 思路：位运算后，如果某一位的和能够被3整除，那么那个只出现一次的数字二进制表示中对应的那一位则是0，否则就是1
```java
	public int FindNumberAppearOnce(int[] numbers) {
		int length = numbers.length;
		if(numbers==null || length==0)
			return -9999;
		int[] bitSum = new int[32];
		for(int i=0; i<bitSum.length; i++)
			bitSum[i] = 0;
		for(int i=0; i<length; i++) {
			int bitMask = 1;
			for(int j=31; j>=0; j--) {
				int bit = numbers[i] & bitMask;
				if(bit != 0)
					bitSum[j] += 1;
				bitMask = bitMask << 1;
			}
		}
		int result = 0;
		for(int i=0; i<32; i++) {
			result = result << 1;
			result += bitSum[i] % 3;
		}
		return result;	
	}
```
### 面试题57
> #### 题目1：和为s的两个数字
> #### 思路：前后一个指针，如果前后两个指针对应的数字之和大于s，则后指针向前移动，否则前指针往后移动。
```java
	public static boolean FindNumbersWithSum(int[] data, int sum, int[] num1, int[] num2) {
		int length = data.length;
		if(data==null || length<=0 || sum<data[0])
			return false;
		int end = length-1;
		int start = 0;
		while(start <= end) {
			if(data[start]+data[end] > sum)
				end--;
			else if(data[start]+data[end] < sum)
				start++;
			else {
				num1[0] = data[start];
				num2[0] = data[end];
				return true;
			}
		}
		return false;
	}
```
> #### 题目2：和为s的连续正数序列
> #### 思路：首先定义一个small为1，big为2.如果从small到big的序列的和大于s，则增大small的值。如果小于s，则可以减小big的值
```java
	public static void FindContinuousSequence(int sum) {
		if(sum < 3)
			return;
		int small = 1;
		int big = 2;
		int middle = (1+sum)/2;
		int curSum = small + big;
		while(small < middle) {
			if(curSum == sum)
				PrintContinuousSequence(sum, small, big);
			while(curSum>sum && small<middle) {
				curSum -= small;
				small++;
				if(curSum == sum)
					PrintContinuousSequence(sum, small, big);
			}
			big++;
			curSum += big;
		}
	}
	public static void PrintContinuousSequence(Integer sum, int small, int big) {
		for(int i=small; i<big; i++) {
			System.out.print(String.valueOf(i)+"+");
		}
		System.out.print(String.valueOf(big)+"="+String.valueOf(sum));
		System.out.println();
	}
```
### 面试题58
> #### 题目1：翻转单词顺序
> #### 思路：第一步翻转句子中所有的字符，第二部再翻转每个单词中字符的顺序
```java
	public static String Reverse(String str) {
		StringBuilder sb = new StringBuilder();
		for(int i=str.length()-1; i>=0; i--)
			sb.append(str.charAt(i));
		return String.valueOf(sb);
	}
	public static String ReverseSentence(String str) {
		if(str==null || str.length()==0)
			return str;
		if(str.trim().length() == 0)
			return str;
		StringBuilder sb = new StringBuilder();
		String re = Reverse(str);
		String[] s = re.split(" ");
		for(int i=0; i<s.length-1;i++)
			sb.append(Reverse(s[i])+" ");
		sb.append(Reverse(s[s.length-1]));
		return String.valueOf(sb);
	}
```
> #### 题目2：左旋转字符串
> #### 思路：利用一个StringBuilder存储后面的字符串，再append前面的字符串。
```java
	public static String leftRotateString(String str, int n) {
		StringBuilder sb = new StringBuilder();
		for(int i=n; i<str.length(); i++) {
			sb.append(str.charAt(i));
		}
		for(int i=0; i<n; i++)
			sb.append(str.charAt(i));
		return String.valueOf(sb);
	}
```
### 面试题59
> #### 题目1：滑动窗口的最大值
> #### 思路：利用一个双端队列来存储当前队列里的最大值以及之后可能的最大值。
```java
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
```
> #### 题目2：队列的最大值
> #### 思路：除了定义一个队列data存储数值，还需额外用一个队列maxmium存储可能的最大值；此外，还要定义一个数据结构，用于存放数据以及当前的index值，用于删除操作时确定是否删除maxmium中最大值。
```java
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
```
### 面试题60
> #### 题目：n个骰子的点数
> #### 思路：基于循环求骰子点数。第一轮循环中，第一个数组中的第n个数字表示骰子和为n出现的次数。在下一轮循环中，加上一个新的骰子，此时和为n的骰子出现的次数应该等于上一轮循环中骰子点数和为n-1,n-2,n-3,n-4,n-5,n-6的次数的综合
```java
	public void PrintProbability(int number) {
		if(number < 1)
			return;
		int g_maxValue = 6;
		int[][] probabilities = new int[2][];
		probabilities[0] = new int[g_maxValue*number+1];
		probabilities[1] = new int[g_maxValue*number+1];
		int flag = 0;
		
		//当第一次掷骰子时，有6种可能，每种可能出现一次
		for(int i=1; i<=g_maxValue; i++) {
			probabilities[0][i] = 1;
		}
		//当第二次掷骰子时，假设第一个数组中的第n个数字表示骰子和为n出现的次数
		for(int k=2; k<=number; k++) {
			for(int i=0; i<k; i++)
				//第k次掷骰子时，和最小为k，小于k的情况下是不可能发生的，所以设置为0
				probabilities[1-flag][i] = 0;
			for(int i=k; i<=g_maxValue*k; i++) {
				//初始化，因为这个数组要重复使用，上一次的值也清为0
				probabilities[1-flag][i] = 0;
				for(int j=0; j<=i&&j<=g_maxValue; j++)
					probabilities[1-flag][i] += probabilities[flag][i-j];
			}
			flag = 1-flag;
		}
		double total = Math.pow(g_maxValue, number);
		for(int i=number; i<=g_maxValue*number; i++) {
			double ratio = (double) probabilities[flag][i]/total;
			System.out.println(i);
			System.out.println(ratio);
		}
	}
```
### 面试题61
> #### 题目：扑克牌中的顺子
> #### 思路：先排序，然后计算空缺的数值，看是否小于等于0的数量
```java
	public boolean IsContinuous(int[] numbers) {
		if(numbers==null || numbers.length<=0)
			return false;
		int count_zero = 0;
		int dif = 0;
		Arrays.sort(numbers);
		
		for(int i=0; i<numbers.length-1; i++) {
			if(numbers[i] == 0) {
				count_zero++;
				continue;
			}
			if(numbers[i] != numbers[i+1])
				dif += numbers[i+1] - numbers[i] - 1;
			else {
				return false;
			}
		}
		//一副扑克牌只有两张大小王
		if(count_zero >= 2)
			return false;
		if(dif <= count_zero)
			return true;
		return false;
	}
```
### 面试题62
> #### 题目：圆圈中最后剩下的数字
> #### 思路：用链表模拟圆圈
```java
	public int LastRemaining(int n, int m) {
		if(n<1 || m<1)
			return -1;
		LinkedList<Integer> list = new LinkedList<Integer>();
		int bt = 0;
		for(int i=0; i<n; i++)
			list.add(i);
		while(list.size()>1) {
			bt = (bt+m+1) % list.size();
			list.remove(bt);
		}
		return list.size()==1?list.get(0):-1;
	}
```
### 面试题63
> #### 题目：股票的最大利润
> #### 思路：遍历数组的第i个数字时，就记录好前面i-1个数的最小值
```java
	public int MaxDiff(int[] number) {
		int length = number.length;
		if(number==null || length<=0)
			return 0;
		int min = number[0];
		int maxDiff = number[1] - min;
		for(int i=2; i<length; i++) {
			if(number[i-1] < min)
				min = number[i-1];
			int currentDiff = number[i] - min;
			if(currentDiff > maxDiff)
				maxDiff = currentDiff;
		}
		return maxDiff;
	}
```
### 面试题64
> #### 题目：求1+2+...+n
> #### 思路：递归
```java
	public static int SumN(int n) {
		int sum = n;
		boolean result = (n>0) && ((sum+=SumN(n-1))>0);
		return sum;
	}
```
### 面试题65
> #### 题目：不用加减乘除做加法
> #### 思路：位运算
```java
	public static int Add(int num1, int num2) {
		while(num2 != 0) {
			int temp = num1 ^ num2;
			num2 = (num1 & num2) << 1;
			num1 = temp;
		}
		return num1;
	}
```
### 面试题66
> #### 题目：构建乘积数组
> #### 思路：矩阵求解
```java
    public static int[] multiply(int[] array) {
        if (array == null) {
            return null;
        }
        int len = array.length;
        if (len == 0) {
            return null;
        }
        int[] left = new int[len];
        int[] right = new int[len];
        int[] result = new int[len];
        // 数组B中第一个数字没有左边部分，所以左边乘积数组第一个数字是1
        left[0] = 1;
        // 计算B[i]对应的在A中的左边部分的乘积，数组A从前向后计算
        for (int i = 1; i < len; i++) {
            // 因为要B[i]不需要计算A[i]，所以左边部分的乘积计算其实需要的是A中对应下标i的上一个下标及之前的数字
            left[i] = left[i - 1] * array[i - 1];
        }
        // 数组B中最后一个数字没有右边部分，所以右边乘积数组的最后一个数字是1
        right[len - 1] = 1;
        // 计算B[i]对应的在A中的右边部分的乘积，数组A从后向前计算，这样才可以一次遍历完
        // 因为计算可以用到上一次的结果，即上一次的结果*本次下标的值
        for (int i = len - 1; i > 0 ; i--) {
            // 因为要B[i]不需要计算A[i]，所以右边部分的乘积计算其实需要的是A中对应下标i的下一个下标及之后的数字
            right[i - 1] = right[i] * array[i];
        }
        for (int i = 0; i < len; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }

```
