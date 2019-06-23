### 面试题27
> #### 题目：请完成一个函数，输入一颗二叉树，该函数输出它的镜像。
> #### 思路：先前序遍历这棵树的每个节点，如果遍历到的节点有子节点，就交换它的两个子节点。当交换完所有非叶结点的左、右子节点之后，就得到了树的镜像。

树结构类（下面通用，不再重复）

```java
	public class BinaryTreeNode{
		double m_dbValue;
		BinaryTreeNode m_pLeft;
		BinaryTreeNode m_pRight;
		public BinaryTreeNode() {
			
		}
		public BinaryTreeNode(double m_dbValue) {
			this.m_dbValue = m_dbValue;
		}
		public BinaryTreeNode(double m_dbValue, BinaryTreeNode m_pLeft) {
			this.m_dbValue = m_dbValue;
			this.m_pLeft = m_pLeft;
		}
		public BinaryTreeNode(double m_dbValue, BinaryTreeNode m_pLeft, BinaryTreeNode m_pRight) {
			this.m_dbValue = m_dbValue;
			this.m_pLeft = m_pLeft;
			this.m_pRight = m_pRight;
		}
	}
```

正文

```java
	public void mirror(BinaryTreeNode pNode) {
		if(pNode == null)
			return;
		if(pNode.m_pLeft == null && pNode.m_pRight == null)
			return;
		
		BinaryTreeNode pTemp = pNode.m_pLeft;
		pNode.m_pLeft = pNode.m_pRight;
		pNode.m_pRight = pTemp;
		
		if(pNode.m_pLeft != null)
			mirror(pNode.m_pLeft);
		if(pNode.m_pRight != null)
			mirror(pNode.m_pRight);
	}
```

### 面试题28
> #### 题目：请实现一个函数，用来判断一棵二叉树是不是对称的
> #### 思路：定义一种遍历算法，先遍历右子节点再遍历左子节点。如果这种遍历结果与前序遍历结果一致，即可判定这棵树是对称的二叉树。
````java
	public boolean symmetrical(BinaryTreeNode pRoot) {
		return symmetrical(pRoot, pRoot);
	}
	public boolean symmetrical(BinaryTreeNode pRoot1, BinaryTreeNode pRoot2) {
		if(pRoot1 == null && pRoot2 == null)
			return true;
		if(pRoot1 == null || pRoot2 == null)
			return false;
		if(pRoot1.m_dbValue != pRoot2.m_dbValue)
			return false;
		return symmetrical(pRoot1.m_pLeft, pRoot2.m_pRight) && symmetrical(pRoot1.m_pRight, pRoot2.m_pLeft);
		
	}
````

### 面试题29
> #### 题目：顺时针打印矩阵
> #### 思路：终止行号大于起始行号，终止列号大于起始列号
```java
	public ArrayList<Integer> printMatrixClockwisely(int[][] number){
		ArrayList<Integer> list = new ArrayList<>();
		if(number == null)
			return list;
		int start = 0;
		int rows = number.length;
		int cols = number[0].length;
		while(cols > start*2 && rows > start*2) {
			printMatrixInCircle(number, start, list);
			start++;
		}
		return list;
	}
	
	public void printMatrixInCircle(int[][] number, int start, ArrayList<Integer> list) {
		int endX = number[0].length - 1 - start;
		int endY = number.length - 1 - start;
		//从左往右打印一行
		for(int i=start; i<=endX; i++)
			list.add(number[start][i]);
		//从上往下打印一列
		if(start < endY) {
			for(int j=start+1; j<=endY; j++)
				list.add(number[j][endX]);
		}
		//从右往左打印一行
		if(start < endX && start < endY) {
			for(int m=endX-1; m>=start; m--)
				list.add(number[endY][m]);
		}
		//从下往上打印
		if(start < endX && start < endY) {
			for(int n=endY-1; n>=start+1; n--)
				list.add(number[n][start]);
		}
	}
```

### 面试题30
> #### 题目：包含min函数的栈
> #### 思路：定义两个栈，一个数据栈存放入的值。另一个辅助栈存最小值。
```java
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
```
### 面试题31
> #### 题目：从上到下打印二叉树
> #### 思路：利用队列和链表辅助，存储一些节点
```java
	public ArrayList<Integer> PrintFromTopToBottom(BinaryTreeNode root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(root == null)
			return list;
		LinkedList<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			BinaryTreeNode node = queue.poll();
			list.add(node.m_dbValue);
			if(node.m_pLeft != null) {
				queue.addLast(node.m_pLeft);
			}
			if(node.m_pRight != null)
				queue.addLast(node.m_pRight);
		}
		return list;
	}
```
