import java.util.Scanner;

class AreasFiguras {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Área del Triángulo
        System.out.println("=== Área de un Triángulo ===");
        System.out.print("Ingrese la base: ");
        double base = sc.nextDouble();
        System.out.print("Ingrese la altura: ");
        double altura = sc.nextDouble();
        double areaTriangulo = (base * altura) / 2;
        System.out.println("El área del triángulo es: " + areaTriangulo);

        sc.close();
    }
}


