public class Fib {
  static long f(int n) {            // F(0)=0, F(1)=1
    return (n < 2) ? n : f(n-1) + f(n-2);
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);      // ej.: java Fib 10
    System.out.println(f(n));               // n-ésimo Fibonacci

    // (Opcional) imprimir la serie 0..n usando la misma f():
    for (int i = 0; i <= n; i++) System.out.print(f(i) + (i<n?" ":"\n"));
  }
}
