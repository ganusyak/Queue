/**
 * Created by yuriyganusyak on 9/23/15.
 */
public class InsertionSort {
    public static void printArray(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }

    public static Comparable[] swap(Comparable[] array, int firstIndex, int secondIndex) {
        Comparable temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        return array;
    }

    public static void sort (Comparable[] array) {
        int N = array.length;
        int compares = 0;
        int exchanges = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0;  j--) {
                compares++;
                if ((array[j].compareTo(array[j - 1]) < 0)) {
                    exchanges++;
                    swap(array, j, j-1);
                    printArray(array);
                }
            }
        }
        System.out.println("Compares: " + compares);
        System.out.println("Exchanges: " + exchanges);
    }
}
