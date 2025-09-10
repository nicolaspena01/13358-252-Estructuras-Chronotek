package Parcial_1_Dennis_Franco;

import java.util.Arrays;

// Sorting and searching algorithms
public final class Algorithms {

    private Algorithms() {}

    // Make a copy of the array (same students, new array)
    public static Student[] cloneArray(Student[] src) {
        return Arrays.copyOf(src, src.length);
    }

    // Print a list with a title
    public static void printStudents(Student[] arr, String title) {
        System.out.println("\n=== " + title + " ===");
        for (Student s : arr) {
            System.out.println(s);
        }
    }

    // ----------------- SORTS -----------------

    // Insertion Sort by age (small to big)
    // Good for small or almost-sorted arrays
    public static void insertionSortByAgeAscending(Student[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Student key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].getAge() > key.getAge()) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Selection Sort by semester (big to small)
    // Repeatedly put the max semester at the front
    public static void selectionSortBySemesterDescending(Student[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].getSemester() > arr[maxIdx].getSemester()) {
                    maxIdx = j;
                }
            }
            if (maxIdx != i) {
                Student tmp = arr[i];
                arr[i] = arr[maxIdx];
                arr[maxIdx] = tmp;
            }
        }
    }

    // Bubble Sort by stratum (small to big)
    // Swap neighbors if they are in the wrong order
    public static void bubbleSortByStratumAscending(Student[] arr) {
        boolean swapped;
        int n = arr.length;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1].getSocioEconomicStratum() > arr[i].getSocioEconomicStratum()) {
                    Student tmp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = tmp;
                    swapped = true;
                }
            }
            n--; // last item is in place
        } while (swapped);
    }

    // ---------------- SEARCHES ----------------

    // Linear Search by programId (left to right)
    // Return index or -1 if not found
    public static int linearSearchByProgramId(Student[] arr, int programId) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getProgramId() == programId) return i;
        }
        return -1;
    }

    // Binary Search by age (array must be sorted by age asc)
    // Return index or -1 if not found
    public static int binarySearchByAge(Student[] arrSortedByAge, int age) {
        int lo = 0, hi = arrSortedByAge.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int midAge = arrSortedByAge[mid].getAge();
            if (midAge == age) return mid;
            if (midAge < age) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }
}
