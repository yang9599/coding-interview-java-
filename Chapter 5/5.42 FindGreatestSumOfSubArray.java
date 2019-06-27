
public class SumOfSubArray {
	public int FindGreatestSumOfSubArray(int[] pData) {
		if(pData==null || pData.length<=0)
			return -9999;
		int cur = pData[0];
		int greate = pData[0];
		for(int i=1; i<pData.length; i++) {
			if(cur < 0)
				cur = pData[i];
			else {
				cur += pData[i];
			}
			if(cur > greate)
				greate = cur;
		}
		return greate;
		
	}

}
