package rush.four;

import java.util.Scanner;

public class exercicio6 {
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int[] A = new int[10];
		int[] B = new int[10];
		int[] C = new int[10];
		int S = 0;
		
		for (int i=0; i < A.length; i++) {
			System.out.println("#A Informe um número: ");
			A[i] = leitor.nextInt();
			System.out.println("#B Informe um número: ");
			B[i] = leitor.nextInt();
		}
		
		int j = B.length;
		for (int i=0; i < A.length; i++) {
			j--;
			C[i] = A[i] / B[i];
			S += A[i] * B[j];	
		}
		
		System.out.println("");
		System.out.println("--------------------------");
		System.out.println("   R E S U L T A D O S    ");
		System.out.println("--------------------------");
		System.out.println("");
		for (int i=0; i < C.length; i++) {
			System.out.println("C "+ i + " = " +  C[i]);
		}
		
		System.out.println("Valor total de S: " + S);
		System.out.println("Números pares do vetor A: ");
		for (int i=0; i < A.length; i++) {
			if(A[i] % 2 == 0) System.out.println(A[i]);
		}
	}

}
