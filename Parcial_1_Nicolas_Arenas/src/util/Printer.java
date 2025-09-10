public class Printer {

    public static void printTitle(String title) {
        System.out.println("\n=== " + title + " ===");
    }

    public static void printList(Student[] a) {
        for (Student s : a) {
            System.out.println(s);
        }
    }

    public static void printSearchResult(String label, int index, Student[] a) {
        if (index >= 0) {
            System.out.println(label + " -> FOUND at index " + index + ": " + a[index]);
        } else {
            System.out.println(label + " -> NOT FOUND");
        }
    }

    /** Utility: copy array to avoid in-place sort side effects */
    public static Student[] copyOf(Student[] a) {
        Student[] b = new Student[a.length];
        System.arraycopy(a, 0, b, 0, a.length);
        return b;
    }
}
