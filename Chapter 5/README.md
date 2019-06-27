### 面试题39
> #### 题目：数组中出现次数超过一半的数字
> #### 思路：遍历数组，下一个数字与保存的数字相同则次数加一，否则减一。当最后次数为1时，则就是我们要找的结果
```java
	public int MoreThanHalfNum(int[] numbers) {
		if(numbers == null)
			return -9999;
		int result = numbers[0];
		int times = 1;
		for(int i=1; i<numbers.length; i++) {
			if(times == 0) {
				result = numbers[i];
				times = 1;
			}
			else if(numbers[i] == result)
				times++;
			else {
				times--;
			}
		}
		//验证出现次数最多的数是不是出现次数超过数组长度的一半
		int sum = 0;
		for(int j=0; j<numbers.length; j++) {
			if(numbers[j] == result)
				sum++;
		}
		if(sum*2 > numbers.length)
			return result;
		else 
			return -9999;
	}
```
