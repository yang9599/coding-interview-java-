
public class UglyNumber {
	public static boolean IsUgly(int number) {
		while(number % 2 == 0)
			number /= 2;
		while(number % 3 == 0)
			number /= 3;
		while(number % 5 == 0)
			number /= 5;
		if(number == 1)
			return true;
		else {
			return false;
		}
	}
	//逐个判断每个整数是不是丑数的解法
	public static int GetUglyNumber1(int index) {
		if(index <= 0)
			return 0;
		int number = 1;
		int uglyFound = 1;
		while(uglyFound < index) {
			number++;
			if(IsUgly(number))
				uglyFound++;
		}
		return number;
	}
	//下一个丑数应该是之前的丑数乘以2、3、5的结果
	public static int GetUglyNumber2(int index) {
		if(index <= 0)
			return 0;
		int[] pUglyNumbers = new int[index];
		pUglyNumbers[0] = 1;
		int multiply2 = 0;
		int multiply3 = 0;
		int multiply5 = 0;
		
		for(int i=1; i<index; i++) {
			int min = Math.min(pUglyNumbers[multiply2]*2, 
					Math.min(pUglyNumbers[multiply3]*3,pUglyNumbers[multiply5]*5));
			pUglyNumbers[i] = min;
			if(pUglyNumbers[multiply2]*2 == min)
				multiply2++;
			if(pUglyNumbers[multiply3]*3 == min)
				multiply3++;
			if(pUglyNumbers[multiply5]*5 == min)
				multiply5++;
		}
		return pUglyNumbers[index-1];
	}
	
	public static void main(String[] args) {
		System.out.println(GetUglyNumber1(1));
		System.out.println(GetUglyNumber1(1500));
		System.out.println(GetUglyNumber2(1));
		System.out.println(GetUglyNumber2(1500));
	}

}
