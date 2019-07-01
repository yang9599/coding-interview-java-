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

![2.4](pictures/2-4.png)

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
> #### 思路：前序遍历和中序遍历序列中确定左、右子树的子序列
```java
