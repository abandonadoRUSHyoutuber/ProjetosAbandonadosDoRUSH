package rush.algoritmos;

import java.util.Scanner;

public class fatorial {
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int x = leitor.nextInt();
		int fat = 1;
		for (int i = 1; i < x+1; i++) {
			fat = fat * i;
		}
		System.out.println(fat);
	}
}
