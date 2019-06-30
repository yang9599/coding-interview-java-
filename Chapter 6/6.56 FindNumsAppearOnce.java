
public class ArrayNumber {
	//数组中只出现一次的两个数字
	public void FindNumsAppearOnce(int[] array, int[] num1, int[] num2) {
		int length = array.length;
		if(length == 2) {
			num1[0] = array[0];
			num2[0] = array[1];
			return;
		}
		int bitResult = 0;
		for(int i=0; i<length; i++)
			bitResult ^= array[i];
		int index = findFirst(bitResult);
		for(int i=0; i<length; i++) {
			if(isBit(array[i], index)) {
				num1[0] ^= array[i];
			}
			else {
				num2[0] ^= array[i];
			}
		}
	}
	public int findFirst(int bitResult) {
		int index = 0;
		while(((bitResult & 1)==0) && index < 32) {
			bitResult >>= 1;
			index++;
		}
		return index;
	}
	public boolean isBit(int target, int index) {
		return ((target >> index)&1) == 1;
	}
	//数组中唯一只出现一次的数字
	public int FindNumberAppearOnce(int[] numbers) {
		int length = numbers.length;
		if(numbers==null || length==0)
			return -9999;
		int[] bitSum = new int[32];
		for(int i=0; i<bitSum.length; i++)
			bitSum[i] = 0;
		for(int i=0; i<length; i++) {
			int bitMask = 1;
			for(int j=31; j>=0; j--) {
				int bit = numbers[i] & bitMask;
				if(bit != 0)
					bitSum[j] += 1;
				bitMask = bitMask << 1;
			}
		}
		int result = 0;
		for(int i=0; i<32; i++) {
			result = result << 1;
			result += bitSum[i] % 3;
		}
		return result;
		
	}

}
