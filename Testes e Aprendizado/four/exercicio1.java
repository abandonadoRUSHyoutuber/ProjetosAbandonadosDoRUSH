package rush.four;

import java.util.Scanner;

public class exercicio1 {
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int cont = 0;
		int[][] mat = new int[4][3];
		for (int i=0; i < mat.length; i++) {
			for (int j=0; j < mat[i].length; j++) {
				System.out.println("Digite um n�mero: ");
				mat[i][j] = leitor.nextInt();
			}
 		}
		
		System.out.println("");
		System.out.println("--------------------------");
		System.out.println("  Exibindo n�meros lidos");
		System.out.println("--------------------------");
		System.out.println("");
		
		for (int i=0; i < mat.length; i++) {
			for (int j=0; j < mat[i].length; j++) {
				System.out.println(mat[i][j]);
			}
		}
		
		System.out.println("");
		System.out.println("--------------------------");
		System.out.println("Digite agora o n�mero no qual voc� quer verificar: ");
		System.out.println("--------------------------");
		System.out.println("");
		int x = leitor.nextInt();
		
		for (int i=0; i < mat.length; i++) {
			for (int j=0; j < mat[i].length; j++) {
				if(x == mat[i][j]) {
					cont++;
				}
			}
 		}
		
		System.out.println("O n�mero " + x + " apareceu " + cont + " vezes na matriz.");
	}
}
