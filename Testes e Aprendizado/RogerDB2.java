import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RogerDB2 {

	public static void main(String[] args) {
		
		Map<String, Map<String, Object>> usuarios = new HashMap<>();
		
		Map<String, Object> roger = new HashMap<>();
		roger.put("nome", "Roger brusa");
		roger.put("nascimento", new Date(2000, 12, 12));
		roger.put("pontos", 15);
		
		Map<String, Object> andy = new HashMap<>();
		andy.put("nome", "Andy caçapa");
		andy.put("nascimento", new Date(1999, 12, 12));
		andy.put("pontos", 10);
		
		usuarios.put("andy", andy);
		usuarios.put("roger", roger);
		
		for (Entry<String, Map<String, Object>> key : usuarios.entrySet()) {
			System.out.println(key + "\n");
		}
	}

}
