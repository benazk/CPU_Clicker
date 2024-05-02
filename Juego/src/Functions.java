public class Functions {

	public static int fibonacci(int n, int a, int b)
	{
	    int c, i;
	    if (n == 0)
	        return a;
	    for (i = 2; i <= n; i++) {
	        c = a + b;
	        a = b;
	        b = c;
	    }
	    return b;
	}
	
	public static int mejora1Price(int cantMej) {
		int precioBase = 5;
		int precioFinal = fibonacci(cantMej,precioBase,precioBase*2);
		return precioFinal;
	}
	
	public static int mejora1(int cantMej) {
		int cantFinal = 1;
		for(int i = 0; i <= cantMej; i++) {
			cantFinal += i * (1 + Juego.BSoD);
		}
		return cantFinal;
	}
	
	public static int mejora2Price(int cantMej) {
		int precioBase = 20;
		int precioFinal = fibonacci(cantMej,precioBase,precioBase*2);
		return precioFinal;

	}
	
	public static int mejora2(int cantMej) {
		int cantFinal = 1;
		for(int i = 0; i <= cantMej * (1+Juego.BSoD); i++) {
			cantFinal += i;
		}
		return cantFinal;
	}

	public static void mejora3() {

	}

	public static void mejora4() {

	}

	public static void mejora5() {

	}

}
