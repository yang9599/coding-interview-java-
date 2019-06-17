public class Print1ToMaxOfNDigits {
	public static void toMaxOfNDigits(int n) {
		if(n<=0)
			return;
		int[] number = new int[n];
		printArray(number, 0);
	}
	
	private static void printArray(int[] array, int n) {
		for(int i=0; i<10; i++) {
			if(n != array.length) {
				array[n] = i;
				printArray(array, n+1);
			}
			else {
				boolean isFirstNo0 = false;
				for(int j=0; j<array.length; j++) {
					if(array[j] != 0) {
						System.out.print(array[j]);
						if(!isFirstNo0)
							isFirstNo0 = true;
					}
					else {
						if(isFirstNo0)
							System.out.print(array[j]);
					}
				}
				System.out.println();
				return;
			}
		}
	}
	public static void main(String[] args) {
		toMaxOfNDigits(2);
	}

}
