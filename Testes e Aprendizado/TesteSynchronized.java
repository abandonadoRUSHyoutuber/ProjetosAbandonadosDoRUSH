import java.util.ArrayList;
import java.util.List;

public class TesteSynchronized {

	public synchronized static void main(String[] args) {
		ArrayList<String> lista = new ArrayList<>();
		for (Integer i = 0; i <50; i++) {
			lista.add(i.toString());
		}
		
		System.out.println(lista.toString());
		lista.forEach(i -> { synchronized (lista) {
			lista.remove(i);
			}	
		});
		System.out.println(lista.toString());

	}

}
