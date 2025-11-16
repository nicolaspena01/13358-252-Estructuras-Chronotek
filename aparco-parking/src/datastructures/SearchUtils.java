package datastructures;

import java.util.Comparator;

public class SearchUtils {

    /** Búsqueda lineal – O(n) */
    public static <T> int linearSearch(T[] arr, T target, Comparator<T> cmp) {
        for (int i = 0; i < arr.length; i++) {
            if (cmp.compare(arr[i], target) == 0)
                return i;
        }
        return -1;
    }

    /**
     * Búsqueda binaria – O(log n). Requiere arreglo ORDENADO según el mismo
     * comparator.
     */
    public static <T> int binarySearch(T[] arr, T target, Comparator<T> cmp) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int c = cmp.compare(arr[mid], target);
            if (c == 0)
                return mid;
            if (c < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
}
