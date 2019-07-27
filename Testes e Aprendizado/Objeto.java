import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Objeto {

	Object a;
	Object b;
	Object c;
	Object d;
	Object e;
	Object f;
	Object h;
	Object i;
	Object k;
	List<Object> m;
	Set<Double> x;
	Random rnd = new Random();
	String[] ss;
	Map<Double, Integer> map;
	Objeto l;
	Objeto n;
	Locale locale = Locale.getDefault();
	PrintStream o = System.out;
	Scanner scan;
	System gv;

	Objeto(Object a, Object b, Object c, Object d, Object e, Object f, Object h, Object i, Object k) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.h = h;
		this.i = i;
		this.k = k;
		this.m = new ArrayList<>();
		this.m.addAll(Arrays.asList(a, b, c, d, e, f, h, i, k));
		this.x = new HashSet<>();
		for (int ii = 0; ii < 20; ii++) {
			x.add(rnd.nextDouble());
		}
		ss = new String[40];
		for (int z = 0; z < ss.length; z++) {
			ss[z] = String.valueOf(rnd.nextGaussian());
		}
		this.map = new HashMap<>();
		for (int g = 0; g < 30; g++) {
			map.put(Double.valueOf(rnd.nextGaussian()), Integer.valueOf(rnd.nextInt()));
		}
		this.l = new Objeto();
		this.n = new Objeto();
	}
	
	public Objeto() {
		this(null,null,null,null,null,null,null,null,null);
	}

	public Objeto(String a) {
		this.a = a;
	}
	
	@Override
	public String toString() {
		return this.a.toString();
	}
	
	
}
