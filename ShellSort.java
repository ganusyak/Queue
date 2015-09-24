/**
 * Created by yuriyganusyak on 9/23/15.
 */
public class ShellSort {
    public static void sort (Comparable[] array) {
        int N = array.length;
        int h = 1;
        while (h < N / 3) {
            h = h * 3 + 1;
        }

        while (h >= 1) {
            System.out.println(h);
            for (int i = h; i < N; i ++) {
                for (int j = i; j >= h && (array[j].compareTo(array[j-h]) < 0); j -= h) {
                    swap(array, j, j - h);
                }
                printArray(array);
            }
            h = h / 3;
        }
    }

    public static Comparable[] swap(Comparable[] array, int firstIndex, int secondIndex) {
        Comparable temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        return array;
    }

    public static void printArray(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }

}
