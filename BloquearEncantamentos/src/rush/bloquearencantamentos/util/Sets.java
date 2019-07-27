package rush.bloquearencantamentos.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Sets {
	
	private static final Random RANDOM = new Random();	
	
	public static Set<Integer> count(int start, int end) {
		HashSet<Integer> newSet = new HashSet<>();
		for (int i = start; start <= end; i++) 
			newSet.add(i);
		return newSet;
	}
	
	public static <E> Set<E> copyOf(Set<E> set) {
		return new HashSet<>(set);
	}

	public static <E> Set<E> singleton(E element) {
		HashSet<E> newSet = new HashSet<>();
		newSet.add(element);
		return newSet;
	}
	
	@SafeVarargs
	public static <E> Set<E> newHashSet(E... elements) {
		HashSet<E> newSet = new HashSet<>();
		for (E element : elements)
			newSet.add(element);
		return newSet;
	}
	
	@SafeVarargs
	public static <E> Set<E> union(Set<E>... sets) {
		HashSet<E> newSet = new HashSet<>();
		for (Set<E> set : sets) 
			newSet.addAll(set);
		return newSet;
	}
	
	public static <E> E randomValue(Set<E> set) {
		int index = RANDOM.nextInt(set.size());
		int count = 0;
		for (E element : set) 
			if (count++ == index) return element;
		return null;
	}

}