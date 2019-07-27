import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class TesteLinkedList {

	public static void main(String[] args) {
		HashMap<String, Integer> mapa = new HashMap<>();
		for (Integer i = 0; i < 100000; i++) {
			mapa.put(i.toString(), i);
		}
		long l1, l2, l3, l4;
		
		l1 = System.nanoTime();
		ArrayList<Entry<String, Integer>> array = new ArrayList<>(mapa.entrySet());
		l2 = System.nanoTime();
		
		l3 = System.nanoTime();
		LinkedList<Entry<String, Integer>> linked = new LinkedList<>(mapa.entrySet());
		l4 = System.nanoTime();
		
		System.out.println("Tempo de criação ARRAY: " + (l2 - l1));
		System.out.println("Tempo de criação LINKE: " + (l4 - l3));
		System.out.println(" ");
		
		l1 = System.nanoTime();
		Collections.sort(array, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		l2 = System.nanoTime();
		
		l3 = System.nanoTime();
		Collections.sort(linked, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		l4 = System.nanoTime();
		
		System.out.println("Tempo de sort ARRAY: " + (l2 - l1));
		System.out.println("Tempo de sort LINKE: " + (l4 - l3));
		System.out.println(" ");
		
		l1 = System.nanoTime();
		for (Entry<String, Integer> a : array) {
			a.getKey();
			a.getValue();
		}
		l2 = System.nanoTime();
		
		l3 = System.nanoTime();
		for (Entry<String, Integer> a : linked) {
			a.getKey();
			a.getValue();
		}
		l4 = System.nanoTime();
		
		System.out.println("Tempo de percorrimento ARRAY: " + (l2 - l1));
		System.out.println("Tempo de percorrimento LINKE: " + (l4 - l3));
		System.out.println(" ");
	}

}
