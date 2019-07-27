package rush.mior.teste;

import java.io.IOException;
import java.util.Scanner;

public class B	 {
	
    public static void main(String[] args) throws IOException {
    	Scanner leitor = new Scanner(System.in);
    	int A = leitor.nextInt(); // alunos
    	int C = leitor.nextInt(); // computadores
    	int X = leitor.nextInt(); // queimados por caio
    	int Y = leitor.nextInt(); // não possuem compilador
    	if (A > (C - X - Y - 1)) {
    		if (A >= (C - Y - 1)) {
        		System.out.println("Igor bolado!");
        	} else {
        		System.out.println("Caio, a culpa eh sua!");
        	}
    	} else {
    		System.out.println("Igor feliz!");
    	}
    }
    
}