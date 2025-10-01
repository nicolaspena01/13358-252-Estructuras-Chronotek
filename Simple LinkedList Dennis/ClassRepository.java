import java.util.HashMap;
import java.util.Map;

public class ClassRepository {
    // NOTA: La restricción de no usar colecciones aplica SOLO a alumnos dentro de la clase.
    // Para gestionar el catálogo de clases, podemos usar un Map en memoria.
    private final Map<Integer, AcademicClass> data = new HashMap<>();

    public boolean create(AcademicClass c) {
        if (data.containsKey(c.getId())) return false;
        data.put(c.getId(), c);
        return true;
    }

    public AcademicClass read(int id) {
        return data.get(id);
    }

    public boolean update(int id, String newName, Integer newCredits) {
        AcademicClass c = data.get(id);
        if (c == null) return false;
        if (newName != null) c.setName(newName);
        if (newCredits != null) c.setCredits(newCredits);
        return true;
    }

    public boolean delete(int id) {
        AcademicClass c = data.get(id);
        if (c == null) return false;
        // Liberar lista enlazada antes de eliminar la clase
        c.clearStudents();
        data.remove(id);
        return true;
    }

    public void printAll() {
        if (data.isEmpty()) {
            System.out.println("[Sin clases]");
            return;
        }
        for (AcademicClass c : data.values()) {
            System.out.println(" - " + c);
        }
    }
}
