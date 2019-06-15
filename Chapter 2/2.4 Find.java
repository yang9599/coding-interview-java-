// 二维数组中的查找
public class Find {
	public static boolean find(int[][] array, int number) {
		boolean f = false;
		if(array==null)
			return(f);
		int row = 0;
		int column = array[0].length-1;
		while(row<array.length && column>=0) {
			if(array[row][column]==number) {
				f = true;
				break;
			}
			if(array[row][column]>number)
				column--;
			else
				row++;
		}
		return(f);
	}
	public static void main(String[] args) {
		int[][] arr = {{1,2,3},{4,5,6}};
		int num = 8;
		boolean f = find(arr, num);
		System.out.println(f);
	}
}
