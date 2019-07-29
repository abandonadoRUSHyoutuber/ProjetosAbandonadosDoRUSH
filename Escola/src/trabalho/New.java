package trabalho;

import java.util.Scanner;

public class New {

	/**
	
	@Numero 1
	@author Mior
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		System.out.println("Digite um número: ");
		int n = leitor.nextInt();
		int teste = verification(n);
		if (teste == 1) System.out.println("Você digitou um número positivo.");
		else System.out.println("Você digitou um número negativo.");
	}
	
	static int verification(int n) {
		if (n >= 0) return 1;
		else return 0;
	}
	
	----------------------------------------------------------------------------------
	
	@Numero 2
	@author Mior
	
	public static void main(String [] args) {
		Scanner leitor = new Scanner(System.in);
		System.out.println("Digite as horas: ");
		int horas = leitor.nextInt();
		System.out.println("Digite os minutos: ");
		int minutos = leitor.nextInt();
		System.out.println("Digite os segundos: ");
		int segundos = leitor.nextInt();
		int totalSegundos = conversor(horas, minutos, segundos);
		System.out.println("O total em segundos é: " + totalSegundos);
	}
	
	static int conversor(int horas, int minutos, int segundos) {
		int totalSegundos = (horas * 3600) + (minutos * 60) + segundos;
		return totalSegundos;
	}
	
	----------------------------------------------------------------------------------
	
	@Numero 3
	@author Mior
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		System.out.println("Digite sua altura: ");
		double alt = leitor.nextDouble();
		System.out.println("Digite seu sexo M/F: ");
		char sexo = leitor.next().toUpperCase().charAt(0);
		double pesoIdeal = idealWeight(alt,sexo);
		System.out.println("Seu peso ideal é: " + pesoIdeal);
	}
	
	static double idealWeight(double alt, char sexo) {
		double pesoIdeal;
		if (sexo == 'M') pesoIdeal = 72.7 * alt - 58;
		else pesoIdeal = 62.1 * alt - 44.7;
		return pesoIdeal;
	}
	
	*/
	
	public static void main(String[] args) {
		int[] j = new int[5];
		j[4] = 4;
		System.out.println(j[4]);
	}
}
