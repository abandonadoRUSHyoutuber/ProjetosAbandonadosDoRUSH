import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TesteWhileIterator {

	public static void main(String[] args) {
		long t1, t2, t3, t4, t5, t6, t7, t8;
		
		LinkedList<String> strings = new LinkedList<>();
		for (Integer i = 0; i < 52072; i++) {
			strings.add(i.toString());
		}
		
		t1 = System.nanoTime();
		for (String s : strings) {
			//s.isEmpty();
		}
		t2 = System.nanoTime();
		
		t3 = System.nanoTime();
		Iterator<String> it = strings.iterator();
		while (it.hasNext()) {
			String next = it.next();
		}
		t4 = System.nanoTime();
		
		t5 = System.nanoTime();
		strings.forEach(s -> {});
		t6 = System.nanoTime();
		
		t7 = System.nanoTime();
		int size = strings.size();
		for (int i = 0; i < size; i++) {
			String next = strings.get(i);
		}
		t8 = System.nanoTime();
		
		System.out.println("for each: " + (t2 - t1));
		System.out.println("for norm: " + (t8 - t7));
		System.out.println("iterator: " + (t4 - t3));
		System.out.println("lambda f: " + (t6 - t5));
		
		
	}

}
