
public class DicePoints {
	public void PrintProbability(int number) {
		if(number < 1)
			return;
		int g_maxValue = 6;
		int[][] probabilities = new int[2][];
		probabilities[0] = new int[g_maxValue*number+1];
		probabilities[1] = new int[g_maxValue*number+1];
		int flag = 0;
		
		//当第一次掷骰子时，有6种可能，每种可能出现一次
		for(int i=1; i<=g_maxValue; i++) {
			probabilities[0][i] = 1;
		}
		//当第二次掷骰子时，假设第一个数组中的第n个数字表示骰子和为n出现的次数
		for(int k=2; k<=number; k++) {
			for(int i=0; i<k; i++)
				//第k次掷骰子时，和最小为k，小于k的情况下是不可能发生的，所以设置为0
				probabilities[1-flag][i] = 0;
			for(int i=k; i<=g_maxValue*k; i++) {
				//初始化，因为这个数组要重复使用，上一次的值也清为0
				probabilities[1-flag][i] = 0;
				for(int j=0; j<=i&&j<=g_maxValue; j++)
					probabilities[1-flag][i] += probabilities[flag][i-j];
			}
			flag = 1-flag;
		}
		double total = Math.pow(g_maxValue, number);
		for(int i=number; i<=g_maxValue*number; i++) {
			double ratio = (double) probabilities[flag][i]/total;
			System.out.println(i);
			System.out.println(ratio);
		}
	}

}
