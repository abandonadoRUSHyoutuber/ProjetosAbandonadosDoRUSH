package five;

import java.util.Scanner;

public class exercicio4 {
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int[] A = new int[4];
		int[] B = new int[4];
		int[] C = new int[4];
		int[] D = new int[4];
		int[][] E = new int[4][4];
		
		for (int i=0; i < A.length; i++) {
			System.out.println("#A Digite um número: ");
			A[i] = leitor.nextInt();
			System.out.println("#B Digite um número: ");
			B[i] = leitor.nextInt();
			System.out.println("#C Digite um número: ");
			C[i] = leitor.nextInt();
			System.out.println("#D Digite um número: ");
			D[i] = leitor.nextInt();
		}
		
		for (int i=0; i < A.length; i++) {
			for (int j=0; j < E[i].length; j++) {
				switch (i) {
					case 0:
						E[i][j] = A[i] * 2; 
						break;
					case 1:
						E[i][j] = B[i] * 3; 
						break;
					case 2:
						E[i][j] = C[i] * 4; 
						break;
					case 3:
						E[i][j] = CALCULO(D[i]); 
						break;
				}
			}
		}
		
		System.out.println("");
		System.out.println("-------------------------------");
		System.out.println("        EXIBINDO MATRIZ        ");
		System.out.println("-------------------------------");
		System.out.println("");
		
		for (int i=0; i < A.length; i++) {
			for (int j=0; j < E[i].length; j++) {
				System.out.println(E[i][j]);				
			}
			System.out.println("   "); // Para facilitar a visualização de cada linha
		}
	}
	
	private static int CALCULO(int x) {
		return x * 5;
	}
}