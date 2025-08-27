import java.util.Scanner;

class InsertionSort {
    public static void insertionSort(int[] A) {
        int N = A.length;
		int i= 1;

        while (i < N) {
            int current = A[i];
            int j = i - 1;

            while (j >= 0 && A[j] > current) {
                A[j + 1] = A[j];
                j = j - 1;
            }

            A[j + 1] = current; 
			i = i +1;
        }
    }

    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuantos números vas a ingresar? ");
		int n = sc.nextInt();

		int[] arr = new int[n];

        System.out.println("Ingresa los " + n +" números:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

		insertionSort(arr);

        System.out.println("Arreglo ordenado: ");
        for (int num : arr) {
        	System.out.print(num + " ");
		}

    }
}
