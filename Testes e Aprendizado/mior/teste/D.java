package rush.mior.teste;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class D	 {
	
    public static void main(String[] args) throws IOException {
    	Scanner leitor = new Scanner(System.in);
    	int N = leitor.nextInt();
		Stack<Integer> pilha = new Stack<>();
		int menor = -1;
		for (int i = 0; i < N; i++) {
			String command = leitor.next();
			if (command.equalsIgnoreCase("POP")) {
				if (pilha.empty()) {
					System.out.println("EMPTY");
				} else {
					pilha.pop();
					menor = Collections.min(pilha);
				}
			} else if (command.equalsIgnoreCase("MIN")) {
				if (pilha.empty()) {
					System.out.println("EMPTY");
				} else {
					System.out.println(menor);
				}
			} else if (command.equalsIgnoreCase("PUSH")) {
				int V = leitor.nextInt();
				if (menor == -1 || V < menor) {
					menor = V;
				}
				pilha.push(V);
			}
		}
    }
    
}