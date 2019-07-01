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
> #### 思路：首先选取数组中右上角的数字。如果该数字等于要查找的数字，则查找过程结束；如果该数字大于要查找的数字，则剔除这个数字所在的列；
如果该数字小于要查找的数字，则剔除这个数字所在行
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
