public class Power {
	
	public boolean equal(double a, double b) {
		if(a - b <0.0000001 && a - b > -0.000001)
			return(true);
		return(false);
	}
	
	public double multiply(double base, int exponent) {
		double sum = 1;
		for(int i=0; i<exponent;i++) {
			sum *= base;
		}
		return(sum);
	}
	
	public double powerInvalid(double base, int exponent) {
		double res = 0;
		if(equal(base, 0.0)) {
			return(0);
		}
		
		if(exponent == 0)
			return(1);
		
		if(exponent > 0) {
			res = multiply(base, exponent);
		}
		else {
			res = multiply(1/base, exponent);
		}
		return(res);
	}

}
