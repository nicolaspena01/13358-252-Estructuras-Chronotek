public class Student {
    public String firstName;
    public String lastName;
    public String idNumber;   // clave única por clase
    public int semester;
    public String program;

    // Puntero al siguiente nodo (lista enlazada simple)
    public Student next;

    public Student(String firstName, String lastName, String idNumber, int semester, String program) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.semester = semester;
        this.program = program;
        this.next = null;
    }

    @Override
    public String toString() {
        return String.format("%s %s | idNumber=%s | semester=%d | program=%s",
                firstName, lastName, idNumber, semester, program);
    }
}
