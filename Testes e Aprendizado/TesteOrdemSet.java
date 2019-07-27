import java.util.HashSet;
import java.util.Set;

public class TesteOrdemSet {

	public static void main(String[] args) {
		Set<String> str1 = new HashSet<>();
		Set<String> str2 = new HashSet<>();
		Set<String> str3 = new HashSet<>();

		str3.add("444aax");
		str1.add("111bby");
		str2.add("333ccx");
		str3.add("222acs");
		
		str1.add("444aax");
		str1.add("222acs");
		str2.add("111bby");
		str3.add("333ccx");
		
		str2.add("444aax");
		str1.add("333ccx");
		str2.add("222acs");
		str3.add("111bby");
		
		for (String str : str1) {
			System.out.println(str);
		}
		
		System.out.println(" ");
		
		for (String str : str2) {
			System.out.println(str);
		}
		
		System.out.println(" ");
		
		for (String str : str3) {
			System.out.println(str);
		}
	}

}
