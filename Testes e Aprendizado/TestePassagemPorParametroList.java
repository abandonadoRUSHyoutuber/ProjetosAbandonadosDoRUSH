import java.util.ArrayList;
import java.util.List;

public class TestePassagemPorParametroList {

	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) 
			list.add(111111);
		
		printarArray(list);
		modificarArray(list);
		printarArray(list);
	}
	
	private static void printarArray(List<Integer> list) {
		for (int i = 0; i < list.size(); i++)
			System.out.println(list.get(i));
	}
	
	private static void modificarArray(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) 
			list.set(i, 999999);
	}
	
}
