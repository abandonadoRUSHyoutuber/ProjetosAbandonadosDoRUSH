package five;

import java.util.Scanner;

public class exercicio5 {
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		
		String[] produtos = new String[5];
		double[][] precos = new double[4][5];
		double[][] imposto = new double[4][5];
		double[] frete = new double[5];
		
		for (int i=0; i < produtos.length; i++) {
			System.out.println("Informe o nome do " + (i+1) + "º produto: ");
			produtos[i] = leitor.nextLine();
		}
		
		for (int i=0; i < precos.length; i++) {
			for (int j=0; j < precos[i].length; j++) {
				System.out.println("Loja #" + (i+1) + " Informe o preço do produto '" + produtos[j] + "':");
				precos[i][j] = leitor.nextDouble();
			}
		}
		
		for (int i=0; i < frete.length; i++) {
			System.out.println("Informe o custo do frete do produto '" + produtos[i] + "': ");
			frete[i] = leitor.nextDouble();
		}
		
		for (int i=0; i < precos.length; i++) {
			for (int j=0; j < precos[i].length; j++) {
				if (precos[i][j] < 50) imposto[i][j] = precos[i][j] * 0.05;
				else if (precos[i][j] <= 100) imposto[i][j] = precos[i][j] * 0.10;
				else imposto[i][j] = precos[i][j] * 0.20;
			}
		}
		
		double precoFinal;
		for (int i=0; i < precos.length; i++) {
			for (int j=0; j < precos[i].length; j++) {
				precoFinal = precos[i][j] + imposto[i][j] + frete[j];
				System.out.println("Loja #" + (i+1) + " - Produto '" + produtos[j]  + "' - Imposto: " + imposto[i][j] + " -"
								 + " Transporte: " + frete[j] + " - Preço: " + precos[i][j] + " - Preço final: " + precoFinal);
			}
		}
	}
}
