import java.util.*;

public class Power {
	
	public static boolean equal(double a, double b) {
		if(a - b <0.0000001 && a - b > -0.000001)
			return(true);
		return(false);
	}
	
	public static double multiply(double base, int exponent) {
		if(exponent == 0)
			return 1;
		if(exponent == 1)
			return base;
		
		double result = multiply(base, exponent >> 1);
		result *= result;
		if((exponent & 0x1) == 1)
			result *= base;
		return result;
	}
	
	public static double powerInvalid(double base, int exponent) {
		double res = 0;
		if(equal(base, 0.0)) {
			return 0;
		}
		
		if(exponent == 0)
			return 1;
		
		if(exponent > 0) {
			res = multiply(base, exponent);
		}
		else {
			res = multiply(1/base, Math.abs(exponent));
		}
		return res;
	}
	public static void main(String[] arg) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入底数：");
		double base = scan.nextDouble();
		System.out.println("请输入指数：");
		int exponent = scan.nextInt();
		scan.close();
		String result = String.format("%.4f", powerInvalid(base, exponent));
		System.out.println(result);
	}

}
