import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TesteClasseVoid {
	
	private int id = 0;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TesteClasseVoid other = (TesteClasseVoid) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public static void main(String[] args) {
		for (Method m : Object.class.getMethods()) {
			System.out.print(Modifier.toString(m.getModifiers()) + " " + m.getReturnType().getSimpleName() + " " + m.getName() + "(");
			int i = 0;
			for (Class<?> c : m.getParameterTypes()) {
				if (c.equals(m.getParameterTypes()[m.getParameterTypes().length -1])) {
					System.out.print(c.getSimpleName() + " arg" + i++);
				} else {
					System.out.print(c.getSimpleName() + " arg" + i++ + ", ");
				}
			}
			System.out.println(")\n");
		}
				
		for (Method m : Void.class.getDeclaredMethods()) {
			System.out.println(m.getName() + "(" + m.getParameterTypes() + ")");
		}
		
		TesteClasseVoid v = new TesteClasseVoid();
	}

}
