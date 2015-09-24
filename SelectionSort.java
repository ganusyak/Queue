/**
 * Created by yuriyganusyak on 9/23/15.
 */
public class SelectionSort {
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

    public static Comparable[] sort(Comparable[] array) {
        int compares = 0;
        int exchanges = 0;
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                compares++;
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            exchanges++;
            swap(array, i, minIndex);
            printArray(array);
        }
        System.out.println("Compares: " + compares);
        System.out.println("Exchanges: " + exchanges);
        return array;
    }
}
