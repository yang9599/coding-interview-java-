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
> #### 题目描述：在一个长度为n的数组里的所有数字都在0到n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
> #### 思路：利用Set集合无序不可重复的特性进行元素过滤
```java
	public static Object[] duplicate(Object[] arr) {
		Set<Object> set = new HashSet<Object>();
		for(int i=0;i<arr.length;i++) {
			set.add(arr[i]);
		}
		return set.toArray();
	}
```
### 面试题4
> #### 题目：二维数组中的查找
> #### 题目描述：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
> #### 思路：首先选取数组中右上角的数字。如果该数字等于要查找的数字，则查找过程结束；如果该数字大于要查找的数字，则剔除这个数字所在的列；如果该数字小于要查找的数字，则剔除这个数字所在行

![2.4](/pictures/2-4.png)

```java
	public static boolean find(int[][] array, int number) {
		boolean f = false;
		if(array==null)
			return f;
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
		return f;
	}
```
### 面试题5
> #### 题目：替换空格
> #### 题目描述：请实现一个函数，把字符串中的每个空格替换成“%20”。
> #### 思路：利用StringBuilder对String字符串进行修改
```java
	public static boolean ReplaceSpace(StringBuffer str) {
		if(str==null)
			return false;
		
		StringBuffer str_re = new StringBuffer();
		int i = 0;
		for(i=0;i<str.length();i++) {
			if(String.valueOf(str.charAt(i)).equals(" "))
				str_re.append("%20");
			else
				str_re.append(str.charAt(i));
		}
		return true;
	}
```
### 面试题6
> #### 题目：从尾到头打印链表
> #### 题目描述：输入一个链表的头节点，从尾到头反过来打印出每个节点的值。
> #### 思路：利用栈先进后出的结构特点
```java
	public static ArrayList<Integer> printList(ListNode node){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(node==null)
			return list;
		Stack<ListNode> stack = new Stack<ListNode>();
		while(node != null) {
			stack.push(node);
			node = node.next;
		}
		while(!stack.isEmpty()) {
			list.add(stack.pop().val);
		}
		return list;
	}
```
### 面试题7
> #### 题目：重建二叉树
> #### 题目描述：输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
> #### 思路：前序遍历和中序遍历序列中确定左、右子树的子序列。前序遍历序列中，第一个数字是根节点的值。中序遍历序列中，根节点左边是左子树的值，右边是右子树的值。
```java
	public static TreeNode construct(int[] preorder, int[] inorder) {
		if(preorder==null || inorder==null)
			return null;
		if(preorder.length==0 || inorder.length==0)
			return null;
		if(preorder.length != inorder.length)
			return null;
		TreeNode root = new TreeNode(preorder[0]);
		for(int i=0; i<preorder.length; i++) {
			if(preorder[0] == inorder[i]) {
				root.left = construct(Arrays.copyOfRange(preorder, 1, i+1), 
						Arrays.copyOfRange(inorder, 0, i));
				root.right = construct(Arrays.copyOfRange(preorder, i+1, preorder.length),
						Arrays.copyOfRange(inorder, i+1, inorder.length));
			}
		}
		return root;
	}
```

> #### 二叉树遍历

```java

	//按层打印二叉树
	public static void Print(TreeNode pRoot){
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		depth(pRoot, 1, list);
		System.out.println list;
	}
	
	//用递归，二叉树遍历
	private static void depth(TreeNode root, int depth, ArrayList<ArrayList<Integer>> list) {
		if(root==null)
			return;
		if(depth > list.size()) //空节点
			list.add(new ArrayList<Integer>());
		list.get(depth-1).add(root.val);
		
		depth(root.left, depth+1, list);
		depth(root.right, depth+1, list);
	}
```
### 面试题8
> #### 题目：二叉树的下一个节点
> #### 题目描述：给定一棵二叉树和其中一个节点，如何找出中序遍历序列的下一个节点？树中的节点除了有两个分别指向左、右子节点的指针，还有一个指向父节点的指针。
> #### 思路：前序遍历和中序遍历序列中确定左、右子树的子序列。（树中除了有左右子节点的指针，还有一个指向父节点的指针）
```java
	public static class Node{
		int val;
		Node left;
		Node right;
		Node parent;
		public Node() {
			
		}
		public Node(int val, Node parent) {
			this.val = val;
			this.parent = parent;
		}
	}
	public static Node getNext(Node pNode) {
		if(pNode==null)
			return null;
		Node pNext = null;
		//如果一个节点有右子树，那么它的下一个节点就是它的右子树的最左子节点。
		if(pNode.right != null) {
			Node pRight = pNode.right;
			while(pRight.left != null)
				pRight = pRight.left;
			pNext = pRight;
		}
		//如果节点没有右节点：1.如果它是父节点的左子树，那么下一个节点就是父节点；
		//2.如果它是父节点的右子树，那么只能沿着指向父节点的指针一直向上遍历，直到找到一个是它父节点的左子节点的节点。
		//如果这样的节点存在的话，那么这个节点的父节点就是我们要找的下一个节点。
		else if(pNode.parent != null){
			Node pCurrent = pNode;
			Node pParent = pNode.parent;
			while(pParent!=null && pCurrent==pParent.right) {
				pCurrent = pParent;
				pParent = pParent.parent;
			}
			pNext = pParent;
		}
		return pNext;
	}
```
### 面试题9
> #### 题目：用两个栈实现队列
> #### 题目描述：用两个栈实现一个队列。队列的声明如下，请实现它的两个函数appendTail和deleteHead，分别完成在队列尾部插入节点和在队列头部删除节点的功能。
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
		return stack2.pop();
	}
```
### 面试题10
> #### 题目：斐波那契数列
> #### 题目描述：写一个函数，输入n，求斐波那契数列的第n项。
> #### 思路1：普通递归
```java
	int Fibonacci(int n){
		if(n==0)
			return 0;
		if(n==1)
			return 1;
		int fib1 = 1;
		int fib2 = 0;
		int fib = 0;
		for(int i=2; i<n; i++){
			fib = fib1+fib2;
			fib2 = fib1;
			fib1 = fib;
		}
		return fib;
	}
```
> #### 思路2：用数学归纳法总结公式
```java
	private static final int[][] UNIT = {{1, 1}, {1, 0}};
	private static final int[][] ZERO = {{0, 0}, {0, 0}};
	private static Scanner scan;
	
	public static int[][] matrixMultiple(int[][] m, int[][] n) {
		//矩阵m的列数不等于矩阵n的行数，两个矩阵无法相乘
		if(m[0].length != n.length)
			return null;
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
		return r;
	}
	
	public static int[][] calculate_Fibonaci(int n){
		if(n == 0)
			return ZERO;
		if(n == 1)
			return UNIT;
		if(n % 2 == 0) {
			 int[][] matrix = calculate_Fibonaci(n >> 1);
			 return(matrixMultiple(matrix, matrix));
			}
		int[][] matrix = calculate_Fibonaci((n-1) >> 1);
		return matrixMultiple(matrixMultiple(matrix, matrix), UNIT);

	}
```
### 面试题11
> #### 题目：旋转数组的最小数字（无论旋转数组中是否存在重复数字，都适用）
> #### 题目描述：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
> #### 思路：前后两个指针同时遍历
```java
	public static int FindMin(int[] arr) {
		if(arr==null)
			return -1;
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
				return MinInOrder(arr, index1, index2);
			}
			//中间指针的数字如果大于第一个指针指向的数字，那么中间指针的数字一定在第一个递增子数组中
			if(arr[indexMid] >= arr[index1])
				index1 = indexMid;
			//中间指针的数字如果小于第二个指针指向的数字，那么中间指针的数字一定在第二个递增子数组中
			else if(arr[indexMid] <= arr[index2])
				index2 = indexMid;
		}
		return arr[indexMid];
	}
	public static int MinInOrder(int[] arr, int index1, int index2) {
		int min = arr[index1];
		for(int i=index1+1;i<=index2;i++) {
			if(min > arr[i])
				min = arr[i];
		}
		return min;
	}
```
### 面试题12
> #### 题目：矩阵中的路径
> #### 题目描述：请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再进入该格子。
> #### 思路：回溯法。
```java
	public static boolean findPath(char[][] matrix, char[] str) {
		if(matrix==null || str==null)
			return false;
		//标识路径是否已经进入了每个格子
		boolean[] visited = new boolean[matrix.length * matrix[0].length];
		for(int i=0; i<visited.length; i++)
			visited[i] = false;
		int pathLength = 0; //定位字符串中的下标
		for(int row=0; row<matrix.length; row++) {
			for(int col=0; col<matrix[0].length; col++) {
				if(hasPathCore(matrix, row, col, str, pathLength, visited))
					return true;
			}
		}
		return false;
	}
	
	public static boolean hasPathCore(char[][] matrix, int row, int col, 
			char[] str, int pathLength, boolean[] visited) {
		
		if(str.length == pathLength) {
			return true;
		
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
		return hasPath;
	}
```
### 面试题13
> #### 题目：机器人的运动范围
> #### 题目描述：地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
> #### 思路：回溯法
```java
	int movingCount(int threshold, int rows, int cols) {
		if(threshold<0 || rows <= 0 || cols <= 0)
			return -1;
		boolean[] visited = new boolean[rows*cols];
		for(int i=0; i<rows*cols; ++i) {
			visited[i] = false;
		}
		int count = movingCountCore(threshold, rows, cols, 0, 0, visited);
		return count;
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
		return count;
	}
	
	boolean check(int threshold, int rows, int cols, 
			int row, int col, boolean[] visited) {
		if(row>=0 && row<rows && col>=0 && col<cols && getDigitSum(row)+getDigitSum(col)<=threshold &&
				!visited[row*cols+col]) {
			return true;
		}
		return false;
	}
	int getDigitSum(int number) {
		int sum = 0;
		while(number>0) {
			sum += number % 10;
			number /= 10;
		}
		return sum;
	}
```
### 面试题14
> #### 题目：剪绳子
> #### 题目描述：给你一根长度为n的绳子，请把绳子剪成m段 (m和n都是整数，n>1并且m>1)每段绳子的长度记为k[0],k[1],…,k[m].请问k[0]k[1]…k[m]可能的最大乘积是多少？例如，当绳子的长度为8时，我们把它剪成长度分别为2,3,3的三段，此时得到的最大乘积是18
> #### 思路：1.动态规划；2.贪婪算法
```java
	//动态规划
	public static int Cutting1(int length) {
		if(length < 2)
			return 0;
		if(length == 2)
			return 1;
		if(length == 3)
			return 2;
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
		return max;
	}
	
	//贪婪算法
	//当length大于等于5时，尽可能多地剪长度为3的绳子；当剩下的绳子长度为4时，把绳子剪成两段长度为2的绳子。
	public static int Cutting2(int length) {
		if(length < 2)
			return 0;
		if(length == 2)
			return 1;
		if(length == 3)
			return 2;
		
		int timesOf3 = length / 3;
		if(length - timesOf3 * 3 == 1)
			timesOf3 -= 1;
		int timesOf2 = (length - timesOf3 * 3) / 2;
		return (int)(Math.pow(3, timesOf3)*(int)(Math.pow(2, timesOf2)));
	}
```
### 面试题15
> #### 题目：二进制中1的个数
> #### 题目描述：请实现一个函数，输入一个整数，输出该数二进制表示中1的个数。
> #### 思路：把一个整数减去1，再和原整数做与运算，会把该整数最右边的1变成0。那么一个整数的二进制表示中有多少个1，就可以进行多少次这样的操作。
```java
	public static int method2(int n) {
		if(n == 0)
			return 0;
		int count = 0;
		while(n != 0) {
			n = n & (n-1);
			count++;
		}
		return count;
	}
```
