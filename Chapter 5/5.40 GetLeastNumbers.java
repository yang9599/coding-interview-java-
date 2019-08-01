import java.util.*;

public class FindKthMin {
	// 创建大根堆的方法，时间复杂度为nlogk，适合处理海量数据
	public ArrayList<Integer> GetLeastNumbers1(int[] input, int k) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(input == null || k<=0 || k>input.length)
			return null;
		
		int[] kArray = Arrays.copyOfRange(input, 0, k);
		//创建大根堆
		buildHeap(kArray);
		for(int i=k; i<input.length; i++) {
			if(input[i] < kArray[0]) {
				kArray[0] = input[i];
				maxHeap(kArray, 0);
			}
		}
		for(int i=kArray.length-1; i>=0; i--) {
			list.add(kArray[i]);
		}
		return list;
	}
	public void buildHeap(int[] input) {
		for(int i=input.length/2-1; i>=0; i--)
			maxHeap(input, i);
	}
	public void maxHeap(int[] input, int i) {
		int left = 2*i+1;
		int right = left+1;
		int max = 0;
		if(left < input.length && input[left] > input[i])
			max = left;
		else {
			max = i;
		}
		if(right < input.length && input[right] > input[max])
			max = right;
		if(max != i) {
			int temp = input[i];
			input[i] = input[max];
			input[max] = temp;
			maxHeap(input, max);
		}
	}
	//使用Partition方法，时间复杂度为n
	private static int Partition(int[] arr, int start, int end) {
		int key = arr[start];
		while(start < end) {
			while(arr[end] >= key && end > start)
				end--;
			arr[start] = arr[end];
			while(arr[start] <= key && end > start)
				start++;
			arr[end] = arr[start];
		}
		arr[start] = key;
		return start;
	}	
	public static void GetLeastNumbers2(int[] input, int k) {
		int len = input.length;
		if(input==null || k>len || len<=0 || k<=0)
			return;
		int start = 0;
		int end = len-1;
		int index = Partition(input, start, end);
		while(index != k-1) {
			if(index > k-1) {
				end = index-1;
				index = Partition(input, start, end);
			}
			else {
				start = index+1;
				index = Partition(input, start, end);
			}
		}
	}
	public static void main(String[] args) {
		int[] arr = {2,8,4,11,6,10,5,18,3,1};
		GetLeastNumbers2(arr, 5);
		for(int i=0; i<5; i++) {
			System.out.println(arr[i]);
		}
	}

}
