package rush.four;

import java.util.Scanner;

public class exercicio7 {
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int[][] mat1 = new int[10][10];
		int[][] mat2 = new int[10][10];

		for (int i=0; i < mat1.length; i++) {
			for (int j=0; j < mat1[i].length; j++) {
				System.out.println("#1 Digite um número: ");
				mat1[i][j] = leitor.nextInt();
			}
		}
		
		for (int i=0; i < mat2.length; i++) {
			for (int j=0; j < mat2[i].length; j++) {
				System.out.println("#2 Digite um número: ");
				mat2[i][j] = leitor.nextInt();
			}
		}
		
		System.out.println("");
		System.out.println("--------------------------");
		System.out.println("EXIBINDO NÚMEROS PRESENTES NAS DUAS MATRIZES");
		System.out.println("--------------------------");
		System.out.println("");
		
		for (int i=0; i < mat1.length; i++) {
			for (int j=0; j < mat1[i].length; j++) {
				for (int k=0; k < mat2.length; k++) {
					for (int l=0; l < mat1[i].length; l++) {
						if (mat1[i][j] == mat2[k][l]) {
							System.out.println(mat1[i][j]);
						}
					}
				}
			}
		}
	}
}