import java.util.Arrays;

public class CardSequence {
	public boolean IsContinuous(int[] numbers) {
		if(numbers==null || numbers.length<=0)
			return false;
		int count_zero = 0;
		int dif = 0;
		Arrays.sort(numbers);
		
		for(int i=0; i<numbers.length-1; i++) {
			if(numbers[i] == 0) {
				count_zero++;
				continue;
			}
			if(numbers[i] != numbers[i+1])
				dif += numbers[i+1] - numbers[i] - 1;
			else {
				return false;
			}
		}
		//一副扑克牌只有两张大小王
		if(count_zero >= 2)
			return false;
		if(dif <= count_zero)
			return true;
		return false;
	}

}
