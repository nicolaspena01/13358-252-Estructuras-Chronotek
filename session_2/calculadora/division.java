import java.util.Scanner;

class Division {

    public static int dividir(int a, int b, boolean mostrarPasos) {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir entre cero.");
        }

        // Determinar signo del resultado
        boolean negativo = (a < 0 && b > 0) || (a > 0 && b < 0);

        // Trabajar con valores absolutos
        int dividendo = Math.abs(a);
        int divisor = Math.abs(b);
        int cociente = 0;

        if (mostrarPasos) {
            System.out.println("Inicio -> a=" + a + ", b=" + b);
            System.out.println("Trabajando con absolutos: dividendo=" + dividendo + ", divisor=" + divisor);
        }

        // Resta repetida
        while (dividendo >= divisor) {
            dividendo -= divisor;
            cociente++;
            if (mostrarPasos) System.out.println("Paso -> dividendo=" + dividendo + ", cociente=" + cociente);
        }

        // Aplicar signo
        if (negativo) cociente = -cociente;

        if (mostrarPasos) {
            System.out.println("Fin -> Cociente=" + cociente + ", Resto=" + dividendo);
        }
        return cociente;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== División ===");
        System.out.print("Ingresa a (dividendo): ");
        int a = sc.nextInt();
        System.out.print("Ingresa b (divisor): ");
        int b = sc.nextInt();

        int resultado = dividir(a, b, true);

        System.out.println("\nResultado final: " + resultado);
        sc.close();
    }
}
