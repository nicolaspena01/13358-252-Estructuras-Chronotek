public final class Factorial {
    // Recursivo
    public static long factRec(long n) {
        if (n < 0) throw new IllegalArgumentException("n debe ser >= 0");
        if (n <= 1) return 1;            // caso base: 0! = 1 y 1! = 1
        return n * factRec(n - 1);       // paso recursivo y progreso n->n-1
    }

    // Iterativo (equivalente, sin recursión)
    public static long factIt(long n) {
        if (n < 0) throw new IllegalArgumentException("n debe ser >= 0");
        long r = 1;
        for (long i = 2; i <= n; i++) r *= i;  // "iterar" = multiplicar de 2..n
        return r;
    }

    public static void main(String[] args) {
        System.out.println(factRec(5)); // 120
        System.out.println(factIt(5));  // 120
    }
}
