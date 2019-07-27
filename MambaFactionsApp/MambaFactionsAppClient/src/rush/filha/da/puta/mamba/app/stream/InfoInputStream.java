package rush.filha.da.puta.mamba.app.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class InfoInputStream extends ObjectInputStream {

	public InfoInputStream(InputStream in) throws IOException {
		super(in);
	}

	@Override
	protected void readStreamHeader() throws IOException {

	}

}