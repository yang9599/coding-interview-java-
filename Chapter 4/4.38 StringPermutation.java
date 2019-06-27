import java.util.ArrayList;
import java.util.TreeSet;

public class StringPermutation {
	public static ArrayList<String> Permutation(char[] str) {
		ArrayList<String> result = new ArrayList<String>();
		if(str==null)
			return result;
		TreeSet<String> temp = new TreeSet<String>();
		Permutation(str,0,temp);
		result.addAll(temp);
		return result;
		
	}
	public static void Permutation(char[] str, int index, TreeSet<String> result) {
		if(str==null || str.length==0)
			return;
		if(index<0 || index>str.length-1)
			return;
		if(index == str.length-1)
			result.add(String.valueOf(str));
		else {
			for(int i=index; i<=str.length-1; i++) {
				swap(str, index, i);
				Permutation(str, index+1, result);
				//回退
				swap(str, index, i);
			}
		}
	}
	public static void swap(char[] c, int a, int b) {
		char temp = c[a];
		c[a] = c[b];
		c[b] = temp;
	}
	public static void main(String[] args) {
		char[] str = {'a', 'b', 'c', 'd'};
		ArrayList<String> result = Permutation(str);
		System.out.println(result.size());
		for(int i=0; i<result.size(); i++)
			System.out.println(result.get(i));
	}

}
