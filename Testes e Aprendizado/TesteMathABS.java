
public class TesteMathABS {

	public static void main(String[] args) {
		int xN = arround(-20, true);
		int xP = arround(30, false);
		int zN = arround(-40, true);
		int zP = arround(50, false);
		System.out.println(xN);
		System.out.println(xP);
		System.out.println(zN);
		System.out.println(zP);
		
		int total = ((Math.abs(xN) + xP) * (Math.abs(zN) + zP)) / 100;
		int count = 0;
		for (int x = xN; x < xP; x += 10) {
			for (int z = zN; z < zP; z += 10) {
				System.out.println((++count * 100) / total + "% concluido...");
			}
		}
	}
	
	private static int arround(int number, boolean invert) {
		while (number % 10 != 0) {
			if (invert)
				number--;
			else
				number++;
		}
		return number;
	}
}
