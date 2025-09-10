package Parcial_1_Dennis_Franco;

// Demo: create students, show sorts and searches
public class Main {
    public static void main(String[] args) {
        // 1) Sample data: 10 students (coherent values)
        Student[] students = new Student[] {
                new Student(19, 2, 2, 8, 4, "Computer Science", 101),
                new Student(22, 6, 3, 24, 2, "Industrial Engineering", 202),
                new Student(21, 4, 1, 16, 6, "Business Administration", 303),
                new Student(24, 8, 4, 32, 1, "Mechanical Engineering", 404),
                new Student(20, 3, 2, 12, 6, "Mathematics", 505),
                new Student(23, 7, 5, 30, 2, "Electronics Engineering", 606),
                new Student(18, 1, 1, 4, 8, "Design", 707),
                new Student(25, 9, 6, 36, 0, "Law", 808),
                new Student(22, 5, 3, 20, 4, "Chemistry", 909),
                new Student(26, 10, 4, 40, 0, "Medicine", 1001)
        };

        // Keep a copy to show original list
        Student[] original = Algorithms.cloneArray(students);

        // Show original list
        Algorithms.printStudents(original, "Original list");

        // 2) Sorts

        // Insertion Sort by age (asc)
        Student[] byAge = Algorithms.cloneArray(original);
        Algorithms.insertionSortByAgeAscending(byAge);
        Algorithms.printStudents(byAge, "Insertion Sort by age (asc)");

        // Selection Sort by semester (desc)
        Student[] bySemesterDesc = Algorithms.cloneArray(original);
        Algorithms.selectionSortBySemesterDescending(bySemesterDesc);
        Algorithms.printStudents(bySemesterDesc, "Selection Sort by semester (desc)");

        // Bubble Sort by stratum (asc) - extra
        Student[] byStratum = Algorithms.cloneArray(original);
        Algorithms.bubbleSortByStratumAscending(byStratum);
        Algorithms.printStudents(byStratum, "Bubble Sort by stratum (asc)");

        // 3) Searches

        // Linear Search by programId
        int targetProgramId = 606;
        int idxProgram = Algorithms.linearSearchByProgramId(original, targetProgramId);
        System.out.println("\n--- Linear Search: programId = " + targetProgramId + " ---");
        if (idxProgram >= 0) {
            System.out.println("Found at index " + idxProgram + ":");
            System.out.println(original[idxProgram]);
        } else {
            System.out.println("Not found.");
        }

        // Binary Search by age (use age-sorted array)
        int targetAge = 22;
        System.out.println("\n--- Binary Search: age = " + targetAge + " ---");
        int idxAge = Algorithms.binarySearchByAge(byAge, targetAge);
        if (idxAge >= 0) {
            System.out.println("Found at index " + idxAge + ":");
            System.out.println(byAge[idxAge]);
        } else {
            System.out.println("Not found.");
        }
    }
}
