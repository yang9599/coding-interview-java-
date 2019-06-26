import java.util.Arrays;

public class BST {
	public static boolean VerifySequenceOfBST(int[] sequence) {
		int len = sequence.length;
		if(sequence == null || len <= 0)
			return false;
		int root = sequence[len-1];
		int  rstart = 0;
		
		for(int i=0; i<len-1; i++) {
			if(sequence[i] < root)
				rstart++;
		}
		
		if(rstart == 0) {
			VerifySequenceOfBST(Arrays.copyOfRange(sequence, 0, len-1));
		}
		else {
			for(int i=rstart; i<len-1; i++) {
				if(sequence[i] <= root)
					return false;
			}
			VerifySequenceOfBST(Arrays.copyOfRange(sequence, 0, rstart));
			VerifySequenceOfBST(Arrays.copyOfRange(sequence, rstart, len-1));
		}
		return true;
	}
	public static void main(String[] args) {
    // int[] sequence = {5,7,6,9,11,10,8};
		int[] sequence = {7,4,6,5};
		boolean verify = VerifySequenceOfBST(sequence);
		System.out.println(verify);
	}

}
