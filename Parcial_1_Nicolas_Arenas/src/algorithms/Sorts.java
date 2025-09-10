import java.util.Comparator;

public class Sorts {

    //Insertion Sort (stable).
    public static void insertionSort(Student[] a, Comparator<Student> cmp) {
        for (int i = 1; i < a.length; i++) {
            Student key = a[i];
            int j = i - 1;
            while (j >= 0 && cmp.compare(a[j], key) > 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    //Bubble Sort (stable).
    public static void bubbleSort(Student[] a, Comparator<Student> cmp) {
        boolean swapped;
        for (int pass = 0; pass < a.length - 1; pass++) {
            swapped = false;
            for (int i = 0; i < a.length - 1 - pass; i++) {
                if (cmp.compare(a[i], a[i + 1]) > 0) {
                    Student tmp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break; // already sorted
        }
    }

    //Selection Sort (NOT stable).
    public static void selectionSort(Student[] a, Comparator<Student> cmp) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < a.length; j++) {
                if (cmp.compare(a[j], a[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                Student tmp = a[i];
                a[i] = a[minIdx];
                a[minIdx] = tmp;
            }
        }
    }
}
