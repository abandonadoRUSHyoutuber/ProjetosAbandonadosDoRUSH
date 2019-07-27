package viapas;

import java.util.Random;

public class InsertionSort {

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

		for (int k = 0; k < vet.length; k++) {
			System.out.print(vet[k] + " - ");
		}

		for (int i = 1; i < vet.length; i++) {
			int aux = vet[i];
			int j = i - 1;
			while (j >= 0 && aux < vet[j]) {
				vet[j + 1] = vet[j];
				j--;
			}
			vet[j + 1] = aux;
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
