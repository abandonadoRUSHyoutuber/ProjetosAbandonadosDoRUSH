package viapas;

import java.util.Random;
import java.util.Scanner;

public class PesquisaDeDados {

	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int length = leitor.nextInt();
		int vet[] = new int[length];
		for (int i = 0; i < vet.length; i++) 
			vet[i] = new Random().nextInt();
		
	}
	
	//private static int log2p(double num) {
		//return (int) Math.ceil(Math.log(num) / Math.log(2));
	//}
}
