package rush.algoritmos;

import java.util.Random;

public class ordenar {

	public static void main(String[] args) {
		int[] vet = new int[20];
		Random rd = new Random();
		
		for (int i = 0; i < vet.length; i++) {
			vet[i] = rd.nextInt(20);
		}
		
		for (int i = 1; i < vet.length; i++) {
		    for (int j = 0; j < i; j++) {
		        if (vet[i] < vet[j]) {
		            int temp = vet[i];
		            vet[i] = vet[j];
		            vet[j] = temp;
		        }
		    }
		}
		
		for (int i = 0; i < vet.length; i++) {
			System.out.println(vet[i]);
		}	
	}
}
