package rush.algoritmos;

public class matrizSecundaria {
	
	public static void main(String[] args) {
		int[][] mat = new int[8][8];
		
		for (int i=0; i < 8; i++) {
			for (int j=0; j < 8; j++) {
				if (i == j) mat[i][j] = 1;
				else if  ((7-i) == j) mat[i][j] = 1;
				else mat[i][j] = 0;
			}
		}
		
		for (int i=0; i < 8; i++) {
			for (int j=0; j < 8; j++) {
				System.out.print(mat[i][j]);
			}
			System.out.println("  ");
		}
	}
}
