import java.util.Arrays;
import java.util.Comparator;

public class MinNumber {
	public String PrintMinNumber(int[] numbers) {
		if(numbers==null || numbers.length==0)
			return "";
		int len = numbers.length;
		String[] str = new String[len];
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<len; i++)
			str[i] = String.valueOf(numbers[i]);
		Arrays.sort(str, new Comparator<String>() {
			public int compare(String str1, String str2) {
				String c1 = str1 + str2;
				String c2 = str2 + str1;
				return c1.compareTo(c2);
			}
		});
		for(int i=0; i<len; i++)
			sb.append(str[i]);
		return sb.toString();
	}

}
