import java.util.Scanner;

public class CONTESTA {
	
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		long N = leitor.nextLong();
		String str = "";
		for (long i = 0; i < N; i++) {
			str += leitor.next();
		}
		long abertos = 0;
		for (char c : str.toCharArray())
		{
			if (c == '(')
			{
				abertos++;
			}
			if (c == ')')
			{	
				if (abertos <= 0) {
					System.out.println("no");
					return;
				}
				abertos--;
			}
		}
		
		if (abertos != 0) {
			System.out.println("no");
		} else {
			System.out.println("yes");
		}
	}

}