### 面试题2
> #### 题目：实现Singleton模式
> #### 思路：按需创建实例
```java
	private singleton4() {
		
	}
	private static class createinstance{
		private static singleton4 instance = new singleton4();
	}
	public static singleton4 getinstance() {
		return(createinstance.instance);
	}
```
### 面试题3
> #### 题目：数组中重复数字
> #### 思路：利用Set集合无序不可重复的特性进行元素过滤
```java
	public static Object[] duplicate(Object[] arr) {
		Set<Object> set = new HashSet<Object>();
		for(int i=0;i<arr.length;i++) {
			set.add(arr[i]);
		}
		return(set.toArray());
	}
```
### 面试题4
> #### 题目：二维数组中的查找
> #### 思路：首先选取数组中右上角的数字。如果该数字等于要查找的数字，则查找过程结束；如果该数字大于要查找的数字，则剔除这个数字所在的列；如果该数字小于要查找的数字，则剔除这个数字所在行

![2.4](/pictures/2-4.png)

```java
	public static boolean find(int[][] array, int number) {
		boolean f = false;
		if(array==null)
			return(f);
		int row = 0;
		int column = array[0].length-1;
		while(row<array.length && column>=0) {
			if(array[row][column]==number) {
				f = true;
				break;
			}
			if(array[row][column]>number)
				column--;
			else
				row++;
		}
		return(f);
	}
```
### 面试题5
> #### 题目：替换空格
> #### 思路：利用StringBuilder对String字符串进行修改
```java
	public static boolean ReplaceSpace(StringBuffer str) {
		if(str==null)
			return(false);
		
		StringBuffer str_re = new StringBuffer();
		int i = 0;
		for(i=0;i<str.length();i++) {
			if(String.valueOf(str.charAt(i)).equals(" "))
				str_re.append("%20");
			else
				str_re.append(str.charAt(i));
		}
		System.out.println(str_re);
		return(true);
	}
```
### 面试题6
> #### 题目：从尾到头打印链表
> #### 思路：利用栈先进后出的结构特点
```java
	public static ArrayList<Integer> printList(ListNode node){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(node==null)
			return(list);
		Stack<ListNode> stack = new Stack<ListNode>();
		while(node != null) {
			stack.push(node);
			node = node.next;
		}
		while(!stack.isEmpty()) {
			list.add(stack.pop().val);
		}
		System.out.println(list);
		return(list);
	}
```
### 面试题7
> #### 题目：重建二叉树
> #### 思路：前序遍历和中序遍历序列中确定左、右子树的子序列
```java
	public static TreeNode construct(int[] preorder, int[] inorder) {
		if(preorder==null || inorder==null)
			return(null);
		if(preorder.length==0 || inorder.length==0)
			return(null);
		if(preorder.length != inorder.length)
			return(null);
		TreeNode root = new TreeNode(preorder[0]);
		for(int i=0; i<preorder.length; i++) {
			if(preorder[0] == inorder[i]) {
				root.left = construct(Arrays.copyOfRange(preorder, 1, i+1), 
						Arrays.copyOfRange(inorder, 0, i));
				root.right = construct(Arrays.copyOfRange(preorder, i+1, preorder.length),
						Arrays.copyOfRange(inorder, i+1, inorder.length));
			}
		}
		return(root);
	}
	
	//按层打印二叉树
	public static void Print(TreeNode pRoot){
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		depth(pRoot, 1, list);
		System.out.println(list);
	}
	
	//用递归，二叉树遍历
	private static void depth(TreeNode root, int depth, ArrayList<ArrayList<Integer>> list) {
		if(root==null)
			return;
		if(depth > list.size())
			list.add(new ArrayList<Integer>());
		list.get(depth-1).add(root.val);
		
		depth(root.left, depth+1, list);
		depth(root.right, depth+1, list);
	}
```
### 面试题8
> #### 题目：二叉树的下一个节点
> #### 思路：前序遍历和中序遍历序列中确定左、右子树的子序列
```java
	public static class Node{
		int val;
		Node left;
		Node right;
		Node parent;
		public Node() {
			
		}
		public Node(int val) {
			this.val = val;
		}
		public Node(int val, Node parent) {
			this.val = val;
			this.parent = parent;
		}
	}
	public static Node getNext(Node pNode) {
		if(pNode==null)
			return(null);
		Node pNext = null;
		if(pNode.right != null) {
			Node pRight = pNode.right;
			while(pRight != null)
				pRight = pRight.left;
			pNext = pRight;
		}
		else if(pNode.parent != null){
			Node pCurrent = pNode;
			Node pParent = pNode.parent;
			while(pParent!=null && pCurrent==pParent.right) {
				pCurrent = pParent;
				pParent = pParent.parent;
			}
			pNext = pParent;
		}
		return(pNext);
	}
```
### 面试题9
> #### 题目：用两个栈实现队列
> #### 思路：如下图的思路所示

![2.9](/pictures/2-9.png)

```java
	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();
	public CQueue() {
		
	}
	public void push(int num) {
		stack1.push(num);
	}
	public int pop() throws Exception{
		if(stack1.isEmpty() && stack2.isEmpty()) {
			throw new Exception("栈为空！");
		}
		if(stack2.isEmpty()) {
			while(!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		return(stack2.pop());
	}
```
### 面试题10
> #### 题目：斐波那契数列
> #### 思路：用数学归纳法总结公式
```java
	private static final int[][] UNIT = {{1, 1}, {1, 0}};
	private static final int[][] ZERO = {{0, 0}, {0, 0}};
	private static Scanner scan;
	
	public static int[][] matrixMultiple(int[][] m, int[][] n) {
		//矩阵m的列数不等于矩阵n的行数，两个矩阵无法相乘
		if(m[0].length != n.length)
			return(null);
		int row = m.length;
		int column = n[0].length;
		int[][] r = new int[row][column];
		for(int i=0; i<row;i++) {
			for(int j=0; j<column; j++) {
				r[i][j] = 0;
				for(int k=0; k<m[i].length; k++) {
					r[i][j] += m[i][k] * n[k][j];
				}
			}
		}
		return(r);
	}
	
	public static int[][] calculate_Fibonaci(int n){
		if(n == 0)
			return(ZERO);
		if(n == 1)
			return(UNIT);
		if(n % 2 == 0) {
			 int[][] matrix = calculate_Fibonaci(n >> 1);
			 return(matrixMultiple(matrix, matrix));
			}
		int[][] matrix = calculate_Fibonaci((n-1) >> 1);
		return(matrixMultiple(matrixMultiple(matrix, matrix), UNIT));

	}
```
### 面试题11
> #### 题目：旋转数组的最小数字
> #### 思路：前后两个指针同时遍历
```java
	public static int FindMin(int[] arr) {
		if(arr==null)
			return(-1);
		int index1 = 0;
		int index2 = arr.length-1;
		int indexMid = index1;
		while(arr[index1] >= arr[index2]) {
			if(index2 - index1 == 1) {
				indexMid = index2;
				break;
			}
			indexMid = (index1 + index2) / 2;
			/*如果下标为index1、index2和indexMid指向的三个数字相等
			 *则只能按照顺序查找
			 */
			if(arr[index1] == arr[index2]&&arr[indexMid]==arr[index1]) {
				return(MinInOrder(arr, index1, index2));
			}
			if(arr[indexMid] >= arr[index1])
				index1 = indexMid;
			else if(arr[indexMid] <= arr[index2])
				index2 = indexMid;
		}
		return(arr[indexMid]);
	}
	public static int MinInOrder(int[] arr, int index1, int index2) {
		int min = arr[index1];
		for(int i=index1+1;i<=index2;i++) {
			if(min > arr[i])
				min = arr[i];
		}
		return(min);
	}
```
### 面试题12
> #### 题目：矩阵中的路径
> #### 思路：回溯法
```java
	public static boolean findPath(char[][] matrix, char[] str) {
		if(matrix==null || str==null)
			return(false);
		boolean[] visited = new boolean[matrix.length * matrix[0].length];
		for(int i=0; i<visited.length; i++)
			visited[i] = false;
		int pathLength = 0;
		for(int row=0; row<matrix.length; row++) {
			for(int col=0; col<matrix[0].length; col++) {
				if(hasPathCore(matrix, row, col, str, pathLength, visited))
					return(true);
			}
		}
		return(false);
	}
	
	public static boolean hasPathCore(char[][] matrix, int row, int col, 
			char[] str, int pathLength, boolean[] visited) {
		
		if(str.length == pathLength) {
			return(true);
		
		}
		boolean hasPath = false;
		if(row>=0 && row< matrix.length && col>=0 && col<matrix[0].length && 
				matrix[row][col]==str[pathLength] && 
				!visited[row*matrix[0].length+col]) {
			++pathLength;
			visited[row*matrix[0].length+col] = true;
			
			hasPath = hasPathCore(matrix, row, col-1, str, pathLength, visited)||
					hasPathCore(matrix, row-1, col, str, pathLength, visited) ||
					hasPathCore(matrix, row, col+1, str, pathLength, visited) ||
					hasPathCore(matrix, row+1, col, str, pathLength, visited);
			if(!hasPath) {
				--pathLength;
				visited[row*matrix[0].length+col] = false;
			}
		}
		return(hasPath);
	}
```
### 面试题13
> #### 题目：机器人的运动范围
> #### 思路：回溯法
```java
	int movingCount(int threshold, int rows, int cols) {
		if(threshold<0 || rows <= 0 || cols <= 0)
			return(-1);
		boolean[] visited = new boolean[rows*cols];
		for(int i=0; i<rows*cols; ++i) {
			visited[i] = false;
		}
		int count = movingCountCore(threshold, rows, cols, 0, 0, visited);
		return(count);
	}
	int movingCountCore(int threshold, int rows, int cols, 
			int row, int col, boolean[] visited) {
		int count = 0;
		if(check(threshold, rows, cols, row, col, visited)) {
			visited[row*cols+col] = true;
			count = 1 + movingCountCore(threshold, rows, cols, row-1, col, visited)+
					movingCountCore(threshold, rows, cols, row, col-1, visited)+
					movingCountCore(threshold, rows, cols, row+1, col, visited)+
					movingCountCore(threshold, rows, cols, row, col+1, visited);
		}
		return(count);
	}
	
	boolean check(int threshold, int rows, int cols, 
			int row, int col, boolean[] visited) {
		if(row>=0 && row<rows && col>=0 && col<cols && getDigitSum(row)+getDigitSum(col)<=threshold &&
				!visited[row*cols+col]) {
			return(true);
		}
		return(false);
	}
	int getDigitSum(int number) {
		int sum = 0;
		while(number>0) {
			sum += number % 10;
			number /= 10;
		}
		return(sum);
	}
```
### 面试题14
> #### 题目：剪绳子
> #### 思路：1.动态规划；2.贪婪算法
```java
	public static int Cutting1(int length) {
		if(length < 2)
			return(0);
		if(length == 2)
			return(1);
		if(length == 3)
			return(2);
		int[] products = new int[length+1];
		products[0] = 0;
		products[1] = 1;
		products[2] = 2;
		products[3] = 3;
		
		int max = 0;
		for(int i=4; i<=length;++i) {
			max = 0;
			for(int j=0; j<=i/2; ++j) {
				int product = products[j] * products[i-j];
				if(max<product)
					max = product;
				products[i] = max;
			}
		}
		max = products[length];
		return(max);
	}
	
	//贪婪算法
	public static int Cutting2(int length) {
		if(length < 2)
			return(0);
		if(length == 2)
			return(1);
		if(length == 3)
			return(2);
		
		int timesOf3 = length / 3;
		if(length - timesOf3 * 3 == 1)
			timesOf3 -= 1;
		int timesOf2 = (length - timesOf3 * 3) / 2;
		return((int)(Math.pow(3, timesOf3)*(int)(Math.pow(2, timesOf2))));
	}
```
### 面试题15
> #### 题目：二进制中1的个数
> #### 思路：把一个整数减去1，再和原整数做与运算，会把该整数最右边的1变成0。那么一个整数的二进制表示中有多少个1，就可以进行多少次这样的操作。
```java
	public static int method2(int n) {
		if(n == 0)
			return(0);
		int count = 0;
		while(n != 0) {
			n = n & (n-1);
			count++;
		}
		return(count);
	}
```
