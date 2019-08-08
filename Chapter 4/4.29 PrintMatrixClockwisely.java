import java.util.ArrayList;

public class PrintMatrix {
	public static ArrayList<Integer> printMatrixClockwisely(int[][] number){
		ArrayList<Integer> list = new ArrayList<>();
		if(number == null)
			return list;
		int start = 0;
		int rows = number.length;
		if(row == 0)
        		return list;
        	int col = matrix[0].length;
		if(col == 0)
			return list;
		while(cols > start*2 && rows > start*2) {
			printMatrixInCircle(number, start, list);
			start++;
		}
		return list;
	}
	
	public static void printMatrixInCircle(int[][] number, int start, ArrayList<Integer> list) {
		int endX = number[0].length - 1 - start;
		int endY = number.length - 1 - start;
		//从左往右打印一行
		for(int i=start; i<=endX; i++)
			list.add(number[start][i]);
		//从上往下打印一列
		if(start < endY) {
			for(int j=start+1; j<=endY; j++)
				list.add(number[j][endX]);
		}
		//从右往左打印一行
		if(start < endX && start < endY) {
			for(int m=endX-1; m>=start; m--)
				list.add(number[endY][m]);
		}
		//从下往上打印
		if(start < endX && start < endY) {
			for(int n=endY-1; n>=start+1; n--)
				list.add(number[n][start]);
		}
	}
	public static void main(String[] args) {
		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		ArrayList<Integer> result = printMatrixClockwisely(matrix);
		for(int i=0; i<result.size(); i++)
			System.out.print(result.get(i)+" ");
	}

}
