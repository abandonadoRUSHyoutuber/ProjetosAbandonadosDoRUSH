package rush.four;

import java.util.Scanner;

public class exercicio4 {
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int[][] M = new int[2][2];
		int maior = 0;
		for (int i=0; i < M.length; i++) {
			for (int j=0; j < M[i].length; j++) {
				System.out.println("Digite um número: ");
				M[i][j] = leitor.nextInt();
				if (i+j == 0) maior = M[i][j];
				else if (M[i][j] > maior) maior = M[i][j];
			}
		}
		
		System.out.println("");
		System.out.println("--------------------------");
		System.out.println("Maior número " + maior + ", matriz final: ");
		System.out.println("--------------------------");
		System.out.println("");
		
		for (int i=0; i < M.length; i++) {
			for (int j=0; j < M[i].length; j++) {
				M[i][j] *= maior;
				System.out.println(M[i][j]);
			}
		}
	}
}
