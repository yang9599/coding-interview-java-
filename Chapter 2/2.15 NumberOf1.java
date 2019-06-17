
public class NumberOf1 {
	public static int method1(int n) {
		if(n==0)
			return(0);
		int count = 0;
		int flag = 1;
		while(flag != 0) {
			if((flag & n) != 0)
				count++;
			flag = flag << 1;
		}
		return(count);
	}
	
	public static int method2(int n) {
		if(n == 0)
			return(0);
		int count = 0;
		while(n != 0) {
			n = n & (n-1);
			count++;
		}
		return(count);
	}
	public static void main(String[] args) {
		System.out.println(method1(12));
		System.out.println(method2(12));
	}
}
