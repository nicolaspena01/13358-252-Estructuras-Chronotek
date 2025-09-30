class Node {
    Integer value;
    Node next;

    Node(Integer value) {
        this.value = value;
        this.next = null;
    }
}

class LinkedList {

    private Node head;

    // Insertar al inicio
    public void insertAtHead(Integer value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }

    // Insertar después de un nodo dado
    public void LinkedListInsertAfter(Node previous, Node newNode) {
        newNode.next = previous.next;
        previous.next = newNode;
    }

    // Buscar un nodo por posición
    public Node LinkedListLookUp(int elementNumber) {
        Node current = head;
        int count = 0;

        while (count < elementNumber && current != null) {
            current = current.next;
            count = count + 1;
        }
        return current;
    }

    // 🔹 Caso 7: Buscar nodo por valor
    public Node searchByValue(Integer value) {
        Node current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return current; // Encontrado
            }
            current = current.next;
        }
        return null; // No encontrado
    }

    // 🔹 Caso 8: Invertir lista
    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    // Imprimir la lista
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + "->");
            current = current.next;
        }
        System.out.print("/\n");
    }
}

public class LinkedListDemo_codigo {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insertAtHead(50);
        list.insertAtHead(40);
        list.insertAtHead(30);
        list.insertAtHead(20);
        list.insertAtHead(10);

        System.out.println("Lista inicial:");
        list.printList();

        // Insertar 71 después del tercer nodo (posición 2)
        Node previous = list.LinkedListLookUp(2);
        Node newNode = new Node(71);
        list.LinkedListInsertAfter(previous, newNode);

        System.out.println("Después de insertar 71:");
        list.printList();

        // 🔹 Caso 7: Buscar por valor
        Node found = list.searchByValue(40);
        if (found != null) {
            System.out.println("Encontrado el valor: " + found.value);
        } else {
            System.out.println("Valor no encontrado");
        }

        // 🔹 Caso 8: Invertir lista
        list.reverse();
        System.out.println("Lista invertida:");
        list.printList();
    }
}
