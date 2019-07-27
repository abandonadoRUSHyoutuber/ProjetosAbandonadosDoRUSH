import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TesteArraySetPequenaLargaEscala {

	public static void main(String[] args) {
		List<String> lista = new ArrayList<>();
		Set<String> set = new HashSet<>();
		
		long listaInicio = 0, listaFim = 0, setInicio = 0, setFim = 0, contLista = 0, contSet = 0;
		
		for (Integer i = 0; i < 2000; i++) {
			lista.add(i.toString());
			set.add(i.toString());
		}
		
		System.out.println("Iniciando pequeno teste...");

		for (int i = 0; i < 10000; i++) {
			
			listaInicio = System.nanoTime();
			for (String str : lista) {
				str.toString();
			}
			listaFim = System.nanoTime();
			
	
			setInicio = System.nanoTime();
			for (String str : set) {
				str.toString();
			}
			setFim = System.nanoTime();
			
			if ((listaFim - listaInicio) > (setFim - setInicio)) contSet++;
			else contLista++;
		}
		
		System.out.println("Teste pequeno finalizado! Resultados:");
		System.out.println("Set  Venceu: " + contSet);
		System.out.println("List Venceu: " + contLista);
		
		
		listaInicio = listaFim = setInicio = setFim = contLista = contSet = 0;
		
		for (Integer i = 0; i < 1000000; i++) {
			lista.add(i.toString());
			set.add(i.toString());
		}
		
		System.out.println("\nIniciando largo teste...");

		for (int i = 0; i < 10000; i++) {
			
			listaInicio = System.nanoTime();
			for (String str : lista) {
				str.toString();
			}
			listaFim = System.nanoTime();
			
	
			setInicio = System.nanoTime();
			for (String str : set) {
				str.toString();
			}
			setFim = System.nanoTime();
			
			if ((listaFim - listaInicio) > (setFim - setInicio)) contSet++;
			else contLista++;
		}
		
		System.out.println("Teste largo finalizado! Resultados:");
		System.out.println("Set  Venceu: " + contSet);
		System.out.println("List Venceu: " + contLista);
	}

}
