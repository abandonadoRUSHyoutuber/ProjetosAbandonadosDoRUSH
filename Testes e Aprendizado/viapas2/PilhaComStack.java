
package viapas2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class PilhaComStack {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);

		// criação da pilha
		Stack<String> pilha = new Stack<>();
		Queue<String> fila = new LinkedList<>();

		int opcao;
		String valor;

		do {
			System.out.println("\n\nMenu\n----");
			System.out.println("1 - Inclusao");
			System.out.println("2 - Consulta");
			System.out.println("3 - Retirada");
			System.out.println("4 - Mostra");
			System.out.println("5 - Esvazia");
			System.out.println("6 - Transferir");
			System.out.println("0 - Fim");
			System.out.print("\nSua opcao : ");
			opcao = teclado.nextInt();

			switch (opcao) {
			case 1:
				System.out.print("\nDigite um valor :  ");
				valor = teclado.next();

				pilha.push(valor);

				System.out.println("\n==> Inclusao efetuada");
				break;
			case 2:
				if (pilha.empty())
					System.out.println("\n==> Pilha vazia");
				else
					System.out.println("Informacao no topo : " + pilha.peek());
				break;
			case 3:
				if (pilha.empty())
					System.out.println("\n==> Underflow");
				else {
					valor = pilha.peek();
					pilha.pop();
					System.out.println("Informacao retirada : " + valor);
				}
				break;
			case 4:
				if (pilha.empty())
					System.out.println("\n==> Pilha Vazia");
				else {
					System.out.println("\n\nConteudo da Pilha:\n");
					// for( String s : pilha )
					// System.out.println(s);
					System.out.println("\nTopo\n");
					for (int i = pilha.size() - 1; i >= 0; i--)
						System.out.println(pilha.get(i));
					System.out.println("\nBase\n");
				}
				break;
			case 5:
				if (pilha.empty())
					System.out.println("\n==> Pilha Vazia");
				else {
					while (!pilha.empty()) {
						System.out.println("Retirando " + pilha.peek() + "...");
						pilha.pop();
					}
				}
				break;
			case 6:
				while (!pilha.empty()) {
					fila.add(pilha.peek());
					pilha.pop();
				}
				break;
			case 7: 
				for (String str : fila) {
					System.out.println(str);
				}
			}
			
				
		} while (opcao != 0);
	}

}
