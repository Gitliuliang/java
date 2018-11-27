package sort;

import java.util.Arrays;

/**
 * 冒充排序
 */
public class BubbleSortTest {

    public static void main(String[] args) {
        int[] array = {132, 2, 543, 643, 6432, 32, 5, 11, 53, 121, 5, 13, 52, 141, 52, 141, 64, 12, 64, 121};
        array = bubble(array);
        for (int i : array) {
            System.out.println(i);
        }
    }

    public static int[] bubble(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        for (int i = 1; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int min = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = min;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return arr;
    }
}
