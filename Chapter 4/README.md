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
> #### 题目：栈的压入、弹出序列
> #### 思路：用栈来压入弹出元素，相等则出栈。
```java
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
```

### 面试题32
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
### 面试题33
> #### 题目：二叉搜索树的后序遍历序列
> #### 思路：区分开左右子树，然后分别进行左右子树递归处理
```java
	public boolean VerifySequenceOfBST(int[] sequence) {
		int len = sequence.length;
		if(sequence == null || len <= 0)
			return false;
		int root = sequence[len-1];
		int  rstart = 0;
		
		for(int i=0; i<len-1; i++) {
			if(sequence[i] < root)
				rstart++;
		}
		
		if(rstart == 0) {
			VerifySequenceOfBST(Arrays.copyOfRange(sequence, 0, len-1));
		}
		else {
			for(int i=rstart; i<len-1; i++) {
				if(sequence[i] <= root)
					return false;
			}
			VerifySequenceOfBST(Arrays.copyOfRange(sequence, 0, rstart));
			VerifySequenceOfBST(Arrays.copyOfRange(sequence, rstart, len-1));
		}
		return true;
	}
```
### 面试题34
> #### 题目：二叉树中和为某一值的路径
> #### 思路：根据路径的定义，选择树的先序遍历作为流程框架。动态保存根节点到当前节点的path。若当前节点为叶子节点，则判断路径和是否为给定的整数值。直到树的遍历结束。
```java
	private ArrayList<ArrayList<Integer>> listAll = new ArrayList<ArrayList<Integer>>();
	private ArrayList<Integer> list = new ArrayList<Integer>();
	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
		if(root == null)
			return listAll;
		list.add(root.val);
		target -= root.val;
		if(target==0 && root.left==null && root.right==null)
			listAll.add(new ArrayList<Integer>(list));
		FindPath(root.left, target);
		FindPath(root.right, target);
		list.remove(list.size()-1);
		return listAll;
		
	}
```
### 面试题35
> #### 题目：复杂链表的复制
> #### 思路：1.根据原始链表的每个节点N创建对应的N’；2.设置复制出来的节点的m_pSibling；3.把这个长链表拆分成两个链表。
```java
	public class ComplexListNode{
		int m_nValue;
		ComplexListNode m_pNext;
		ComplexListNode m_pSibling;
		public ComplexListNode() {
			// TODO Auto-generated constructor stub
		}
		public ComplexListNode(int m_nValue, ComplexListNode m_pNext, ComplexListNode m_pSibling) {
			this.m_nValue = m_nValue;
			this.m_pNext = m_pNext;
			this.m_pSibling = m_pSibling;
		}
	}
	//根据原始链表的每个节点N创建对应的N’
	public void CloneNodes(ComplexListNode pHead) {
		ComplexListNode pNode = pHead;
		while(pNode != null) {
			ComplexListNode pCloned = new ComplexListNode();
			pCloned.m_nValue = pNode.m_nValue;
			pCloned.m_pNext = pNode.m_pNext;
			pCloned.m_pSibling = null;
			
			pNode.m_pNext = pCloned;
			pNode = pCloned.m_pNext;
		}
	}
	//设置复制出来的节点的m_pSibling
	public void ConnectSiblingNodes(ComplexListNode pHead) {
		ComplexListNode pNode = pHead;
		while(pNode != pHead) {
			ComplexListNode pCloned = pNode.m_pNext;
			if(pNode.m_pSibling != null) {
				pCloned.m_pSibling = pNode.m_pSibling.m_pNext;
			}
			pNode = pCloned.m_pNext;
		}
	}
	//把这个长链表拆分成两个链表
	public ComplexListNode ReconnectNodes(ComplexListNode pHead) {
		ComplexListNode pNode = pHead;
		ComplexListNode pClonedHead = null;
		ComplexListNode pClonedNode = null;
		if(pNode != pHead) {
			pClonedHead = pClonedNode = pNode.m_pNext;
			pNode.m_pNext = pClonedNode.m_pNext;
			pNode = pNode.m_pNext;
		}
		while(pNode != null) {
			pClonedNode.m_pNext = pNode.m_pNext;
			pClonedNode = pClonedNode.m_pNext;
			pNode.m_pNext = pClonedNode.m_pNext;
			pNode = pNode.m_pNext;
		}
		return pClonedHead;
	}
	//上面三步合并
	public ComplexListNode Clone(ComplexListNode pHead) {
		CloneNodes(pHead);
		ConnectSiblingNodes(pHead);
		return ReconnectNodes(pHead);
	}
```
### 面试题36
> #### 题目：二叉搜索树与双向链表
> #### 思路：定义一个链表的尾节点，递归处理左右子树，最后返回链表的头节点
```java
	public BinaryTreeNode Convert(BinaryTreeNode pRootOfTree) {
		BinaryTreeNode lastlist = ConvertNode(pRootOfTree, null);
		BinaryTreeNode pHead = lastlist;
		while(pHead!=null && pHead.m_pLeft!=null)
			pHead = pHead.m_pLeft;
		return pHead;
	}
	public BinaryTreeNode ConvertNode(BinaryTreeNode root, BinaryTreeNode lastlist) {
		if(root == null)
			return null;
		BinaryTreeNode cur = root;
		if(cur.m_pLeft != null) {
			lastlist = ConvertNode(cur.m_pLeft, lastlist);
		}
		cur.m_pLeft = lastlist;
		if(lastlist != null)
			lastlist.m_pRight = cur;
		lastlist = cur;
		if(cur.m_pRight != null)
			lastlist = ConvertNode(cur.m_pRight, lastlist);
		return lastlist;
	}
```
### 面试题37
> #### 题目：序列化二叉树
> #### 思路：序列化：前序遍历二叉树存入字符串中；反序列化：根据前序遍历重建二叉树。
```java
	public String Serialize(BinaryTreeNode root) {
		StringBuffer sb = new StringBuffer();
		if(root == null) {
			sb.append("$,");
			return sb.toString();
		}
		sb.append(root.m_dbValue+",");
		sb.append(Serialize(root.m_pLeft));
		sb.append(Serialize(root.m_pRight));
		return sb.toString();
	}
	public int index = -1;
	public BinaryTreeNode Deserialize(String str) {
		index++;
		int len = str.length();
		String[] s = str.split(",");
		BinaryTreeNode node = null;
		
		if(index >= len)
			return null;
		if(!s[index].equals("$")) {
			node = new BinaryTreeNode(Integer.valueOf(s[index]));
			node.m_pLeft = Deserialize(str);
			node.m_pRight = Deserialize(str);
		}
		return node;
	}
```
### 面试题38
> #### 题目：字符串的排列
> #### 思路：用递归拿第一个字符和后面的字符逐个交换
````java
	public ArrayList<String> Permutation(char[] str) {
		ArrayList<String> result = new ArrayList<String>();
		if(str==null)
			return result;
		TreeSet<String> temp = new TreeSet<String>();
		Permutation(str,0,temp);
		result.addAll(temp);
		return result;
		
	}
	public void Permutation(char[] str, int index, TreeSet<String> result) {
		if(str==null || str.length==0)
			return;
		if(index<0 || index>str.length-1)
			return;
		if(index == str.length-1)
			result.add(String.valueOf(str));
		else {
			for(int i=index; i<=str.length-1; i++) {
				swap(str, index, i);
				Permutation(str, index+1, result);
				//回退
				swap(str, index, i);
			}
		}
	}
	public void swap(char[] c, int a, int b) {
		char temp = c[a];
		c[a] = c[b];
		c[b] = temp;
	}
````
