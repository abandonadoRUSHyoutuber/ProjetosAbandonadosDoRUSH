package five;

import java.util.Arrays;
import java.util.Scanner;

public class exercicio1 {
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int[] vet = new int[10];
		for(int i=0; i < vet.length; i++) {
			System.out.println("Informe o " + (i+1) + "º número: ");
			vet[i] = leitor.nextInt();
		}
		
		System.out.println("");
		System.out.println("-------------------------------");
		System.out.println("     EXIBINDO VETOR LIDO       ");
		System.out.println("-------------------------------");
		System.out.println("");
		for(int i=0; i < vet.length; i++) {
			System.out.println("Posição " + (i+1) + "º número: " + vet[i]);
		}
		
		Arrays.sort(vet);
		
		System.out.println("");
		System.out.println("-------------------------------");
		System.out.println("EXIBINDO VETOR LIDO EM ORDEM CRESCENTE");
		System.out.println("-------------------------------");
		System.out.println("");
		for(int i=0; i < vet.length; i++) {
			System.out.println("Posição " + (i+1) + "º número: " + vet[i]);
		}
	}
}
