package datastructures;

public interface SimpleStack<T> {
  void push(T x);
  T pop();
  T peek();
  boolean isEmpty();
  int size();
}

