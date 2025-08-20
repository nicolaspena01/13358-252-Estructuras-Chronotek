import java.util.Scanner;

class Suma {

    // Suma a + b usando SOLO ++ y -- (sin usar +)
    // Flujo: c = b; mientras a>0 => c++ y a--; si a<0 => c-- y a++
    public static int sumar(int a, int b, boolean mostrarPasos) {
        int c = b;

        if (mostrarPasos) {
            System.out.println("Inicio -> a=" + a + ", b=" + b + ", c=b=" + c);
        }

        // Si a es positivo: c++ y a--
        while (a > 0) {
            c++;
            a--;
            if (mostrarPasos) System.out.println("Paso -> a--, c++  => a=" + a + ", c=" + c);
        }

        // Si a es negativo: c-- y a++
        while (a < 0) {
            c--;
            a++;
            if (mostrarPasos) System.out.println("Paso -> a++, c--  => a=" + a + ", c=" + c);
        }

        if (mostrarPasos) {
            System.out.println("Fin -> a=" + a + ", c=" + c + " (resultado)");
        }
        return c; // c == a(original) + b
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Suma ===");
        System.out.print("Ingresa a: ");
        int a = sc.nextInt();
        System.out.print("Ingresa b: ");
        int b = sc.nextInt();

        // Cambia a true si quieres ver cada paso
        int resultado = sumar(a, b, true);

        System.out.println("\nResultado final: " + resultado);
        sc.close();
    }
}
