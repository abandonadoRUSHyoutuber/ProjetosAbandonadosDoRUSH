package rush.algoritmos;

import java.util.Scanner;

public class Xadrez {
		
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		String[][] xadrez = new String[8][8];
		
		System.out.print("Informe a linha da rainha: ");
		int linha = leitor.nextInt()-1; // Ele diminui 1 porque para o usuario o começo é no 1 não no 0
		System.out.print("Informe a coluna da rainha: ");
		int coluna = leitor.nextInt()-1; // Ele diminui 1 porque para o usuario o começo é no 1 não no 0
		int diagonal1 = coluna-linha;
		int diagonal2 = coluna+linha;
		
		for (int i=0; i < xadrez.length; i++) {
			for (int j=0; j < xadrez[i].length; j++) {
				
				if (i == linha && j == coluna) xadrez[i][j] = "◊";
				else if (i == linha) xadrez[i][j] = "■";
				else if (j == coluna) xadrez[i][j] = "■";
				else if (j == diagonal1) xadrez[i][j] = "■";
				else if (j == diagonal2) xadrez[i][j] = "■";
				else xadrez[i][j] = "□"; 
			}
			diagonal1++;
			diagonal2--;
		}
		
		for (int i=0; i < xadrez.length; i++) {
			for (int j=0; j < xadrez[i].length; j++) {
				 System.out.print(xadrez[i][j]);
			}
			System.out.print("\n"); // Isso server para quebrar a linha e ir para uma nova
		}		
	}
}
