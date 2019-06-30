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