public class Sumatoria{
	public static long sumaHasta(long n){
		if (n < 1) return;
		long suma = 0;
		for (long i=1; i<=n; i++){
			suma += i;
		}
		return suma;
	}
	public static void main(String[] args) {
		if (args.lenght != 1) {
			System.out.println("Ingresa el numero");
		}

		long n = Long.parseLong(args[0]);
		System.out.println("Suma hasta");

	}
}
