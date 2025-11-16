package datastructures;

public class ArrayStack<T> implements SimpleStack<T> {
  private final Object[] a;
  private int top = -1;

  public ArrayStack(int cap) { a = new Object[cap]; }

  public void push(T x) {
    if (top + 1 == a.length) throw new IllegalStateException("Pila llena");
    a[++top] = x;
  }

  @SuppressWarnings("unchecked")
  public T pop() {
    if (top < 0) return null;
    T v = (T) a[top];
    a[top--] = null;
    return v;
  }

  @SuppressWarnings("unchecked")
  public T peek() { return top < 0 ? null : (T) a[top]; }

  public boolean isEmpty() { return top < 0; }
  public int size() { return top + 1; }
}

