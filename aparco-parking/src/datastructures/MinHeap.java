package datastructures;

import java.util.Comparator;

public class MinHeap<T> {
  private Object[] a;
  private int size;
  private final Comparator<T> cmp;

  public MinHeap(int capacity, Comparator<T> cmp) {
    this.a = new Object[capacity];
    this.cmp = cmp;
  }

  public boolean isEmpty() { return size == 0; }
  public int size() { return size; }

  public void add(T value) {
    ensureCapacity();
    a[size] = value;
    siftUp(size);
    size++;
  }

  @SuppressWarnings("unchecked")
  public T peek() {
    if (size == 0) return null;
    return (T) a[0];
  }

  @SuppressWarnings("unchecked")
  public T poll() {
    if (size == 0) return null;
    T res = (T) a[0];
    a[0] = a[size-1];
    a[size-1] = null;
    size--;
    siftDown(0);
    return res;
  }

  private void ensureCapacity() {
    if (size == a.length) {
      Object[] na = new Object[a.length * 2 + 1];
      System.arraycopy(a, 0, na, 0, a.length);
      a = na;
    }
  }

  @SuppressWarnings("unchecked")
  private void siftUp(int idx) {
    while (idx > 0) {
      int parent = (idx - 1) / 2;
      T v = (T) a[idx];
      T pv = (T) a[parent];
      if (cmp.compare(v, pv) >= 0) break;
      a[idx] = pv;
      a[parent] = v;
      idx = parent;
    }
  }

  @SuppressWarnings("unchecked")
  private void siftDown(int idx) {
    while (true) {
      int left = 2*idx + 1;
      int right = 2*idx + 2;
      int smallest = idx;
      if (left < size && cmp.compare((T)a[left], (T)a[smallest]) < 0) smallest = left;
      if (right < size && cmp.compare((T)a[right], (T)a[smallest]) < 0) smallest = right;
      if (smallest == idx) break;
      Object tmp = a[idx];
      a[idx] = a[smallest];
      a[smallest] = tmp;
      idx = smallest;
    }
  }
}
