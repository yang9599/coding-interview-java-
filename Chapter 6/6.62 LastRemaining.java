import java.util.LinkedList;

public class CircleNumber {
	public int LastRemaining(int n, int m) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		int bt = 0;
		for(int i=0; i<n; i++)
			list.add(i);
		while(list.size()>1) {
			bt = (bt+m-1) % list.size();
			list.remove(bt);
		}
		return list.size()==1?list.get(0):-1;
	}

}
