// 数组中的重复数字
// 与书上方法不同，更简单一些
// 利用Set集合无序不可重复的特性进行元素过滤
// 比较最初的数组和返回后的数组里面元素个数是否相等，若相等，说明原数组中没有重复数字

import java.util.*;

public class array_duplicate {
	public static Object[] duplicate(Object[] arr) {
		Set<Object> set = new HashSet<Object>();
		for(int i=0;i<arr.length;i++) {
			set.add(arr[i]);
		}
		return(set.toArray());
	}
	public static void main(String[] args) {
		Object[] arr = {2,5,1,3,4};
		Object[] arr_duplicate = duplicate(arr);
		if(arr.length == arr_duplicate.length)
			System.out.println("true");
		else
			System.out.println("false");
	}
}
