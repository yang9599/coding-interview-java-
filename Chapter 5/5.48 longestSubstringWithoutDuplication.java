
public class LongestSubString {
	public int findLongestSubString(String string) {
		if(string==null || string.length()==0)
			return 0;
		int maxLength = 0;
		int curLength = 0;
		int[] positions = new int[26];
		for(int i=0; i<positions.length; i++) {
			positions[i] = -1;//初始化为-1，负数表示没出现过
		}
		for(int i=0; i<string.length();i++) {
			int curChar = string.charAt(i) - 'a';
			int prePosition = positions[curChar];
			//当前字符与它上次出现位置之间的距离
			int distance = i - prePosition;
			//当前字符第一次出现，或者前一个非重复子字符串中没有包含当前字符
			if(prePosition<0 || distance>curLength)
				curLength++;
			else {
				if(curLength > maxLength)
					maxLength = curLength;
				curLength = distance;
			}
			positions[curChar] = i;
		}
		if(curLength > maxLength)
			maxLength = curLength;
		return maxLength;
	}

}
