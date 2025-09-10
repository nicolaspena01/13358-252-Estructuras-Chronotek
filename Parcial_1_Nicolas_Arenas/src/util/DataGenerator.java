/**
 * Generates a coherent dataset with at least 10 students.
 */
public class DataGenerator {

    public static Student[] generate() {
        return new Student[] {
            new Student(19, 2, 2, 8, 4, "Systems Engineering",     1001),
            new Student(21, 4, 3,16, 6, "Industrial Engineering",   1002),
            new Student(22, 5, 4,20, 5, "Business Administration",  1003),
            new Student(18, 1, 1, 4, 6, "Psychology",               1004),
            new Student(24, 6, 5,28, 2, "Law",                      1005),
            new Student(23, 5, 6,22, 8, "Medicine",                 1006),
            new Student(20, 3, 3,12, 7, "Economics",                1007),
            new Student(26, 7, 4,35, 3, "Architecture",             1008),
            new Student(27, 8, 2,40, 1, "Graphic Design",           1009),
            new Student(28, 9, 5,50, 0, "Mathematics",              1010),
            // extras to show duplicates by age (for binary search behavior)
            new Student(22, 3, 2,14, 5, "Statistics",               1011),
            new Student(20, 2, 1,10, 6, "Philosophy",               1012)
        };
    }
}
