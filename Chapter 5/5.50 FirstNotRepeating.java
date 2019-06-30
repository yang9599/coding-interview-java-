import java.util.LinkedHashMap;

public class NotRepeating {
	//字符串中第一个只出现一次的字符
	public int FirstNotRepeatingChar(String str) {
		if(str==null || str.length()==0)
			return -1;
		char[] c = str.toCharArray();
		LinkedHashMap<Character, Integer> hash = new LinkedHashMap<Character, Integer>();
		
		for(char item:c) {
			if(hash.containsKey(item))
				hash.put(item , hash.get(item)+1);
			else {
				hash.put(item, 1);
			}
		}
		for(int i=0; i<str.length(); i++) {
			if(hash.get(str.charAt(i))==1)
				return i;
		}
		return -1;
		
	}
	//字符流中第一个只出现一次的字符
	char[] chars = new char[256];
	StringBuilder sb = new StringBuilder();
	public void Insert(char ch) {
		sb.append(ch);
		chars[ch]++;
	}
	public char FirstAppearingOnce() {
		char[] str = sb.toString().toCharArray();
		for(char c:str) {
			if(chars[c] == 1)
				return c;
		}
		return '#';
	}

}
