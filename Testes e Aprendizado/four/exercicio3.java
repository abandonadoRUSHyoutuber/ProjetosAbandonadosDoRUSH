package rush.four;

import java.util.Scanner;

public class exercicio3 {
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int[][] M = new int[7][7];
		int[] PAR = new int[28];
		int[] IMPAR = new int[21];
		int contpar = 0;
		int contimpar = 0;
		
		for (int i=0; i < M.length; i++) {
			for (int j=0; j < M[i].length; j++) {
				System.out.println("Digite um número: ");
				M[i][j] = leitor.nextInt();
				if (i % 2 == 0) {
					PAR[contpar] = M[i][j];
					contpar++;
				} else {
					IMPAR[contimpar] = M[i][j];
					contimpar++;
				}
			}
		}
						
		System.out.println("");
		System.out.println("--------------------------");
		System.out.println("Vetor PAR");
		System.out.println("--------------------------");
		System.out.println("");
		for (int i=0; i < PAR.length; i++) {
			System.out.println(PAR[i]);
		}
		
		System.out.println("");
		System.out.println("--------------------------");
		System.out.println("Vetor IMPAR");
		System.out.println("--------------------------");
		System.out.println("");
		for (int i=0; i < IMPAR.length; i++) {
			System.out.println(IMPAR[i]);
		}
	}
}
