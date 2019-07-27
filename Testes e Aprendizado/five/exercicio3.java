package five;

import java.util.Scanner;

public class exercicio3 {
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int[][] A = new int[10][7];
		
		int contPar = 0;
		int contImpar = 0;
		for (int i=0; i < A.length; i++) {
			for (int j=0; j < A[i].length; j++) {
				System.out.println("Digite um número: ");
				A[i][j] = leitor.nextInt();
				if (A[i][j] % 2 == 0) contPar++;
				else contImpar++;
			}
		}
		
		int totalNumeros = contPar + contImpar;
		double porcentPar = (contPar * 100) / totalNumeros;
		double porcentImpar = 100 - porcentPar;
		
		System.out.println("");
		System.out.println("-------------------------------");
		System.out.println("           RESULTADOS          ");
		System.out.println("-------------------------------");
		System.out.println("");
		System.out.println("Total de números pares na matriz: " + contPar);
		System.out.println("Total de números impares na matriz: " + contImpar);
		System.out.println("Porcentagem de números pares na matriz: " + porcentPar);
		System.out.println("Porcentagem de números pares na matriz: " + porcentImpar);
	}
}
