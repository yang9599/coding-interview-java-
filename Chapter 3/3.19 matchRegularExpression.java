public class matchRegularExpression {
	public boolean match(char[] str, char[] pattern) {
		if(str == null || pattern == null)
			return false;
		//若字符串长度为1
		if(str.length == 1) {
			if(pattern.length == 1) {
				if(str[0] == pattern[0] || pattern[0] == '.')
					return true;
				return false;
			}
		}
		
		int sindex = 0;
		int pindex = 0;
		return matchIndex(str, sindex, pattern, pindex);
	}
	
	public boolean matchIndex(char[] str, int sindex, char[] pattern, int pindex) {
		 if(sindex == str.length && pindex == pattern.length)
			 return true;
		 if(sindex != str.length && pindex == pattern.length)
			 return false;
		 //若pattern的第二个字符是'*'
		 if(pindex+1 < pattern.length && pattern[pindex+1] == '*') {
			 if(sindex != str.length && pattern[pindex] == str[pindex] || 
					 sindex != str.length && pattern[pindex] == '.')
				 return matchIndex(str, sindex+1, pattern, pindex+2) || 
						 matchIndex(str, sindex, pattern, pindex+2) ||
						 matchIndex(str, sindex+1, pattern, pindex); 
			 else
				return matchIndex(str, sindex, pattern, pindex+2);
			 
		 }
		 //若pattern的第二个字符不是'*'
		 if(sindex != str.length && pattern[pindex] == str[sindex] || 
				 sindex != str.length && pattern[pindex] == '.')
			 return matchIndex(str, sindex+1, pattern, pindex+1);
		 return false;
	}
}
