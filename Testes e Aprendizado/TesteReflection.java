import java.lang.reflect.Field;

public class TesteReflection extends RX {

	public static String a = "a";
	public String b = "a";
	
	protected static String aa = "a";
	protected String bb = "a";
	
	private static String aaa = "a";
	private String bbb = "a";
	
	String aaaa = "a";
	String bbbb = "a";
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		
		for (Field m : TesteReflection.class.getFields()) {
			System.out.println(m.getName() + " -- " + m.get(new TesteReflection()));
		}
		
		System.out.println("===");

		for (Field m : TesteReflection.class.getDeclaredFields()) {
			System.out.println(m.getName() + " -- " + m.get(new TesteReflection()));
		}
		
	}

}
