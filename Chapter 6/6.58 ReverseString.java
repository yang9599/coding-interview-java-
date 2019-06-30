
public class ReverseString {
	//翻转单词顺序
	public static String Reverse(String str) {
		StringBuilder sb = new StringBuilder();
		for(int i=str.length()-1; i>=0; i--)
			sb.append(str.charAt(i));
		return String.valueOf(sb);
	}
	public static String ReverseSentence(String str) {
		if(str==null || str.length()==0)
			return str;
		if(str.trim().length() == 0)
			return str;
		StringBuilder sb = new StringBuilder();
		String re = Reverse(str);
		String[] s = re.split(" ");
		for(int i=0; i<s.length-1;i++)
			sb.append(Reverse(s[i])+" ");
		sb.append(Reverse(s[s.length-1]));
		return String.valueOf(sb);
	}
	//左旋转字符串
	public static String leftRotateString(String str, int n) {
		StringBuilder sb = new StringBuilder();
		for(int i=n; i<str.length(); i++) {
			sb.append(str.charAt(i));
		}
		for(int i=0; i<n; i++)
			sb.append(str.charAt(i));
		return String.valueOf(sb);
	}
	public static void main(String[] args) {
		String str1 = "I am a student.";
		String str2 = "abcdefg";
		System.out.println(ReverseSentence(str1));
		System.out.println(leftRotateString(str2, 2));
	}

}
