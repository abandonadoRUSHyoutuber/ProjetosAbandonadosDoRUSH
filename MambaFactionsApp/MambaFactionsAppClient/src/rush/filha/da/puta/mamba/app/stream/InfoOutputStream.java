package rush.filha.da.puta.mamba.app.stream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class InfoOutputStream extends ObjectOutputStream {

	public InfoOutputStream(OutputStream out) throws IOException {
		super(out);
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		reset();
	}

}