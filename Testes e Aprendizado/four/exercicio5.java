package rush.four;

import java.util.Scanner;

public class exercicio5 {
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int[][] M = new int[3][3];
		int p = 0;
		int somaL1 = 0;
		int somaC3 = 0;
		int somaDP = 0;
		int somaGeral = 0;
		for (int i=0; i < M.length; i++) {
			for (int j=0; j < M[i].length; j++) {
				System.out.println("Digite um número: ");
				M[i][j] = leitor.nextInt();
				somaGeral += M[i][j];
				
				if (i == 0) somaL1 += M[i][j];
				if (j == 2) somaC3 += M[i][j];
				if (j == p) somaDP += M[i][j]; 
			}
			p++; 
		}
		
		System.out.println("");
		System.out.println("--------------------------");
		System.out.println("   R E S U L T A D O S    ");
		System.out.println("--------------------------");
		System.out.println("");
		System.out.println("Soma dos elementos da linha 1 = " + somaL1);
		System.out.println("Soma dos elementos da coluna 3 = " + somaC3);
		System.out.println("Soma dos elementos da diagonal principal = " + somaDP);
		System.out.println("Soma de todos elementos da matriz = " + somaGeral);
	}
}
