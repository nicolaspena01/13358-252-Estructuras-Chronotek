public class AcademicClass {
    private int id;
    private String name;
    private int credits;

    // REFERENCIA al primer nodo (lista enlazada simple)
    private Student head;

    public AcademicClass(int id, String name, int credits) {
        if (credits <= 0) {
            throw new IllegalArgumentException("credits debe ser positivo");
        }
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.head = null; // lista vacía
    }

    // --- Getters/Setters seguros ---
    public int getId() { return id; }
    public String getName() { return name; }
    public int getCredits() { return credits; }
    public void setName(String name) { this.name = name; }
    public void setCredits(int credits) {
        if (credits <= 0) throw new IllegalArgumentException("credits debe ser positivo");
        this.credits = credits;
    }

    // --- Utilidades de comparación para ordenar por idNumber (ascendente) ---
    private int compareId(String a, String b) {
        // Si ambos son numéricos, comparar como números; de lo contrario, lexicográfico
        boolean da = a != null && a.matches("\\d+");
        boolean db = b != null && b.matches("\\d+");
        if (da && db) {
            long la = Long.parseLong(a);
            long lb = Long.parseLong(b);
            return Long.compare(la, lb);
        }
        return a.compareToIgnoreCase(b);
    }

    // --- Operaciones sobre la lista enlazada de alumnos ---

    /** Inscripción ordenada por idNumber (rechaza duplicados). */
    public boolean enrollSortedById(Student s) {
        if (s == null || s.idNumber == null) {
            throw new IllegalArgumentException("Student y idNumber no deben ser nulos");
        }
        // Caso lista vacía
        if (head == null) {
            head = s;
            s.next = null;
            return true;
        }
        // Duplicado en head
        if (head.idNumber.equals(s.idNumber)) {
            return false; // duplicado
        }
        // Insertar antes del head si corresponde
        if (compareId(s.idNumber, head.idNumber) < 0) {
            s.next = head;
            head = s;
            return true;
        }
        // Recorrido general
        Student prev = head;
        Student curr = head.next;
        while (curr != null) {
            if (curr.idNumber.equals(s.idNumber)) {
                return false; // duplicado
            }
            if (compareId(s.idNumber, curr.idNumber) < 0) {
                // insertar entre prev y curr
                prev.next = s;
                s.next = curr;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        // Insertar al final
        prev.next = s;
        s.next = null;
        return true;
    }

    /** Buscar alumno por idNumber (recorrido secuencial). */
    public Student findById(String idNumber) {
        Student curr = head;
        while (curr != null) {
            if (curr.idNumber.equals(idNumber)) return curr;
            curr = curr.next;
        }
        return null;
    }

    /** Actualizar alumno por idNumber. Retorna true si se actualizó. */
    public boolean updateStudentById(String idNumber, String newFirst, String newLast, Integer newSemester, String newProgram) {
        Student node = findById(idNumber);
        if (node == null) return false;
        if (newFirst != null) node.firstName = newFirst;
        if (newLast != null) node.lastName = newLast;
        if (newSemester != null) node.semester = newSemester;
        if (newProgram != null) node.program = newProgram;
        return true;
    }

    /** Retiro (eliminar) alumno por idNumber. Maneja head/intermedio/último. */
    public boolean removeStudentById(String idNumber) {
        if (head == null) return false;
        if (head.idNumber.equals(idNumber)) {
            Student tmp = head;
            head = head.next;
            tmp.next = null; // liberar enlace del nodo removido
            return true;
        }
        Student prev = head;
        Student curr = head.next;
        while (curr != null) {
            if (curr.idNumber.equals(idNumber)) {
                prev.next = curr.next;
                curr.next = null; // liberar
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    /** Lista a todos los alumnos recorriendo desde head (sin colecciones de alto nivel). */
    public void printStudents() {
        if (head == null) {
            System.out.println("[Lista vacía]");
            return;
        }
        Student curr = head;
        while (curr != null) {
            System.out.println(" - " + curr);
            curr = curr.next;
        }
    }

    /** Tamaño de la lista (O(n)). */
    public int size() {
        int c = 0;
        Student curr = head;
        while (curr != null) {
            c++;
            curr = curr.next;
        }
        return c;
    }

    /** Liberar la lista enlazada por completo (para eliminar la clase). */
    public void clearStudents() {
        Student curr = head;
        while (curr != null) {
            Student next = curr.next;
            curr.next = null;
            curr = next;
        }
        head = null;
    }

    @Override
    public String toString() {
        return String.format("Class{id=%d, name=%s, credits=%d, students=%d}", id, name, credits, size());
    }
}
