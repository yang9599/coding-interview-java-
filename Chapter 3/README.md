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
			 if(sindex != str.length && pattern[pindex] == str[sindex] || 
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
### 面试题20
> #### 题目：表示数值的字符串
> #### 思路：数字的格式可以用A[.[B]][e|EC]或者.B[e|EC]表示，其中A和C都是整数（可以用正负号，也可以没有），而B是一个无符号的整数
```java
	public static boolean isNumeric(char[] str) {
		if(str == null)
			return false;
		int index = 0;
		int ecount = 0;
		int point = 0;
		//第一个字符是正负号就直接跳过
		if(str[0]=='-' || str[0]=='+')
			index++;
		for(int i=index; i<str.length; i++) {
			//遍历的字符是正负号，那么如果前面一个字符不是e或者E，那么就不是数值
			if(str[i]=='-' || str[i]=='+') {
				if(str[i-1]!='e' && str[i-1]!='E')
					return false;
				continue;
			}
			if(str[i]=='e' || str[i]=='E') {
				ecount++;
				if(ecount>1)
					return false;
				//阿拉伯数字的0~9对应的ASCII码是从48到57
				if(i==0 || str[i-1]<48 || str[i-1]>57 || i==str.length-1)
					return false;
				point++;
				continue;
			}
			if(str[i]=='.') {
				point++;
				if(point > 1)
					return false;
				continue;
			}
			if((str[i]<48 || str[i]>57) && (str[i]!='e') && (str[i]!='E'))
				return false;
		}
		return true;
	}
```
### 面试题21
> #### 题目：调整数组顺序使奇数位于偶数前面
> #### 思路：前后两个指针，前指针遇到的偶数和后指针遇到的奇数直接调换位置
```java
	public static void reorder(int[] data) {
		if(data == null)
			return;
		int first = 0;
		int last = data.length-1;
		while(first < last) {
			while(first<last && (data[first]&0x1)!=0)
				first++;
			while(first<last && (data[last]&0x1)==0)
				last--;
			if(first<last) {
				int temp = data[first];
				data[first] = data[last];
				data[last] = temp;
			}
		}
		
	}
```
### 面试题22
> #### 题目：链表中倒数第k个节点
> #### 思路：在头节点定义两个指针，第一个指针从先走k-1步后，两个指针一起走，这样两个指针相差k-1，当第一个指针到达尾节点时，第二个指针正好指向倒数第k个节点
```java
	public ListNode find(ListNode head, int k) {
		if(head == null || k <= 0)
			return null;
		ListNode fast = head;
		ListNode slow = head;
		while(k-- > 1) {
			if(fast.next != null)
				fast = fast.next;
			else {
				return null;
			}
		}
		while(fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}
		return slow;
	}
```
### 面试题23
> #### 题目：链表中环的入口
> #### 思路：1.先确定链表中是否有环。定义两个指针，一个走的快，每次走两步，另一个走的慢，一次走一步，如果两个指针能够相遇，那么链表中有环，没有相遇，则没有环；2.确定环中节点数量，两个指针相遇一定是在环中，所以相遇后再走一次，就可以得到环中节点数量；3.如果环中节点数量为k，先让第一个指针从头开始从走k步后，第二个指针从头和第一个指针一起走，相遇的节点就是环的入口
```java
	public ListNode meeting(ListNode pHead) {
		if(pHead == null)
			return null;
		ListNode pSlow = pHead.next;
		if(pSlow == null)
			return null;
		ListNode pFast = pSlow.next;
		while(pFast != null && pSlow != null) {
			if(pFast == pSlow)
				return pFast;
			pSlow = pSlow.next;
			pFast = pFast.next;
			if(pFast != null)
				pFast = pFast.next;
		}
		return null;
	}
	public ListNode loop(ListNode pHead) {
		ListNode meetingNode = meeting(pHead);
		if(meetingNode == null)
			return null;
		//得到环中节点的数量
		int nodesInLoop = 1;
		ListNode pNode1 = meetingNode;
		while(pNode1.next != meetingNode) {
			pNode1 = pNode1.next;
			nodesInLoop++;
		}
		//先移动pNode1，次数为环中节点的数目
		pNode1 = pHead;
		for(int i=0; i<nodesInLoop; i++) {
			pNode1 = pNode1.next;
		}
		ListNode pNode2 = pHead;
		while(pNode1 != pNode2) {
			pNode1 = pNode1.next;
			pNode2 = pNode2.next;
		}
		return pNode1;
	}
```
### 面试题24
> #### 题目：翻转链表
> #### 思路：遍历到的节点的next指针，指向前一个节点，为了防止改变next后无法找到下一个节点，所以先提前存储好下一个节点的信息，在转换next指针
```java
	public ListNode reverse(ListNode pHead) {
		if(pHead == null)
			return null;
		ListNode pReverseHead = null;
		ListNode pNode = pHead;
		ListNode pPrev = null;
		while(pNode != null) {
			ListNode pNext = pNode.next;
			if(pNext == null) 
				pReverseHead = pNode;
			pNode.next = pPrev;
			pPrev = pNode;
			pNode = pNext;
		}
		return pReverseHead;
	}
```
### 面试题25
> #### 题目：合并两个排序的链表
> #### 思路：如下图所示，两个链表，各定义一个指针遍历，比较两个指针当前位置的数字大小，小的放入新的链表中

![3-25](/pictures/3-25.png)

```java
	public ListNode merge(ListNode pHead1, ListNode pHead2) {
		if(pHead1 == null)
			return pHead2;
		if(pHead2 == null)
			return pHead1;
		
		ListNode mergeNode = null;
		if(pHead1.val < pHead2.val) {
			mergeNode = pHead1;
			mergeNode.next = merge(pHead1.next, pHead2);
		}
		else {
			mergeNode = pHead2;
			mergeNode.next = merge(pHead1, pHead2.next);
		}
		return mergeNode;
	}
```
### 面试题26
> #### 题目：树的子结构
> #### 思路：1.在树A中找到和树B的根节点的值一样的节点R；2.判断树A中以R为根节点的子树是不是包含和树B一样的结构
```java
	public static boolean Equal(double num1, double num2) {
		if(Math.abs(num1-num2)<0.0000001)
			return true;
		else {
			return false;
		}
	}
	
	public static boolean DoesTree1HaveTree2(BinaryTreeNode pRoot1, BinaryTreeNode pRoot2) {
		//约定空树是任意一个树的子结构
		if(pRoot2 == null) 
			return true;
		if(pRoot1 == null)
			return false;
		if(!Equal(pRoot1.m_dbValue, pRoot2.m_dbValue))
			return false;
		return(DoesTree1HaveTree2(pRoot1.m_pLeft, pRoot2.m_pLeft) && DoesTree1HaveTree2(pRoot1.m_pRight, pRoot2.m_pRight));
	}
	
	public static boolean HasSubtree(BinaryTreeNode pRoot1, BinaryTreeNode pRoot2) {
		boolean result = false;
		if(pRoot1 != null && pRoot2 != null) {
			if(Equal(pRoot1.m_dbValue, pRoot2.m_dbValue))
				result = DoesTree1HaveTree2(pRoot1, pRoot2);
			if(!result)
				result = HasSubtree(pRoot1.m_pLeft, pRoot2);
			if(!result)
				result = HasSubtree(pRoot1.m_pRight, pRoot2);
		}
		return result;
	}
```
