public class Functions {

	public static int precioFuncion(int precioBase, int cantMejoras)
	{
		int multiplier = 0;
		for(int i = 0; i < cantMejoras; i++) {
			if (i%3 == 0) {
				multiplier += 1;
			}
			precioBase += (int) Math.floor(precioBase/4) + multiplier;
		}
		return precioBase;
	}
	
	public static int mejora1Price(int cantMej) {
		int precioBase = 5;
		int precioFinal = precioFuncion(precioBase,cantMej);;
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
		int precioBase = 40;
		int precioFinal = precioFuncion(precioBase,cantMej);
		return precioFinal;

	}
	
	public static int mejora2(int cantMej) {
		int cantFinal = 1;
		for(int i = 0; i <= cantMej * (1+Juego.BSoD); i++) {
			cantFinal += i;
		}
		return cantFinal;
	}

	public static int mejora3Price(int cantMej) {
		int precioBase = 300;
		int precioFinal = precioFuncion(precioBase,cantMej);
		return precioFinal;

	}
	
	public static int mejora3(int cantMej) {
		int cantFinal = 1;
		for(int i = 0; i <= cantMej * (1+Juego.BSoD); i++) {
			cantFinal += i*8;
		}
		return cantFinal;
	}

	public static int mejora4Price(int cantMej) {
		int precioBase = 2000;
		int precioFinal = precioFuncion(precioBase,cantMej);
		return precioFinal;

	}
	
	public static int mejora4(int cantMej) {
		int cantFinal = 1;
		for(int i = 0; i <= cantMej * (1+Juego.BSoD); i++) {
			cantFinal += i*50;
		}
		return cantFinal;
	}

	

}
