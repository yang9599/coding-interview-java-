
public class MultiplyMatrix {
    public static int[] multiply(int[] array) {
        if (array == null) {
            return null;
        }
        int len = array.length;
        if (len == 0) {
            return null;
        }
        int[] left = new int[len];
        int[] right = new int[len];
        int[] result = new int[len];
        // 数组B中第一个数字没有左边部分，所以左边乘积数组第一个数字是1
        left[0] = 1;
        // 计算B[i]对应的在A中的左边部分的乘积，数组A从前向后计算
        for (int i = 1; i < len; i++) {
            // 因为要B[i]不需要计算A[i]，所以左边部分的乘积计算其实需要的是A中对应下标i的上一个下标及之前的数字
            left[i] = left[i - 1] * array[i - 1];
        }
        // 数组B中最后一个数字没有右边部分，所以右边乘积数组的最后一个数字是1
        right[len - 1] = 1;
        // 计算B[i]对应的在A中的右边部分的乘积，数组A从后向前计算，这样才可以一次遍历完
        // 因为计算可以用到上一次的结果，即上一次的结果*本次下标的值
        for (int i = len - 1; i > 0 ; i--) {
            // 因为要B[i]不需要计算A[i]，所以右边部分的乘积计算其实需要的是A中对应下标i的下一个下标及之后的数字
            right[i - 1] = right[i] * array[i];
        }
        for (int i = 0; i < len; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        int[] result = multiply(array);
        for (Integer i : result) {
            System.out.print(i + " ");
        }
    }

}
