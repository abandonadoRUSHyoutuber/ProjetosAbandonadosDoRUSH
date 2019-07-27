
public class TestePassagemPorParametroArray {

	public static void main(String[] args) {
		
		int[] array = new int[5];
		for (int i = 0; i < 5; i++) 
			array[i] = 111111;
		
		printarArray(array);
		modificarArray(array);
		printarArray(array);
	}
	
	private static void printarArray(int[] array) {
		for (int i = 0; i < array.length; i++)
			System.out.println(array[i]);
	}
	
	private static void modificarArray(int[] array) {
		for (int i = 0; i < array.length; i++) 
			array[i] = 999999;
	}
	
}
