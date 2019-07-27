import java.util.ArrayList;
import java.util.List;

public class TesteArrayStatic {

	public static List<String> strings = new ArrayList<>();
	
	public static void main(String[] args) {
		String a = "String00";
		strings.add(a);
		for (int i = 1; i <= 100; i++) {
			strings.add("String" + i);
		}
		
		int slot = 10;
		for (String str : strings) {
			System.out.println(slot);
			slot += slot == 34 ? -24 : slot == 16 || slot == 25 ? + 3 : + 1;
		}
	}

}
