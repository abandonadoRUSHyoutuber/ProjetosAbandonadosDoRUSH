public class TesteTryIf {

	public static void main(String[] args) {
		System.out.println("Iniciando teste com todos os valores nulos!\n");
		test(true);
		System.out.println("\nIniciando o teste sem valores nuloes!\n");
		test(true);
		System.out.println("\nIniciando o teste com metade dos valores nulos!\n");
		test(true);	
	}
	
	public static void test(Boolean allValuesNulleds) {
		
		ObjectInt[]	array = new ObjectInt[100];
		
		if (allValuesNulleds == null) {
			for (Integer i = 0; i < array.length; i++) {
				if (i >= 49) {
					array[i] = new ObjectInt(i, i.toString(), i.doubleValue());
				}
			}
		}
		
		else if (allValuesNulleds) {
			for (Integer i = 0; i < array.length; i++) {
				array[i] = null;
			}
		}		
		
		else {
			for (Integer i = 0; i < array.length; i++) {
				array[i] = new ObjectInt(i, i.toString(), i.doubleValue());
			}
		}

		long tryInicio = 0, tryFim = 0, ifInicio = 0, ifFim = 0, contTry = 0, contIf = 0;
		
		for (int i = 0; i < 100; i++) {
			
			for (int j = 0; j < array.length; j++) {
			
				tryInicio = System.nanoTime();
				testeTry(array[j]);
				tryFim = System.nanoTime();
				
		
				ifInicio = System.nanoTime();
				testeIf(array[j]);
				ifFim = System.nanoTime();
				
				if ((tryFim - tryInicio) > (ifFim - ifInicio)) contIf++;
				else contTry++;
			}
		}
		
		System.out.println("If  Venceu: " + contIf + " vezes");
		System.out.println("try Venceu: " + contTry + " vezes\n");
		
		
		tryInicio = tryFim = ifInicio = ifFim = contTry = contIf = 0;
				
		for (int i = 0; i < 1000; i++) {
			
			for (int j = 0; j < array.length; j++) {
				
				tryInicio = System.nanoTime();
				testeTry(array[j]);
				tryFim = System.nanoTime();
				
		
				ifInicio = System.nanoTime();
				testeIf(array[j]);
				ifFim = System.nanoTime();
				
				if ((tryFim - tryInicio) > (ifFim - ifInicio)) contIf++;
				else contTry++;
			}
		}
		
		System.out.println("If  Venceu: " + contIf + " vezes");
		System.out.println("try Venceu: " + contTry + " vezes\n");
		
		tryInicio = tryFim = ifInicio = ifFim = contTry = contIf = 0;
				
		for (int i = 0; i < 10000; i++) {
			
			for (int j = 0; j < array.length; j++) {
				
				tryInicio = System.nanoTime();
				testeTry(array[j]);
				tryFim = System.nanoTime();
				
		
				ifInicio = System.nanoTime();
				testeIf(array[j]);
				ifFim = System.nanoTime();
				
				if ((tryFim - tryInicio) > (ifFim - ifInicio)) contIf++;
				else contTry++;
			}
		}
		
		System.out.println("If  Venceu: " + contIf + " vezes");
		System.out.println("try Venceu: " + contTry + " vezes\n");
		
		tryInicio = tryFim = ifInicio = ifFim = contTry = contIf = 0;
		
		for (int i = 0; i < 100000; i++) {
			
			for (int j = 0; j < array.length; j++) {
				
				tryInicio = System.nanoTime();
				testeTry(array[j]);
				tryFim = System.nanoTime();
				
		
				ifInicio = System.nanoTime();
				testeIf(array[j]);
				ifFim = System.nanoTime();
				
				if ((tryFim - tryInicio) > (ifFim - ifInicio)) contIf++;
				else contTry++;
			}
		}
		
		System.out.println("If  Venceu: " + contIf + " vezes");
		System.out.println("try Venceu: " + contTry + " vezes");
	}
	
	public static String testeTry(ObjectInt obj) {
		try {
			return obj.toString();
		} catch (Throwable e) {
			return "";
		}
	}
	
	public static String testeIf(ObjectInt obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}
	
}
