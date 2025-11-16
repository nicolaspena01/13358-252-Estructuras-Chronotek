package datastructures;

public interface SimpleQueue<T> {
  void enqueue(T x);
  T dequeue();
  T peek();
  boolean isEmpty();
  int size();
}

