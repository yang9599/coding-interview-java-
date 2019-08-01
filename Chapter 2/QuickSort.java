import java.util.Scanner;

// 使用递归实现快速排序
public class QuickSort {
	private static int Partition(int[] arr, int start, int end) {
		int key = arr[start];
		while(start < end) {
			while(arr[end] >= key && end > start)
				end--;
			arr[start] = arr[end];
			while(arr[start] <= key && end > start)
				start++;
			arr[end] = arr[start];
		}
		arr[start] = key;
		return start;
	}
	public static int[] quickSort(int[] arr, int start, int end) {
		if(start < end) {
			int index = Partition(arr, start, end);
			quickSort(arr, start, index-1);
			quickSort(arr, index+1, end);
		}
		return arr;
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入数组总量：");
		int m = scan.nextInt();
		int[] arr = new int[m];
		System.out.println("请输入数组数字：");
		for(int i=0; i<m; i++)
			arr[i] = scan.nextInt();
		scan.close();
		int[] arr_sort = quickSort(arr, 0, m-1);
		System.out.println("排序后结果为：");
		for(int j=0; j<m; j++)
			System.out.print(arr_sort[j]+" ");
	}
}
