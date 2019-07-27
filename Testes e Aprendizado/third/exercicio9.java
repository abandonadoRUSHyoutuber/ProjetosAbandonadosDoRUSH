package third;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author MIOR
 */

public class exercicio9 {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int[] vet = new int[15];
		
        for (int i=0; i < vet.length; i++) {
        	System.out.println("Digite o " + (i+1) + "º valor: ");
        	vet[i] = leitor.nextInt();
        }
        
        Arrays.sort(vet);
        
        for (int i=0; i < vet.length; i++) {
        	System.out.println(vet[i]);
        }
    }
}
