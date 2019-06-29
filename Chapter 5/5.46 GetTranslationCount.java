
public class NumberToString {
	public int GetTranslationCount(int number) {
		if(number <= 0)
			return -9999;
		return GetTranslationCount(String.valueOf(number));
	}
	public int GetTranslationCount(String number) {
		int length = number.length();
		int[] countRecords = new int[length];
		countRecords[length-1] = 1;
		int count;
		for(int i=length-2;i>=0;i--) {
			count = countRecords[i+1];
			int digit1 = number.charAt(i)-'0';
			int digit2 = number.charAt(i+1)-'0';
			int connectedNumber = digit1*10+digit2;
			if(connectedNumber>=10 && connectedNumber <=25) {
				if(i < length-2) {
					count += countRecords[i+2];
				}
				else if(i == length-2){
					count += 1;
				}
			}
			countRecords[i] = count;
		}
		count = countRecords[0];
		return count;
	}

}
