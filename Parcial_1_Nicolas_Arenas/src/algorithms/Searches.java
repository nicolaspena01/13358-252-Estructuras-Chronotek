/**
 * Linear and Binary search implementations for Student[].
 */
public class Searches {

    //Linear Search by programId.
    public static int linearSearchByProgramId(Student[] a, int targetProgramId) {
        for (int i = 0; i < a.length; i++) {
            if (a[i].getProgramId() == targetProgramId) {
                return i;
            }
        }
        return -1;
    }

    //Binary Search by age 
    public static int binarySearchByAge(Student[] a, int targetAge) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int midAge = a[mid].getAge();
            if (midAge == targetAge) return mid;
            if (midAge < targetAge) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }
}
