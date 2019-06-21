import java.util.Scanner;

public class NumericofString {
	public static boolean isNumeric(char[] str) {
		if(str == null)
			return false;
		int index = 0;
		int ecount = 0;
		int point = 0;
		//第一个字符是正负号就直接跳过
		if(str[0]=='-' || str[0]=='+')
			index++;
		for(int i=index; i<str.length; i++) {
			//遍历的字符是正负号，那么如果前面一个字符不是e或者E，那么就不是数值
			if(str[i]=='-' || str[i]=='+') {
				if(str[i-1]!='e' && str[i-1]!='E')
					return false;
				continue;
			}
			if(str[i]=='e' || str[i]=='E') {
				ecount++;
				if(ecount>1)
					return false;
				//阿拉伯数字的0~9对应的ASCII码是从48到57
				if(i==0 || str[i-1]<48 || str[i-1]>57 || i==str.length-1)
					return false;
				point++;
				continue;
			}
			if(str[i]=='.') {
				point++;
				if(point > 1)
					return false;
				continue;
			}
			if((str[i]<48 || str[i]>57) && (str[i]!='e') && (str[i]!='E'))
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
		scanner.close();
		char[] str = s.toCharArray();
		System.out.println(isNumeric(str));
	}

}
