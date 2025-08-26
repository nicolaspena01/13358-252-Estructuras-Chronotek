import java.util.Scanner;

class AreasFiguras {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		
        // Área del Rectángulo
        System.out.println("\n=== Área de un Rectángulo ===");
        System.out.print("Ingrese la base: ");
        double baseRect = sc.nextDouble();
        System.out.print("Ingrese la altura: ");
        double alturaRect = sc.nextDouble();
        double areaRectangulo = baseRect * alturaRect;
        System.out.println("El área del rectángulo es: " + areaRectangulo);

        sc.close();
    }
}


