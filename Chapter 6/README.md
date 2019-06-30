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
