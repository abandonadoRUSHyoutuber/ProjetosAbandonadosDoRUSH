package rush.mior;

import java.util.Scanner;

public class All {
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int[] numeros = new int[10];
		for (int i=0; i < numeros.length; i++) { 
			System.out.println("Digite o " + i + "° número: ");
			numeros[i] = leitor.nextInt();
		}
		
		for (int i=0; i < numeros.length; i++) { 
			System.out.println("Valor na posição " + i + ": " + numeros[i]);
		}
	}

}
