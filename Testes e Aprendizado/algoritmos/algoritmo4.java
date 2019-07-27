package rush.algoritmos;

import java.util.Random;

public class algoritmo4 {
	
	public static void main(String[] args) {
		int[][] M = new int[30][5];
		int maior = 0;
		int soma = 0;
		Random rd = new Random();
		
		for (int i=0; i < 30; i++) {
			for (int j=0; j < 3; j++) {
				M[i][j] = rd.nextInt(10);
			}
		}
		
		for (int i=0; i < 30; i++) {
			for (int j=0; j < 3; j++) {
				if (j == 0) maior = M[i][j];
				else if (M[i][j] > maior) maior = M[i][j];
			}
			M[i][3] = maior;
		}
		
		for (int i=0; i < 30; i++) {
			for (int j=0; j < 4; j++) {
				soma += M[i][j];
			}
			M[i][4] = soma;
			soma = 0;
		}
		
		for (int i=0; i < 30; i++) {
			for (int j=0; j < 5; j++) {
				System.out.print(M[i][j] + " ");
			}
			System.out.println("");
		}
	}
}
