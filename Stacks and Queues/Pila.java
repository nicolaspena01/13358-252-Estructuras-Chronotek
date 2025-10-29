public class Pila {
    private int[] pila;
    private int tope;
    private int max;

    public Pila(int max) {
        this.max = max;
        pila = new int[max];
        tope = -1;
    }

    public boolean estaVacia() {
        return tope == -1;
    }

    public boolean estaLlena() {
        return tope == max - 1;
    }

    public void push(int dato) {
        if (estaLlena()) {
            System.out.println("La pila está llena. No se puede agregar: " + dato);
        } else {
            tope++;
            pila[tope] = dato;
            System.out.println("Se agregó " + dato + " a la pila.");
        }
    }

    public void pop() {
        if (estaVacia()) {
            System.out.println("La pila está vacía. No se puede eliminar.");
        } else {
            System.out.println("Se eliminó " + pila[tope] + " de la pila.");
            tope--;
        }
    }

    public void mostrarPila() {
        System.out.println("\nContenido de la pila:");
        if (estaVacia()) {
            System.out.println("[Vacía]");
        } else {
            for (int i = tope; i >= 0; i--) {
                System.out.println("| " + pila[i] + " |");
            }
        }
    }
}
