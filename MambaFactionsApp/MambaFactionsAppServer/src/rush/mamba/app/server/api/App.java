package rush.mamba.app.server.api;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

import com.massivecraft.factions.event.EventFactionsDisband;
import com.massivecraft.factions.event.EventFactionsEnteredInAttack;
import com.massivecraft.factions.event.EventFactionsFinishAttack;
import com.massivecraft.massivecore.event.EventMassiveCore;

public class App {

	private Set<User> users = new HashSet<>();
	
	public App() {
		new Thread(new Server(this)).start();
	}

	public Set<User> getUsers() {
		return this.users;
	}
	
	public void sendNotification(EventMassiveCore e, int type) {
		try {
			JSONObject notification = new JSONObject();
			notification.put("type", 3);
			notification.put("type-notification", type);
			
			switch (type) 
			{
				case 0: {
					EventFactionsEnteredInAttack event = (EventFactionsEnteredInAttack) e;
					notification.put("faction", event.getFaction().getName());
					break;
				}
					
				case 1: {
					EventFactionsFinishAttack event = (EventFactionsFinishAttack) e;
					notification.put("faction", event.getFaction().getName());
					break;
				}
				
				case 2: {
					EventFactionsDisband event = (EventFactionsDisband) e;
					notification.put("faction", event.getFaction().getName());
					notification.put("player", event.getMPlayer().getName());
					break;
				}
				
			}
			sendNotification(notification.toString());
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	private void sendNotification(String notification) {
		new Thread() {
			@Override
			public void run() {
				for (User user : users) {
					try {
						user.getOutput().writeObject(notification);
					} catch (Throwable e) {}
				}
			}
		}.start();
	}

}