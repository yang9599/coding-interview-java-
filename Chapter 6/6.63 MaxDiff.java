
public class StockMaxProfit {
	public int MaxDiff(int[] number) {
		int length = number.length;
		if(number==null || length<=0)
			return 0;
		int min = number[0];
		int maxDiff = number[1] - min;
		for(int i=2; i<length; i++) {
			if(number[i-1] < min)
				min = number[i-1];
			int currentDiff = number[i] - min;
			if(currentDiff > maxDiff)
				maxDiff = currentDiff;
		}
		return maxDiff;
	}

}
