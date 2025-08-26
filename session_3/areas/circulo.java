import java.util.Scanner;

class AreasCirculo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Área del Círculo
        System.out.println("\n=== Área de un Círculo ===");
        System.out.print("Ingrese el radio: ");
        double radio = sc.nextDouble();
        double areaCirculo = Math.PI * Math.pow(radio, 2);
        System.out.println("El área del círculo es: " + areaCirculo);

        sc.close();
    }
}


