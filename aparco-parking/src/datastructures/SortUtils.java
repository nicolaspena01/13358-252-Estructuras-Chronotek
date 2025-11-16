package datastructures;

import java.util.Comparator;

public class SortUtils {

  /** Ordenamiento Burbuja – O(n^2) */
  public static <T> void bubbleSort(T[] arr, Comparator<T> cmp) {
    int n = arr.length;
    boolean swapped;
    do {
      swapped = false;
      for (int i = 1; i < n; i++) {
        if (cmp.compare(arr[i - 1], arr[i]) > 0) {
          T tmp = arr[i];
          arr[i] = arr[i - 1];
          arr[i - 1] = tmp;
          swapped = true;
        }
      }
      n--; // el último ya está en su sitio
    } while (swapped);
  }

  /** Ordenamiento por Inserción – O(n^2) pero bueno para casi ordenados */
  public static <T> void insertionSort(T[] arr, Comparator<T> cmp) {
    for (int i = 1; i < arr.length; i++) {
      T key = arr[i];
      int j = i - 1;
      while (j >= 0 && cmp.compare(arr[j], key) > 0) {
        arr[j + 1] = arr[j];
        j--;
      }
      arr[j + 1] = key;
    }
  }

  /** Quicksort – O(n log n) promedio */
  public static <T> void quickSort(T[] arr, Comparator<T> cmp) {
    quickSort(arr, 0, arr.length - 1, cmp);
  }

  private static <T> void quickSort(T[] arr, int low, int high, Comparator<T> cmp) {
    if (low >= high) return; // condición de parada (recursividad)
    int i = low, j = high;
    T pivot = arr[(low + high) / 2];
    while (i <= j) {
      while (cmp.compare(arr[i], pivot) < 0) i++;
      while (cmp.compare(arr[j], pivot) > 0) j--;
      if (i <= j) {
        T tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
        i++; j--;
      }
    }
    if (low < j)  quickSort(arr, low, j, cmp);
    if (i   < high) quickSort(arr, i,   high, cmp);
  }

  /** MergeSort – O(n log n), recursivo */
  public static <T> void mergeSort(T[] arr, Comparator<T> cmp) {
    if (arr.length <= 1) return;
    @SuppressWarnings("unchecked")
    T[] aux = (T[]) new Object[arr.length];
    mergeSort(arr, aux, 0, arr.length - 1, cmp);
  }

  private static <T> void mergeSort(T[] arr, T[] aux, int left, int right, Comparator<T> cmp) {
    if (left >= right) return;
    int mid = (left + right) / 2;
    mergeSort(arr, aux, left, mid, cmp);
    mergeSort(arr, aux, mid + 1, right, cmp);
    merge(arr, aux, left, mid, right, cmp);
  }

  private static <T> void merge(T[] arr, T[] aux, int left, int mid, int right, Comparator<T> cmp) {
    System.arraycopy(arr, left, aux, left, right - left + 1);
    int i = left, j = mid + 1, k = left;
    while (i <= mid && j <= right) {
      if (cmp.compare(aux[i], aux[j]) <= 0) {
        arr[k++] = aux[i++];
      } else {
        arr[k++] = aux[j++];
      }
    }
    while (i <= mid) arr[k++] = aux[i++];
    // lo que queda a la derecha ya está en su lugar
  }
}
