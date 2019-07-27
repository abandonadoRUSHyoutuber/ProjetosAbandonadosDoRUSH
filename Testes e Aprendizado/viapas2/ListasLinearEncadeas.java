package viapas2;

import java.util.Scanner;

public class ListasLinearEncadeas {

	// UNDERFLOW = VAZIA
	// 
	
	public static class NODO {
		public String nome;
		public int idade;
		public NODO elo;
	}
	
	public static class DESCRITOR {
		public NODO inicio;
		public NODO fim;
		public int tamanho;
	}
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		DESCRITOR l = new DESCRITOR();
		NODO novo, endereco;
		
		l.inicio = null;
		l.fim = null;
		l.tamanho = 0;
	
		while(true) {
			System.out.println(" ");
			System.out.println("+----------------+");
			System.out.println("|      MENU      |");
			System.out.println("| I - INCLUSÃO I |");
			System.out.println("| R - RETIRADA   |");
			System.out.println("| C - CONSULTA   |");
			System.out.println("| M - MOSTRA     |");
			System.out.println("| F - INCLUSÃO F |");
			System.out.println("+----------------+");
			System.out.println(" ");
			
			char option = leitor.next().toUpperCase().charAt(0);
			switch (option) {
			
			
			/*
			 * ADICIONAR UM ELEMENTO NA LISTA
			 */
				case 'F':
					System.out.println("\nINCLUSAO NO FIM");
					
					System.out.println("Informe o nome: ");
					String nome = leitor.next();
					System.out.println("Informe a idade: ");
					int idade = leitor.nextInt();
					
					novo = new NODO();
					novo.nome = nome;
					novo.idade = idade;
					novo.elo = null;

					if (l.inicio == null) {
						l.inicio = novo;
					} else {
						l.fim.elo = novo;
					}
										
					l.fim = novo;
					l.tamanho++;
					
					break;
					
					
				/*
				 * REMOVER UM ELEMENTO NA LISTA
				 */
				case 'R':
					System.out.println("Informe o nome a ser removido:");
					nome = leitor.next();
					endereco = l.inicio;
					NODO anterior = null;
					while (endereco != null) {
						if (endereco.nome.equals(nome)) {
							break;
						}
						anterior = endereco;
						endereco = endereco.elo;
					}
					if (endereco == null) {
						System.out.println("\n==== Não encontrado!");
					} else {
						if (endereco == l.inicio && endereco == l.fim) {
							l.inicio = l.fim = null;
						} else if (endereco == l.inicio) {
							// Primeiro da lista
							l.inicio = l.inicio.elo;
						} else if (endereco == l.fim) {
							// Ultimos da lista
							l.fim = anterior;
							l.fim.elo = null;
						} else {
							anterior.elo = endereco.elo;
						}
						System.out.println("Informação removida com sucesso.");
						l.tamanho--;
					}
					break;
					
				/*
				 * CONSULTAR UM ELEMENTO NA LISTA
				 */	
				case 'C':
					if (l.inicio == null) {
						System.out.println("\n=====> LISTA VAZIIA");
					} else {
						System.out.println("Digite o nome a ser consultado:");
						nome = leitor.next();
						endereco = l.inicio;
						while (endereco != null) {
							if (endereco.nome.equals(nome)) {
								System.out.println("Existe! Idade: " + endereco.idade );
								break;
							}
							endereco = endereco.elo;
						}
						if (endereco == null) {
							System.out.println("\n==== Não encontrado!");
						}
					}
					break;
					
					
				/*
				 * MOSTRAR ELEMENTOS DA LISTA
				 */
				case 'M':
					if (l.inicio == null) {
						System.out.println("\n=====> LISTA VAZIIA");
					} else {
						endereco = l.inicio;
						while (endereco != null) {
							System.out.println("Nome: " + endereco.nome + " idade: " + endereco.idade);
							endereco = endereco.elo;
						}
					}
					break;
						
				case 'I':
					System.out.println("\nINCLUSAO NO INICIO");
					
					System.out.println("Informe o nome: ");
					nome = leitor.next();
					System.out.println("Informe a idade: ");
					idade = leitor.nextInt();
					
					novo = new NODO();
					novo.nome = nome;
					novo.idade = idade;
					novo.elo = null;

					if (l.inicio == null) { // Lista vazia
						l.inicio = novo;
					} else {
						novo.elo = l.inicio;
						l.inicio = novo;
					}
										
					l.tamanho++;
					
					break;
				default:
					System.err.println("Opção invalida!");
					break;
			}
		}
		
	}
	
}