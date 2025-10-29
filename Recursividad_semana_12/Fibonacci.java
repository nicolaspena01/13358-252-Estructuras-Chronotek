import java.util.*;

public final class Fibonacci {

    // Recursivo (claro pero lento: O(2^n) tiempo, O(n) espacio por pila)
    public static long fibRec(int n) {
        if (n < 0) throw new IllegalArgumentException("n >= 0");
        if (n <= 1) return n;          // caso base
        return fibRec(n - 1) + fibRec(n - 2); // paso recursivo
    }

    // Iterativo (eficiente: O(n) tiempo, O(1) espacio)
    public static long fibIt(int n) {
        if (n < 0) throw new IllegalArgumentException("n >= 0");
        if (n <= 1) return n;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    // Serie 0..n (usa el método iterativo)
    public static List<Long> serieHastaN(int n) {
        if (n < 0) throw new IllegalArgumentException("n >= 0");
        List<Long> serie = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) serie.add(fibIt(i));
        return serie;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("F(" + n + ") rec: " + fibRec(n)); // 55
        System.out.println("F(" + n + ") it : " + fibIt(n));  // 55
        System.out.println("Serie 0.." + n + ": " + serieHastaN(n));
        // -> [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
    }
}
