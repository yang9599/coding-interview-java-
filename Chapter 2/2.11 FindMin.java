// 利用二分法寻找旋转数组中的最小值
public class Min {
	public static int FindMin(int[] arr) {
		if(arr==null)
			return(-1);
		int index1 = 0;
		int index2 = arr.length-1;
		int indexMid = index1;
		while(arr[index1] >= arr[index2]) {
			if(index2 - index1 == 1) {
				indexMid = index2;
				break;
			}
			indexMid = (index1 + index2) / 2;
			/*如果下标为index1、index2和indexMid指向的三个数字相等
			 *则只能按照顺序查找
			 */
			if(arr[index1] == arr[index2]&&arr[indexMid]==arr[index1]) {
				return(MinInOrder(arr, index1, index2));
			}
			if(arr[indexMid] >= arr[index1])
				index1 = indexMid;
			else if(arr[indexMid] <= arr[index2])
				index2 = indexMid;
		}
		return(arr[indexMid]);
	}
	public static int MinInOrder(int[] arr, int index1, int index2) {
		int min = arr[index1];
		for(int i=index1+1;i<=index2;i++) {
			if(min > arr[i])
				min = arr[i];
		}
		return(min);
	}
	
	public static void main(String[] args) {
		int[] arr = {1,0,1,1,1};
		int min = FindMin(arr);
		if(min==-1)
			System.out.println("数组为空！");
		else
			System.out.println("数组中最小的数为："+min);
		
	}
