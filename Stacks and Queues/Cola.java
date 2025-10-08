public class Cola {
    private int[] cola;
    private int frente;
    private int fin;
    private int max;

    public Cola(int max) {
        this.max = max;
        cola = new int[max];
        frente = -1;
        fin = -1;
    }

    public boolean estaVacia() {
        return frente == -1;
    }

    public boolean estaLlena() {
        return (fin + 1) % max == frente;
    }

    public void encolar(int dato) {
        if (estaLlena()) {
            System.out.println("La cola está llena. No se puede agregar: " + dato);
        } else {
            if (estaVacia()) {
                frente = 0;
            }
            fin = (fin + 1) % max;
            cola[fin] = dato;
            System.out.println("Se agregó " + dato + " a la cola.");
        }
    }

    public void desencolar() {
        if (estaVacia()) {
            System.out.println("La cola está vacía. No se puede eliminar.");
        } else {
            System.out.println("Se eliminó " + cola[frente] + " de la cola.");
            if (frente == fin) {
                frente = fin = -1; // Cola vacía
            } else {
                frente = (frente + 1) % max;
            }
        }
    }

    public void mostrarCola() {
        System.out.println("\nContenido de la cola:");
        if (estaVacia()) {
            System.out.println("[Vacía]");
        } else {
            int i = frente;
            while (true) {
                System.out.print(cola[i] + " ");
                if (i == fin) break;
                i = (i + 1) % max;
            }
            System.out.println();
        }
    }
}
