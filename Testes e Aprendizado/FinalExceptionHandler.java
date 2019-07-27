import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;

public class FinalExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread thread, final Throwable ex) {
		new Thread() {
			@Override
			public void run() {
				try {
					File file = new File("D:\\log.log");
					FileWriter arquivo = new FileWriter(file);
				    PrintWriter gravador = new PrintWriter(new FileWriter(file));
				    ex.printStackTrace(gravador);
				    arquivo.close();
				    gravador.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}