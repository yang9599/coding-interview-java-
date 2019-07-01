### 面试题16
> #### 题目：数值的整数次方
> #### 思路：利用下述公式，使用递归求解

![2-16](/pictures/2-16.png)

```java
	public static boolean equal(double a, double b) {
		if(a - b <0.0000001 && a - b > -0.000001)
			return(true);
		return(false);
	}
	
	public static double multiply(double base, int exponent) {
		if(exponent == 0)
			return 1;
		if(exponent == 1)
			return base;
		
		double result = multiply(base, exponent >> 1);
		result *= result;
		if((exponent & 0x1) == 1)
			result *= base;
		return result;
	}
	
	public static double powerInvalid(double base, int exponent) {
		double res = 0;
		if(equal(base, 0.0)) {
			return 0;
		}
		
		if(exponent == 0)
			return 1;
		
		if(exponent > 0) {
			res = multiply(base, exponent);
		}
		else {
			res = multiply(1/base, Math.abs(exponent));
		}
		return res;
	}
```
### 面试题17
> #### 题目：打印从1到最大的n位数
> #### 思路：使用字符串来存储最大的n位数，防止这个n位数过大而导致的long long类型溢出
```java
	public static void toMaxOfNDigits(int n) {
		if(n<=0)
			return;
		int[] number = new int[n];
		printArray(number, 0);
	}
	
	private static void printArray(int[] array, int n) {
		for(int i=0; i<10; i++) {
			if(n != array.length) {
				array[n] = i;
				printArray(array, n+1);
			}
			else {
				boolean isFirstNo0 = false;
				for(int j=0; j<array.length; j++) {
					if(array[j] != 0) {
						System.out.print(array[j]);
						if(!isFirstNo0)
							isFirstNo0 = true;
					}
					else {
						if(isFirstNo0)
							System.out.print(array[j]);
					}
				}
				System.out.println();
				return;
			}
		}
	}
```
### 面试题18
> #### 题目：删除链表的节点
> #### 思路：把要删除节点的下一个节点的内容复制到需要删除的节点上覆盖原有的内容，再把下一个节点删除，如下图所示

![2-18](/pictures/2-18.png)

```java
	//在O(1)时间内删除链表节点
	public void deletNode(ListNode head, ListNode deleted) {
		if(head == null || deleted==null)
			return;
		
		//如果链表中只有一个节点，删除后，头节点设置为null
		if(head == deleted)
			head = null;
		else {
			//如果删除的节点是尾节点，从头节点开始遍历
			if(deleted.next == null) {
				ListNode pointListNode = head;
				while(pointListNode.next.next != null) {
					pointListNode = pointListNode.next;
				}
				pointListNode.next = null;
			}
			else {
				deleted.val = deleted.next.val;
				deleted.next = deleted.next.next;
			}
		}
	}
	
	//删除链表中的重复节点
	public ListNode deleteDuplication(ListNode pHead) {
		if(pHead == null )
			return null;
		
		//新建一个节点，防止头节点被删除
		ListNode first = new ListNode(-1);
		first.next = pHead;
		ListNode p = pHead;
		ListNode preNode = first;
		
		while(p != null && p.next != null) {
			if(p.val == p.next.val) {
				int val = p.val;
				while(p != null && p.val == val)
					p = p.next;
				preNode.next = p;
			}
			else {
				preNode = p;
				p = p.next;
			}
		}
		return first.next;
	}
```
### 面试题19
> #### 题目：正则表达式匹配
> #### 思路：由于‘.’可以表示任意一个字符，比较好匹配，直接考虑‘.’后的字符。但是对于‘*’的问题要复杂一些，一种选择是直接后移两位，相当于直接忽略了‘*’和它前面的字符。如果需要匹配的字符串和原字符串的第一个字符相匹配，则在原字符串上向后移动一个字符，而在需要匹配的字符串上有两种选择：可以往后移动两个字符，也可以保持不变。
```java
	public boolean match(char[] str, char[] pattern) {
		if(str == null || pattern == null)
			return false;
		//若字符串长度为1
		if(str.length == 1) {
			if(pattern.length == 1) {
				if(str[0] == pattern[0] || pattern[0] == '.')
					return true;
				return false;
			}
		}
		
		int sindex = 0;
		int pindex = 0;
		return matchIndex(str, sindex, pattern, pindex);
	}
	
	public boolean matchIndex(char[] str, int sindex, char[] pattern, int pindex) {
		 if(sindex == str.length && pindex == pattern.length)
			 return true;
		 if(sindex != str.length && pindex == pattern.length)
			 return false;
		 //若pattern的第二个字符是'*'
		 if(pindex+1 < pattern.length && pattern[pindex+1] == '*') {
			 if(sindex != str.length && pattern[pindex] == str[pindex] || 
					 sindex != str.length && pattern[pindex] == '.')
				 return matchIndex(str, sindex+1, pattern, pindex+2) || 
						 matchIndex(str, sindex, pattern, pindex+2) ||
						 matchIndex(str, sindex+1, pattern, pindex); 
			 else
				return matchIndex(str, sindex, pattern, pindex+2);
			 
		 }
		 //若pattern的第二个字符不是'*'
		 if(sindex != str.length && pattern[pindex] == str[sindex] || 
				 sindex != str.length && pattern[pindex] == '.')
			 return matchIndex(str, sindex+1, pattern, pindex+1);
		 return false;
	}
```
