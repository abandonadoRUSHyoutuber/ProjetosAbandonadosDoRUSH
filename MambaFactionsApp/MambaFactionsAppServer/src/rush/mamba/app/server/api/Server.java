package rush.mamba.app.server.api;

import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	
	private App app;
	
	public Server(App app) {
		this.app = app;
	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	@SuppressWarnings("resource")
	@Override
	public void run() {
		try
		{
			ServerSocket server = new ServerSocket(56789);
			while (true) 
			{
				try 
				{
					Socket client = server.accept();
					new Thread(new User(this, client)).start();
				} 
				catch (Throwable e) 
				{
					e.printStackTrace();
				}
			}
		} 
		catch (Throwable e) 
		{
			e.printStackTrace();
		}		
	}
	
}