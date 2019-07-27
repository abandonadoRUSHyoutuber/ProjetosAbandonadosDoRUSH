import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TesteOrdenacaoHashMap {

	public static void main(String[] args) {
		Map<String, Integer> mapa = new LinkedHashMap<>();
		for (int i = 0; i < 500; i++) {
			mapa.put(i + " -> " + Math.random() + " - " + i, i);
		}
		
		for (Entry<String, Integer> facs : mapa.entrySet()) {
			System.out.println(facs.getKey());
		}

	}

}
