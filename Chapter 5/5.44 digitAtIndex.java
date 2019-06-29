
public class digit {
	int digitAtIndex(int index) {
		if(index < 0)
			return -9999;
		int digits = 1;
		while(true) {
			int numbers = countOfInteger(digits);
			if(index < numbers*digits)
				return digitAtIndex(index, digits);
		}
	}
	//例如输入2，则返回两位数（10~99）的个数90
	int countOfInteger(int digits) {
		if(digits == 1)
			return 10;
		int count = (int)(Math.pow(10, digits-1));
		return 9*count;
	}
	//当知道要找的那一位数字位于某m位数之中后，就可以使用如下函数找出那一位数字
	int digitAtIndex(int index, int digits) {
		int number = beginNumber(digits) + index / digits;
		int indexFromRight = digits - index % digits;
		for(int i=1; i<indexFromRight; i++) {
			number /= 10;
		}
		return number%10;
	}
	int beginNumber(int digits) {
		if(digits == 1)
			return 0;
		return (int)(Math.pow(10, digits-1));
	}

}
