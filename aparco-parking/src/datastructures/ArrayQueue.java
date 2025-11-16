package datastructures;

public class ArrayQueue<T> implements SimpleQueue<T> {
  private final Object[] a;
  private int head = 0, tail = 0, size = 0;

  public ArrayQueue(int cap) { a = new Object[cap]; }

  @Override
  public void enqueue(T x) {
    if (size == a.length) throw new IllegalStateException("Cola llena");
    a[tail] = x;
    tail = (tail + 1) % a.length;
    size++;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T dequeue() {
    if (size == 0) return null;
    T v = (T) a[head];
    a[head] = null;
    head = (head + 1) % a.length;
    size--;
    return v;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T peek() { return size == 0 ? null : (T) a[head]; }

  @Override
  public boolean isEmpty() { return size == 0; }

  @Override
  public int size() { return size; }

  /**
   * Devuelve una copia de la cola en orden de salida (head -> tail).
   */
  public Object[] toArray() {
    Object[] r = new Object[size];
    int idx = head;
    for (int i = 0; i < size; i++) {
      r[i] = a[idx];
      idx = (idx + 1) % a.length;
    }
    return r;
  }
}
