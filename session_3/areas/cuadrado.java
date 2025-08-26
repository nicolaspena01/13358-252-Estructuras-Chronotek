import java.util.Scanner;

class AreasCuadrado {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Área del Cuadrado
        System.out.println("\n=== Área de un Cuadrado ===");
        System.out.print("Ingrese el lado: ");
        double lado = sc.nextDouble();
        double areaCuadrado = lado * lado;
        System.out.println("El área del cuadrado es: " + areaCuadrado);

        sc.close();
    }
}


