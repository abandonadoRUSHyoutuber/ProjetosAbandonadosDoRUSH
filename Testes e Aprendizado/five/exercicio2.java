package five;

import java.util.Scanner;

public class exercicio2 {
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int[][] A = new int[5][5];
		int[][] B = new int[5][5];
		
		int p = 0;
		for (int i=0; i < A.length; i++) {
			for (int j=0; j < A[i].length; j++) {
				System.out.println("Digite um número: ");
				A[i][j] = leitor.nextInt();
				
				if (j == p) B[i][j] = A[i][j] * 3;
				else B[i][j] = A[i][j] *2;
			}
			p++; 
		}

		System.out.println("");
		System.out.println("-------------------------------");
		System.out.println("        EXIBINDO MATRIZ        ");
		System.out.println("-------------------------------");
		System.out.println("");
		
		for (int i=0; i < A.length; i++) {
			for (int j=0; j < A[i].length; j++) {
				System.out.println(B[i][j]);				
			}
			System.out.println("   "); // Para facilitar a visualização de cada linha
		}
	}
}
