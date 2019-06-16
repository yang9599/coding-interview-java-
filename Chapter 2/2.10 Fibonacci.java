//时间复杂度为O(n)的算法
public class Fibonacci {
	public static long calculate_Fibonacci(int n) {
		int[] result = {0, 1};
		if (n < 2)
			return(result[n]);
		long fibNMinusOne = 1;
		long fibNMinusTwo = 0;
		long fibN = 0;
		for(int i=2; i<=n; i++) {
			fibN = fibNMinusOne + fibNMinusTwo;
			fibNMinusTwo = fibNMinusOne;
			fibNMinusOne = fibN;
		}
		return(fibN);
	}
	public static void main(String[] args) {
		System.out.println(calculate_Fibonacci(20));
	}
}


//时间复杂度为O(logn)的算法
/*
  [F(n+1)   F(n)]    [1  1 ]^(n)  (n次方,可以使用归纳法证明)
  |             |  = |     |                     
  [F(n)   F(n-1)]    [1  0 ]
 */
 
import java.util.Scanner;

public class Fibonacci {
	private static final int[][] UNIT = {{1, 1}, {1, 0}};
	private static final int[][] ZERO = {{0, 0}, {0, 0}};
	private static Scanner scan;
	
	public static int[][] matrixMultiple(int[][] m, int[][] n) {
		//矩阵m的列数不等于矩阵n的行数，两个矩阵无法相乘
		if(m[0].length != n.length)
			return(null);
		int row = m.length;
		int column = n[0].length;
		int[][] r = new int[row][column];
		for(int i=0; i<row;i++) {
			for(int j=0; j<column; j++) {
				r[i][j] = 0;
				for(int k=0; k<m[i].length; k++) {
					r[i][j] += m[i][k] * n[k][j];
				}
			}
		}
		return(r);
	}
	
	public static int[][] calculate_Fibonaci(int n){
		if(n == 0)
			return(ZERO);
		if(n == 1)
			return(UNIT);
		if(n % 2 == 0) {
			 int[][] matrix = calculate_Fibonaci(n >> 1);
			 return(matrixMultiple(matrix, matrix));
			}
		int[][] matrix = calculate_Fibonaci((n-1) >> 1);
		return(matrixMultiple(matrixMultiple(matrix, matrix), UNIT));

	}
	
	public static void main(String[] args) {
		System.out.println("请输入一个整数：");
		Scanner scan = new Scanner(System.in);
		while(true) {
			int n = scan.nextInt();
			if(n==0)
				System.out.println(0);
			else if(n==1)
				System.out.println(1);
			else {
				int[][] matrix = calculate_Fibonaci(n);
				System.out.println(matrix[0][1]);
			}
		}
		scan.close();
	}
}
