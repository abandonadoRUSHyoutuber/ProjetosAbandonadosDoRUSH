import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAccount {

	public static void main(String[] args) {
		
		double fat10 = getFatorial(10);
		
		double resposta1 = Math.pow(Math.pow((fat10 * fat10), 2), Math.pow((fat10 * fat10), 2));
		BigDecimal resposta2 = (new BigDecimal(fat10).multiply(new BigDecimal(fat10)).pow(2)).pow((new BigDecimal(fat10).multiply(new BigDecimal(fat10)).pow(2)).intValue());
		
		System.out.println(resposta1);
		System.out.println(resposta2);
	}
	
	private static double getFatorial(double n) {
		Double fat = 1d;
		for (int i = 1; i <= n; i++)
			fat *= i;
		return fat;
	}

}
