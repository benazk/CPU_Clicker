public class Functions {

	public static int fibonacci(int n, int b)
	{
	    int a = 0, c, i;
	    if (n == 0)
	        return a;
	    for (i = 2; i <= n; i++) {
	        c = a + b;
	        a = b;
	        b = c;
	    }
	    return b;
	}
	
	public static int mejora2Price(int cantMej) {
		int PrecioBase = 10;
		int precioFinal = fibonacci(cantMej,PrecioBase);
		return precioFinal;

	}
	
	public static int mejora2(int cantMej) {
		int cantFinal = 1;
		for(int i = 0; i <= cantMej * (1+Juego.BSoD); i++) {
			cantFinal += i;
		}
		return cantFinal;
	}
	
	public static void mejora1() {
		
	}

	public static void mejora3() {

	}

	public static void mejora4() {

	}

	public static void mejora5() {

	}

}
