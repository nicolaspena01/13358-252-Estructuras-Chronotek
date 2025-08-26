import java.util.Scanner;

 class Multiplicacion {

    public static int multiplicar(int a, int b, boolean mostrarPasos) {
        int c = 0; // resultado

        if (mostrarPasos) {
            System.out.println("Inicio -> a=" + a + ", b=" + b + ", c=" + c);
        }

        // Caso b positivo
        while (b > 0) {
            c = c + a;  // sumamos el valor de a directamente
            b--;
            if (mostrarPasos) System.out.println("Paso -> c=" + c + ", b=" + b);
        }

        // Caso b negativo
        while (b < 0) {
            c = c - a;  // restamos el valor de a directamente
            b++;
            if (mostrarPasos) System.out.println("Paso -> c=" + c + ", b=" + b);
        }

        if (mostrarPasos) {
            System.out.println("Fin -> c=" + c + " (resultado)");
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Multiplicación ===");
        System.out.print("Ingresa a: ");
        int a = sc.nextInt();
        System.out.print("Ingresa b: ");
        int b = sc.nextInt();

        int resultado = multiplicar(a, b, true);

        System.out.println("\nResultado final: " + resultado);
        sc.close();
    }
}
