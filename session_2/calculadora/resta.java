import java.util.Scanner;

class Resta {

    // Resta a - b usando solo ++ y --
    public static int restar(int a, int b, boolean mostrarPasos) {
        int c = a;

        if (mostrarPasos) {
            System.out.println("Inicio -> a=" + a + ", b=" + b + ", c=a=" + c);
        }

        // Caso b positivo: vamos quitando
        while (b > 0) {
            c--;
            b--;
            if (mostrarPasos) System.out.println("Paso -> b--, c-- => b=" + b + ", c=" + c);
        }

        // Caso b negativo: es como sumar |b|
        while (b < 0) {
            c++;
            b++;
            if (mostrarPasos) System.out.println("Paso -> b++, c++ => b=" + b + ", c=" + c);
        }

        if (mostrarPasos) {
            System.out.println("Fin -> c=" + c + " (resultado)");
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Resta ===");
        System.out.print("Ingresa a: ");
        int a = sc.nextInt();
        System.out.print("Ingresa b: ");
        int b = sc.nextInt();

        // true para mostrar paso a paso
        int resultado = restar(a, b, true);

        System.out.println("\nResultado final: " + resultado);
        sc.close();
    }
}
