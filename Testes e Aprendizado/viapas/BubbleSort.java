package viapas;

import java.util.Scanner;

public class BubbleSort {

	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int[] vet = new int[10];
		int maior = vet[0], posMaior = 0;
		
		/*
		 * Lendo o vetor e garantido que nenhum número é repetido
		 */
		for (int i = 0; i < vet.length; i++) {
			System.out.println("Informe um número: ");
			vet[i] = leitor.nextInt();
			boolean verificar = true;
			while (verificar) {
				verificar = false;
				for (int j = 0; j < i; j++) {
					while (vet[j] == vet[i]) {
						System.err.println("Número repetido, por favor informe outro: ");
						vet[i] = leitor.nextInt();
						verificar = true;
					}
				}
			}
		}

		/*                                   
		 * Invertendo o vetor
		 */
		for (int i = 0; i < vet.length / 2; i++) {
			int aux = vet[i];
			vet[i] = vet[vet.length - 1 - i];
			vet[vet.length - 1 - i] = aux;
		}

		/*
		 * Encontrando o maior numero do vetor e seu indice
		 */
		for (int i = 0; i < vet.length; i++) {
			if (vet[i] > maior) {
				maior = vet[i];
				posMaior = i;
			}
		}

		/*
		 * Imprimindo o vetor
		 */
		for (int i = 0; i < vet.length; i++) {
			System.out.println(vet[i]);
		}

		/*
		 * Imprindo o maior número do vetor e seu indice
		 */
		System.out.println("MAIOR: " + maior);
		System.out.println("ÍNDICE: " + posMaior);

		/*
		 * Colocando o vetor em ordem crescente
		 */

		for (int i = 0; i < vet.length - 1; i++) {
			boolean troca = false;
			for (int j = 0; j < vet.length - 1 -i; j++) {
				if (vet[j] > vet[j + 1]) {
					int aux = vet[j];
					vet[j] = vet[j + 1];
					vet[j + 1] = aux;
					troca = true;
				}
			}
			if (!troca) {
				break;
			}
		}
		
		/**
		 *  @author Mior
		 */

		/*
		 * Imprimindo o vetor novamente
		 */
		for (int i = 0; i < vet.length; i++) {
			System.out.println(vet[i]);
		}
	}
}
