package viapas2;

import java.util.ArrayList;
import java.util.Scanner;

public class Alunos {

	public static void main(String[] args) {
		ArrayList<String> alunos = new ArrayList<>();
		while (true) {
			printMenu();
			char option = readChar();
			switch (option) {
				case 'I':
					System.out.println("Informe o nome do aluno: ");
					alunos.add(readNext());
					System.out.println("Aluno adicionado com sucesso!");
					break;
				case 'R':
					System.out.println("Informe o nome do aluno: ");
					if (alunos.remove(readNext())) 
						System.out.println("Aluno removido com sucesso!");
					else
						System.err.println("Aluno inexistente!");
					break;
				case 'M':
					if (alunos.size() == 0)
						System.err.println("Nenhum aluno esta cadastrado no sistema!");
					else 
						for (int i = 0; i < alunos.size(); i++)
							System.out.println("Aluno " + (i+1) + " : " + alunos.get(i));
					break;
				case 'F':
					System.out.println("Finalizando sistema...");
					return;
			}
		}
	}
	
	private static void printMenu() {
		System.out.println(" ");
		System.out.println("+----------------+");
		System.out.println("|      MENU      |");
		System.out.println("| I - INCLUSÃO   |");
		System.out.println("| R - RETIRADA   |");
		System.out.println("| M - MOSTRA     |");
		System.out.println("| F - FIM        |");
		System.out.println("+----------------+");
		System.out.println(" ");
	}
	
	private static char readChar() {
		Scanner leitor = new Scanner(System.in);
		char c = leitor.next().toUpperCase().charAt(0);
		while (c != 'I' && c != 'R' && c != 'M' && c != 'F') {
			System.err.println("Opção invalida, por favor redigite!");
			c = leitor.next().toUpperCase().charAt(0);
		}
		return c;
	}
	
	private static String readNext() {
		Scanner leitor = new Scanner(System.in);
		String aluno = "";
		while (aluno.isEmpty()) 
			aluno = leitor.nextLine();
		return aluno;
	}
}
