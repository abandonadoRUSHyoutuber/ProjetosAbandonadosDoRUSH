package teste.package2;

import java.lang.reflect.Field;

import teste.package1.TesteClasse;

public class TesteClasse23 {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		
		for (Field m : TesteClasse.class.getFields()) {
			System.out.println(m.getName() + " -- " + m.get(new TesteClasse()));
		}
		
		System.out.println("===");

		for (Field m : TesteClasse.class.getDeclaredFields()) {
			System.out.println(m.getName() + " -- " + m.get(new TesteClasse()));
		}
	}

}
