import java.util.*;

public class FindKthMin {
	public ArrayList<Integer> GetLeastNumbers(int[] input, int k) {
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

}
