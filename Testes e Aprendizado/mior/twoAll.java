package rush.mior;

public class twoAll {
	
	public static void main(String[] args) {
		int[] numeros = new int[10];
		int n = 75;
		
		for (int i=0; i < numeros.length; i++) {
			numeros[i] = n;
			System.out.println("Valor na posição " + i + ": " + numeros[i]);
			n--;
		}
	}
}
