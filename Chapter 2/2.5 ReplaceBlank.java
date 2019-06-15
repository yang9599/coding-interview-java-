public class ReplaceBlank {

	public static boolean ReplaceSpace(StringBuffer str) {
		if(str==null)
			return(false);
		
		StringBuffer str_re = new StringBuffer();
		int i = 0;
		for(i=0;i<str.length();i++) {
			if(String.valueOf(str.charAt(i)).equals(" "))
				str_re.append("%20");
			else
				str_re.append(str.charAt(i));
		}
		System.out.println(str_re);
		return(true);
	}
	public static void main(String[] args) {
		StringBuffer str = new StringBuffer("We are happy!");
		boolean b = ReplaceSpace(str);
		System.out.println(b);
	}

}
