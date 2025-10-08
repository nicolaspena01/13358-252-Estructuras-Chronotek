public class Main {
    public static void main(String[] args) {
        System.out.println("=== Prueba de PILA ===");
        Pila pila = new Pila(5);
        for (int i = 1; i <= 5; i++) pila.push(i);
        pila.mostrarPila();
        pila.pop();
        pila.mostrarPila();

        System.out.println("\n=== Prueba de COLA ===");
        Cola cola = new Cola(5);
        for (int i = 1; i <= 5; i++) cola.encolar(i);
        cola.mostrarCola();
        cola.desencolar();
        cola.mostrarCola();
    }
}
