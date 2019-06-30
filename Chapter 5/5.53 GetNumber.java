import java.util.Arrays;

public class GetNumber {
	//数字在排序数组中出现的次数
	public int GetNumberOfK(int[] data, int k) {
		int result = 0;
		int mid = data.length/2;
		if(data==null || data.length==0)
			return 0;
		if(data.length == 1) {
			if(data[0] == k)
				return 1;
			else {
				return 0;
			}
		}
		if(k < data[mid]) {
			result += GetNumberOfK(Arrays.copyOfRange(data, 0, mid), k);
		}
		else if(k > data[mid])
			result += GetNumberOfK(Arrays.copyOfRange(data, mid, data.length), k);
		else {
			for(int i=mid; i<data.length; i++) {
				if(data[i] == k)
					result++;
				else {
					break;
				}
			}
			for(int i=mid-1; i>=0; i--) {
				if(data[i] == k)
					result++;
				else {
					break;
				}
			}
		}
		return result;
	}
	//0~n-1中缺失的数字
	public int GetMissingNumber(int[] numbers) {
		int length = numbers.length;
		if(numbers==null || length==0)
			return -1;
		int left = 0;
		int right = length-1;
		while(left <= right) {
			int middle = (left+right) >> 1;
			if(numbers[middle] != middle) {
				if(middle==0 || numbers[middle-1]==middle-1)
					return middle;
				right = middle-1;
			}
			else {
				left = middle+1;
			}
		}
		if(left == length)
			return length;
		//无效的输入
		return -1;
	}

}
