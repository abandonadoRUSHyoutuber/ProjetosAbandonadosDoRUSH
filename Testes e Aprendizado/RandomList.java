import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class RandomList<T> extends ArrayList<T> {
	
	private static final long serialVersionUID = -3659500168194524086L;
	private static final Random random = new Random();
	
	public RandomList(Collection<? extends T> c) {
		this.addAll(c);
	}
	
	public RandomList(T... elements) {
		this.addAll(elements);
	}
	
	@Override
	public boolean add(T element) {
		super.add(random.nextInt(size() + 1), element);
		return true;
	}
	
	@Override
	public boolean addAll(Collection<? extends T> c) {
		c.forEach(element -> this.add(element));
		return true;
	}
	
	public void addAll(T... elements) {
		for (T element : elements)
			this.add(element);
	}
	
}