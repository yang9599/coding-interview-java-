//矩阵中的路径
public class hasPath {
	public static boolean findPath(char[][] matrix, char[] str) {
		if(matrix==null || str==null)
			return(false);
		boolean[] visited = new boolean[matrix.length * matrix[0].length];
		for(int i=0; i<visited.length; i++)
			visited[i] = false;
		int pathLength = 0;
		for(int row=0; row<matrix.length; row++) {
			for(int col=0; col<matrix[0].length; col++) {
				if(hasPathCore(matrix, row, col, str, pathLength, visited))
					return(true);
			}
		}
		return(false);
	}
	
	public static boolean hasPathCore(char[][] matrix, int row, int col, 
			char[] str, int pathLength, boolean[] visited) {
		
		if(str.length == pathLength) {
			return(true);
		
		}
		boolean hasPath = false;
		if(row>=0 && row< matrix.length && col>=0 && col<matrix[0].length && 
				matrix[row][col]==str[pathLength] && 
				!visited[row*matrix[0].length+col]) {
			++pathLength;
			visited[row*matrix[0].length+col] = true;
			
			hasPath = hasPathCore(matrix, row, col-1, str, pathLength, visited)||
					hasPathCore(matrix, row-1, col, str, pathLength, visited) ||
					hasPathCore(matrix, row, col+1, str, pathLength, visited) ||
					hasPathCore(matrix, row+1, col, str, pathLength, visited);
			if(!hasPath) {
				--pathLength;
				visited[row*matrix[0].length+col] = false;
			}
		}
		return(hasPath);
	}
	
	public static void main(String[] args) {
		char[][] matrix = {{'a','b','t','g'},{'c','f','c','s'},{'j','d','e','h'}};
		char[] str = {'a', 'b', 'f', 'd'};
		System.out.println(findPath(matrix, str));
		
	}

}
