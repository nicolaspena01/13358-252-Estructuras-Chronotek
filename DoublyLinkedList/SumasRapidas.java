import java.util.*;

class Node {
    String nombre;
    int puntaje;
    Node prev;
    Node next;

    public Node(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    // Insertar jugador en orden descendente por puntaje
    public void insertInOrder(String nombre, int puntaje) {
        Node nuevo = new Node(nombre, puntaje);
        if (head == null) {
            head = tail = nuevo;
        } else {
            Node actual = head;
            while (actual != null && puntaje < actual.puntaje) {
                actual = actual.next;
            }

            // Insertar al principio
            if (actual == head) {
                nuevo.next = head;
                head.prev = nuevo;
                head = nuevo;
            }
            // Insertar al final
            else if (actual == null) {
                tail.next = nuevo;
                nuevo.prev = tail;
                tail = nuevo;
            }
            // Insertar en medio
            else {
                nuevo.prev = actual.prev;
                nuevo.next = actual;
                actual.prev.next = nuevo;
                actual.prev = nuevo;
            }
        }

        size++;
        if (size > 5) removeLast(); // mantener solo top 5
    }

    // Eliminar ultimo nodo
    public void removeLast() {
        if (tail != null) {
            tail = tail.prev;
            if (tail != null) tail.next = null;
            size--;
        }
    }

    // Mostrar ranking (de mayor a menor puntaje)
    public void showRanking() {
        System.out.println("\nRANKING DE LOS 5 MEJORES JUGADORES");
        Node actual = head;
        int pos = 1;
        while (actual != null) {
            System.out.println(pos + ". " + actual.nombre + " - " + actual.puntaje + " puntos");
            actual = actual.next;
            pos++;
        }
        if (head == null) System.out.println("Aun no hay jugadores en el ranking.");
    }

    // Mostrar de atras hacia adelante (opcional)
    public void showReverse() {
        System.out.println("\nRanking en orden inverso:");
        Node actual = tail;
        int pos = 1;
        while (actual != null) {
            System.out.println(pos + ". " + actual.nombre + " - " + actual.puntaje);
            actual = actual.prev;
            pos++;
        }
    }

    // Limpiar lista
    public void clear() {
        head = tail = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }
}

public class SumasRapidas {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static DoublyLinkedList ranking = new DoublyLinkedList();

    public static void main(String[] args) {
        boolean jugarDeNuevo = true;
        while (jugarDeNuevo) {
            System.out.print("\nIngrese su nombre de jugador: ");
            String nombre = sc.nextLine();
            int puntaje = jugar(nombre);
            ranking.insertInOrder(nombre, puntaje);
            ranking.showRanking();

            System.out.print("\nDesea jugar otra vez? (s/n): ");
            String resp = sc.nextLine().trim().toLowerCase();
            jugarDeNuevo = resp.equals("s");
        }
        System.out.println("\nGracias por jugar Sumas Rapidas!");
    }

    public static int jugar(String nombre) {
        int puntaje = 0;
        int nivel = 1;
        int tiempoLimite = 10;
        boolean continuar = true;

        System.out.println("\nBienvenido " + nombre + " al juego de SUMAS RAPIDAS");

        while (continuar) {
            System.out.println("\n=== NIVEL " + nivel + " ===");
            for (int i = 1; i <= 5; i++) {
                int a = rand.nextInt(50) + 1;
                int b = rand.nextInt(50) + 1;
                int resultadoCorrecto = a + b;

                System.out.println("\nTiempo disponible: " + tiempoLimite + " segundos");
                System.out.print("Pregunta " + i + ": Cuanto es " + a + " + " + b + "? -> ");

                long inicio = System.currentTimeMillis();
                String respuesta = leerConTiempo(tiempoLimite * 1000);
                long fin = System.currentTimeMillis();

                if (respuesta == null) {
                    System.out.println("\nSe acabo el tiempo!");
                    continuar = false;
                    break;
                }

                try {
                    int respuestaInt = Integer.parseInt(respuesta);
                    if (respuestaInt == resultadoCorrecto) {
                        long duracion = (fin - inicio) / 1000;
                        System.out.println("Correcto en " + duracion + "s");
                        puntaje += 100;
                    } else {
                        System.out.println("Incorrecto. La respuesta era " + resultadoCorrecto);
                        continuar = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Respuesta invalida.");
                    continuar = false;
                    break;
                }
            }

            if (continuar) {
                nivel++;
                tiempoLimite = Math.max(2, tiempoLimite - 2);
                System.out.println("\nNivel superado! Pasas al nivel " + nivel);
            }
        }

        System.out.println("\nJuego terminado para " + nombre + ". Puntaje final: " + puntaje);
        return puntaje;
    }

    public static String leerConTiempo(long tiempoMaximoMs) {
        long inicio = System.currentTimeMillis();
        while ((System.currentTimeMillis() - inicio) < tiempoMaximoMs) {
            try {
                if (System.in.available() > 0) {
                    return sc.nextLine();
                }
                Thread.sleep(50);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
