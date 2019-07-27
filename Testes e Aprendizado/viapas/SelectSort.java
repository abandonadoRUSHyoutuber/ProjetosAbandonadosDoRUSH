package viapas;

import java.util.Random;

public class SelectSort {

	public static void main(String[] args) {
		int[] vet = new int[10];
		Random rnd = new Random();

		/*
		 * Lendo o vetor e garantido que nenhum número é repetido
		 */
		for (int i = 0; i < vet.length; i++) {
			// System.out.println("Informe um número: ");
			vet[i] = rnd.nextInt(100);
			boolean verificar = true;
			while (verificar) {
				verificar = false;
				for (int j = 0; j < i; j++) {
					while (vet[j] == vet[i]) {
						// System.err.println("Número repetido, por favor informe outro: ");
						vet[i] = rnd.nextInt(100);
						verificar = true;
					}
				}
			}
		}

		/*
		 * Imprimindo o vetor novamente
		 */
		for (int i = 0; i < vet.length; i++) {
			System.out.println(vet[i]);
		}

		/*
		 * Colocando o vetor em ordem crescente
		 */

		for (int i = 0; i < vet.length - 1; i++) {
			int maior = vet[0];
			int posMaior = 0;
			for (int j = 0; j < vet.length -i; j++) {
				if (vet[j] > maior) {
					maior = vet[j];
					posMaior = j;
				}
			}
			int aux = vet[vet.length -i -1];
			vet[vet.length -i -1] = maior;
			vet[posMaior] = aux;
		}
		
		System.out.println("\nVetor ordenado\n");

		/*
		 * Imprimindo o vetor novamente
		 */
		for (int i = 0; i < vet.length; i++) {
			System.out.println(vet[i]);
		}
	}
}
