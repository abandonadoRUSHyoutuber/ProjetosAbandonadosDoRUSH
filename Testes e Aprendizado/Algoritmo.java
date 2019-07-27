import java.util.Scanner;

public class Algoritmo {

	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in); // Criando o scanner
		
		String nomeMaiorNota = ""; // Variavel para salvar o nome do canditado com a maior nota
		double valorMaiorNota = -1; // Variavel para salvar a maior nota
		
		// Laço de repetição pra ler as 10 notas
		for (int i = 0; i < 10; i++) {
			
			// Lendo o nome e a nota do canditado
			System.out.println("Informe o nome do candidato: ");
			String nome = leitor.next();
			System.out.println("Informe a nota do candidato: ");
			double nota = leitor.nextDouble();
			
			// Verificando se a nota é valida
			while (nota < 0 || nota > 10) {
				System.err.println("Nota invalida! Informa um número de 0 a 10");
				nota = leitor.nextInt();
			}
			
			// Verificando se a nota do candidato é maior que a maior nota atual
			if (nota > valorMaiorNota) {
				valorMaiorNota = nota;
				nomeMaiorNota = nome;
			}
			System.out.println(" "); // Só pra pular a linha
		}
		
		// Exibindo
		System.out.println("A maior nota foi de " + nomeMaiorNota + " com total de " + valorMaiorNota);
	}

}
