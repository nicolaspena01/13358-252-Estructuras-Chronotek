import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        // 0) Generate original dataset
        Student[] original = DataGenerator.generate();

        // 1) Show original list
        Printer.printTitle("Original list (" + original.length + " students)");
        Printer.printList(original);

        // --- Comparators for different criteria ---
        Comparator<Student> byAgeAsc = (a, b) -> Integer.compare(a.getAge(), b.getAge());
        Comparator<Student> bySemesterDesc = (a, b) -> Integer.compare(b.getSemester(), a.getSemester());
        Comparator<Student> byStratumAsc = (a, b) -> Integer.compare(a.getSocioEconomicStratum(), b.getSocioEconomicStratum());

        // 2) Insertion Sort by age (asc)
        Student[] byAge = Printer.copyOf(original);
        Sorts.insertionSort(byAge, byAgeAsc);
        Printer.printTitle("After Insertion Sort by age (asc)");
        Printer.printList(byAge);

        // 3) Bubble Sort by semester (desc)
        Student[] bySemester = Printer.copyOf(original);
        Sorts.bubbleSort(bySemester, bySemesterDesc);
        Printer.printTitle("After Bubble Sort by semester (desc)");
        Printer.printList(bySemester);

        // 4) Selection Sort by socio-economic stratum (asc)
        Student[] byStratum = Printer.copyOf(original);
        Sorts.selectionSort(byStratum, byStratumAsc);
        Printer.printTitle("After Selection Sort by stratum (asc)");
        Printer.printList(byStratum);

        // 5) Linear Search by programId (one success, one failure)
        int existingProgramId = original[6].getProgramId(); // pick one from dataset
        int missingProgramId = 9999;
        int idx1 = Searches.linearSearchByProgramId(original, existingProgramId);
        Printer.printTitle("Linear Search by programId");
        Printer.printSearchResult("programId=" + existingProgramId, idx1, original);
        int idx2 = Searches.linearSearchByProgramId(original, missingProgramId);
        Printer.printSearchResult("programId=" + missingProgramId, idx2, original);

        // 6) Binary Search by age (REQUIRES array sorted by age asc)
        Student[] sortedByAge = Printer.copyOf(original);
        Sorts.insertionSort(sortedByAge, byAgeAsc); // ensure precondition

        int existingAge = sortedByAge[3].getAge(); // an age that exists
        int missingAge = 25;                        // 25 doesn't appear in dataset
        Printer.printTitle("Binary Search by age (array sorted by age asc)");
        int b1 = Searches.binarySearchByAge(sortedByAge, existingAge);
        Printer.printSearchResult("age=" + existingAge, b1, sortedByAge);
        int b2 = Searches.binarySearchByAge(sortedByAge, missingAge);
        Printer.printSearchResult("age=" + missingAge, b2, sortedByAge);

        // End
        Printer.printTitle("Demo finished");
    }
}
