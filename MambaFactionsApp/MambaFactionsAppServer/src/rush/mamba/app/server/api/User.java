package rush.mamba.app.server.api;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.json.JSONObject;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;

public class User implements Runnable {

	private Socket socket;
	private Server server;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	public User(Server server, Socket socket) {
		this.setServer(server);
		this.setSocket(socket);
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
	
	public ObjectOutputStream getOutput() {
		return output;
	}

	public void setOutput(ObjectOutputStream output) {
		this.output = output;
	}

	public ObjectInputStream getInput() {
		return input;
	}

	public void setInput(ObjectInputStream input) {
		this.input = input;
	}

	@Override
	public void run() {
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
			for (;;) {
				JSONObject request = new JSONObject(input.readObject().toString());
				JSONObject answer = new JSONObject();
				switch ((int) request.get("type")) 
				{
					case -1:
						break;
					
					case 0:
						answer.put("type", 0);
						output.writeObject(answer.toString());
						break;
						
					case 1:
						Faction factiom = FactionColl.get().getByName(request.getString("faction"));
						answer.put("type", 1);
						answer.put("sucess", factiom != null);
						output.writeObject(answer.toString());
						break;
						
					case 2:
						Faction faction = FactionColl.get().getByName(request.getString("faction"));
						boolean valid = faction != null;
						answer.put("type", 2);
						answer.put("sucess", valid);
						if (valid) {
							answer.put("lands", faction.getLandCount());
							answer.put("templands", faction.getTempClaims().size());
							answer.put("members", faction.getMembersCount());
							answer.put("maxmembers", faction.getMembersLimit());
							answer.put("membersonline", faction.getOnlinePlayers().size());
							answer.put("power", faction.getPowerRounded());
							answer.put("maxpower", faction.getPowerMaxRounded());
							answer.put("kills", faction.getKills());
							answer.put("deaths", faction.getDeaths());
							answer.put("kdr", faction.getKdrRounded());
							answer.put("desc", faction.getDescription());
							answer.put("motd", faction.getMotd());
							answer.put("attack", faction.isInAttack());
							server.getApp().getUsers().add(this);
						}
						output.writeObject(answer.toString());
						break;
				}
			}
		} catch (Throwable e) {}
	}
	
}