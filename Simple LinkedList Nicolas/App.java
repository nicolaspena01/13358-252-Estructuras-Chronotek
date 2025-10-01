import java.util.Scanner;

public class App {
    private static final Scanner sc = new Scanner(System.in);
    private static final ClassRepository repo = new ClassRepository();

    public static void main(String[] args) {
        System.out.println("=== Gestión de Oferta Académica (Listas Enlazadas Simples) ===");
        int op;
        do {
            menu();
            op = leerEntero("Opción");
            switch (op) {
                case 1 -> crearClase();
                case 2 -> listarClases();
                case 3 -> actualizarClase();
                case 4 -> eliminarClase();
                case 5 -> inscribirAlumno();
                case 6 -> actualizarAlumno();
                case 7 -> retirarAlumno();
                case 8 -> buscarAlumno();
                case 9 -> listarAlumnos();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
            System.out.println();
        } while (op != 0);
    }

    private static void menu() {
        System.out.println("""
            ------------------------------
            1) Crear clase
            2) Listar clases
            3) Actualizar clase
            4) Eliminar clase
            5) Inscribir alumno (orden por idNumber)
            6) Actualizar alumno por idNumber
            7) Retirar alumno por idNumber
            8) Buscar alumno por idNumber
            9) Listar alumnos de una clase
            0) Salir
            """);
    }

    private static int leerEntero(String label) {
        while (true) {
            System.out.print(label + ": ");
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Debe ser un número entero.");
            }
        }
    }

    private static String leerNoVacio(String label) {
        while (true) {
            System.out.print(label + ": ");
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("No puede estar vacío.");
        }
    }

    // --- CRUD de clases ---

    private static void crearClase() {
        int id = leerEntero("Id de la clase (int)");
        String name = leerNoVacio("Nombre");
        int credits;
        while (true) {
            credits = leerEntero("Créditos (>0)");
            if (credits > 0) break;
            System.out.println("Créditos debe ser > 0");
        }
        AcademicClass c;
        try {
            c = new AcademicClass(id, name, credits);
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
            return;
        }
        boolean ok = repo.create(c);
        System.out.println(ok ? "Clase creada." : "Ya existe una clase con ese id.");
    }

    private static void listarClases() {
        repo.printAll();
    }

    private static void actualizarClase() {
        int id = leerEntero("Id de la clase");
        AcademicClass c = repo.read(id);
        if (c == null) {
            System.out.println("No existe la clase.");
            return;
        }
        System.out.println("Clase actual: " + c);
        String newName = leerNoVacio("Nuevo nombre (deja igual si repites el actual)");
        Integer newCredits;
        while (true) {
            newCredits = leerEntero("Nuevos créditos (>0)");
            if (newCredits > 0) break;
            System.out.println("Créditos debe ser > 0");
        }
        try {
            boolean ok = repo.update(id, newName, newCredits);
            System.out.println(ok ? "Clase actualizada." : "No se pudo actualizar.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void eliminarClase() {
        int id = leerEntero("Id de la clase");
        boolean ok = repo.delete(id);
        System.out.println(ok ? "Clase eliminada y lista liberada." : "No existe la clase.");
    }

    // --- Operaciones sobre alumnos (lista enlazada simple) ---

    private static AcademicClass pedirClase() {
        int id = leerEntero("Id de la clase");
        AcademicClass c = repo.read(id);
        if (c == null) {
            System.out.println("No existe la clase.");
            return null;
        }
        return c;
    }

    private static void inscribirAlumno() {
        AcademicClass c = pedirClase();
        if (c == null) return;

        String idNumber = leerNoVacio("idNumber (único en la clase)");
        String first = leerNoVacio("firstName");
        String last = leerNoVacio("lastName");
        int semester = leerEntero("semester (int)");
        String program = leerNoVacio("program");

        Student s = new Student(first, last, idNumber, semester, program);
        boolean ok = c.enrollSortedById(s);
        if (ok) {
            System.out.println("Inscripción exitosa (en lista ordenada por idNumber).");
            c.printStudents();
        } else {
            System.out.println("Inscripción rechazada: idNumber duplicado.");
        }
    }

    private static void actualizarAlumno() {
        AcademicClass c = pedirClase();
        if (c == null) return;

        String idNumber = leerNoVacio("idNumber a actualizar");
        Student existe = c.findById(idNumber);
        if (existe == null) {
            System.out.println("Alumno no encontrado.");
            return;
        }
        System.out.println("Alumno actual: " + existe);

        String newFirst = leerNoVacio("Nuevo firstName (o repite el actual)");
        String newLast = leerNoVacio("Nuevo lastName (o repite el actual)");
        int newSemester = leerEntero("Nuevo semester (int)");
        String newProgram = leerNoVacio("Nuevo program");

        boolean ok = c.updateStudentById(idNumber, newFirst, newLast, newSemester, newProgram);
        System.out.println(ok ? "Alumno actualizado." : "No se pudo actualizar.");
    }

    private static void retirarAlumno() {
        AcademicClass c = pedirClase();
        if (c == null) return;

        String idNumber = leerNoVacio("idNumber a retirar");
        boolean ok = c.removeStudentById(idNumber);
        System.out.println(ok ? "Alumno retirado. Lista consistente:" : "Alumno no encontrado.");
        c.printStudents();
    }

    private static void buscarAlumno() {
        AcademicClass c = pedirClase();
        if (c == null) return;

        String idNumber = leerNoVacio("idNumber a buscar");
        Student s = c.findById(idNumber);
        if (s == null) System.out.println("No encontrado.");
        else System.out.println("Encontrado: " + s);
    }

    private static void listarAlumnos() {
        AcademicClass c = pedirClase();
        if (c == null) return;

        System.out.println("Alumnos en " + c.getName() + ":");
        c.printStudents();
    }
}
