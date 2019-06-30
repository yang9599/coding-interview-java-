
public class SumS {
	//和为s的两个数字
	public static boolean FindNumbersWithSum(int[] data, int sum, int[] num1, int[] num2) {
		int length = data.length;
		if(data==null || length<=0 || sum<data[0])
			return false;
		int end = length-1;
		int start = 0;
		while(start <= end) {
			if(data[start]+data[end] > sum)
				end--;
			else if(data[start]+data[end] < sum)
				start++;
			else {
				num1[0] = data[start];
				num2[0] = data[end];
				return true;
			}
		}
		return false;
	}
	//和为s的连续证书序列
	public static void FindContinuousSequence(int sum) {
		if(sum < 3)
			return;
		int small = 1;
		int big = 2;
		int middle = (1+sum)/2;
		int curSum = small + big;
		while(small < middle) {
			if(curSum == sum)
				PrintContinuousSequence(sum, small, big);
			while(curSum>sum && small<middle) {
				curSum -= small;
				small++;
				if(curSum == sum)
					PrintContinuousSequence(sum, small, big);
			}
			big++;
			curSum += big;
		}
	}
	public static void PrintContinuousSequence(Integer sum, int small, int big) {
		for(int i=small; i<big; i++) {
			System.out.print(String.valueOf(i)+"+");
		}
		System.out.print(String.valueOf(big)+"="+String.valueOf(sum));
		System.out.println();
	}
	
	
	
	public static void main(String[] args) {
		int[] data = {1,2,2,3,4,8,9,10,11,15};
		int[] num1 = new int[1];
		int[] num2 = new int[1];
		int sum = 27;
		boolean found = FindNumbersWithSum(data, sum, num1, num2);
		System.out.println(found);
		if(found)
			System.out.println(String.valueOf(num1[0])+"+"+String.valueOf(num2[0])
			+"="+String.valueOf(sum));
		FindContinuousSequence(15);
	}

}
