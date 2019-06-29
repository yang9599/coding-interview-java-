
public class Gift {
	public int getMaxValue(int[][] values) {
		if(values == null)
			return 0;
		int rows = values.length;
		int cols = values[0].length;
		if(rows<=0 || cols<=0)
			return 0;
		int[][] maxValues = new int[rows][cols];
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				int left = 0;
				int up = 0;
				if(i > 0)
					up = maxValues[i-1][j];
				if(j > 0)
					left = maxValues[i][j-1];
				maxValues[i][j] = Math.max(left, up) + values[i][j];
			}
		}
		return maxValues[rows-1][cols-1];
	}

}
