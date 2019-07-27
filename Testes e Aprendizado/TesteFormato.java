import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class TesteFormato {

	public static void main(String[] args) {
		System.out.println(fixMoney(242141241451541251L));
		System.out.println(NumberFormat.getInstance().format(342345345634.104D));
	}

	private static String format(double d) {
		NumberFormat format = NumberFormat.getInstance(new Locale("pt", "BR"));
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(0);
		return format.format(d);
	}

	private static String fixMoney(double d) {

		if (d < 1000L) {
			return format(d);
		}
		if (d < 1000000L) {
			return format(d / 1000L) + "k";
		}
		if (d < 1000000000L) {
			return format(d / 1000000L) + "m";
		}
		if (d < 1000000000000L) {
			return format(d / 1000000000L) + "b";
		}
		if (d < 1000000000000000L) {
			return format(d / 1000000000000L) + "t";
		}
		if (d < 1000000000000000000L) {
			return format(d / 1000000000000000L) + "q";
		}

		return String.valueOf(d);
	}
	
}
