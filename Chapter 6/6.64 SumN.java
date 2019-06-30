public class Sum {
	public static int SumN(int n) {
		int sum = n;
		boolean result = (n>0) && ((sum+=SumN(n-1))>0);
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(SumN(10));
	}

}
