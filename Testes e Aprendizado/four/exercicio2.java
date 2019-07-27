package rush.four;

import java.util.Scanner;

public class exercicio2 {
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int[][] mat = new int[2][3];
		for (int i=0; i < mat.length; i++) {
			for (int j=0; j < mat[i].length; j++) {
				System.out.println("Digite um número: ");
				mat[i][j] = leitor.nextInt();
			}
 		}
		
		System.out.println("");
		System.out.println("--------------------------");
		System.out.println("Digite agora o número pelo qual você quer multiplicar a matriz: ");
		System.out.println("--------------------------");
		System.out.println("");
		int x = leitor.nextInt();

		for (int i=0; i < mat.length; i++) {
			for (int j=0; j < mat[i].length; j++) {
				mat[i][j] *= x;
			}
 		}
		
		System.out.println("");
		System.out.println("--------------------------");
		System.out.println("Exibindo resultados da multiplicação matriz");
		System.out.println("--------------------------");
		System.out.println("");
		
		for (int i=0; i < mat.length; i++) {
			for (int j=0; j < mat[i].length; j++) {
				System.out.println(mat[i][j]);
			}
 		}
	}
}
