package leandro.salustriano.recursos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import leandro.salustriano.bydefault.Imortal;

public class ResourcesThreadEvent {
	
	public static ArrayList<Player> fila = new ArrayList<Player>();
	public static ArrayList<Player> camarote = new ArrayList<Player>();
	static boolean isActiveThread = false;
	static boolean isActivedThreadCheckPlayerImortal = false;
	public static Player player1 = null;
	public static Player player2 = null;
	public static Player playerWinner = null;
	public ArrayList<Player> playerListsDeadFK = new ArrayList<Player>();
	public static Imortal m;
	public Resources src;
	String timeConfig;
	String pos1;
	String pos2;
	String espera;
	String saida;
	String camaroteExist;
	static Player playerTop1;
	long waitSecond;
	static long mili;
	static long miliDesistir;

	public ResourcesThreadEvent(Imortal main) {
		m = main;
		this.src = new Resources(main);
	}

	public void restartOnEvent() {
		try {
			for (;;) {
				Thread.sleep(1200000L);
				this.src.restartEvent();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setCooldownAfterDead() {
		if ((player1 != null) && (player2 != null) && ((player1 == this.src.afterPlayerDeadCheckFK) || (player2 == this.src.afterPlayerDeadCheckFK))) {
			this.playerListsDeadFK.add(player1);
			this.playerListsDeadFK.add(player2);
			player1.sendMessage("§5[Imortal]§6O duelo se iniciara dentro de §c" + m.getConfig().getString("Seconds_to_start_duel") + "§6 segundos, prepare-se");
			player2.sendMessage("§5[Imortal]§6O duelo se iniciara dentro de §c"	+ m.getConfig().getString("Seconds_to_start_duel") + "§6 segundos, prepare-se");
			try {
				Thread.sleep(Long.parseLong(m.getConfig().getString("Seconds_to_start_duel") + "000"));
				player1.sendMessage("§5[Imortal]§6Batalhem !!");
				player2.sendMessage("§5[Imortal]§6Batalhem !!");
				this.playerListsDeadFK.remove(player1);
				this.playerListsDeadFK.remove(player2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void threadEventImortal(boolean Active) {
		isActiveThread = Active;
		mili = Long.parseLong(m.getConfig().getString("Seconds_to_join") + "000");
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (ResourcesThreadEvent.isActiveThread) {
					if ((ResourcesThreadEvent.fila.size() >= 1) && (ResourcesThreadEvent.player1 == null) && (ResourcesThreadEvent.player2 != null)) {
						ResourcesThreadEvent.player1 = (Player) ResourcesThreadEvent.fila.get(0);
						ResourcesThreadEvent.fila.remove(ResourcesThreadEvent.player1);
						try {
							ResourcesThreadEvent.this.src.noticeCamarote(ResourcesThreadEvent.camarote,	ResourcesThreadEvent.player1, ResourcesThreadEvent.player2);
							ResourcesThreadEvent.player1.sendMessage("§5[Imortal]§6Prepare-se pois voce e o proximo a batalhar.");
							Thread.sleep(ResourcesThreadEvent.mili);
							if (ResourcesThreadEvent.player1 != null) {
								ResourcesThreadEvent.this.src.teleportPlayer(ResourcesThreadEvent.player1, "pos1");
							}
							if (ResourcesThreadEvent.player2 != null) {
								ResourcesThreadEvent.this.src.teleportPlayer(ResourcesThreadEvent.player2, "pos2");
							}
							ResourcesThreadEvent.this.setCooldownAfterDead();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if ((ResourcesThreadEvent.fila.size() >= 1) && (ResourcesThreadEvent.player1 != null) && (ResourcesThreadEvent.player2 == null)) {
						ResourcesThreadEvent.player2 = (Player) ResourcesThreadEvent.fila.get(0);
						ResourcesThreadEvent.fila.remove(ResourcesThreadEvent.player2);
						try {
							ResourcesThreadEvent.this.src.noticeCamarote(ResourcesThreadEvent.camarote,	ResourcesThreadEvent.player1, ResourcesThreadEvent.player2);
							ResourcesThreadEvent.player2.sendMessage("§5[Imortal]§6Prepare-se pois voce e o proximo a batalhar.");
							Thread.sleep(ResourcesThreadEvent.mili);
							if (ResourcesThreadEvent.player2 != null) {
								ResourcesThreadEvent.this.src.teleportPlayer(ResourcesThreadEvent.player2, "pos2");
							}
							if (ResourcesThreadEvent.player1 != null) {
								ResourcesThreadEvent.this.src.teleportPlayer(ResourcesThreadEvent.player1, "pos1");
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ResourcesThreadEvent.this.setCooldownAfterDead();
					} else if ((ResourcesThreadEvent.fila.size() >= 2) && (ResourcesThreadEvent.player1 == null) && (ResourcesThreadEvent.player2 == null)) {
						ResourcesThreadEvent.player1 = (Player) ResourcesThreadEvent.fila.get(0);
						ResourcesThreadEvent.player2 = (Player) ResourcesThreadEvent.fila.get(1);
						ResourcesThreadEvent.fila.remove(ResourcesThreadEvent.player1);
						ResourcesThreadEvent.fila.remove(ResourcesThreadEvent.player2);

						ResourcesThreadEvent.this.src.noticeCamarote(ResourcesThreadEvent.camarote,	ResourcesThreadEvent.player1, ResourcesThreadEvent.player2);
						ResourcesThreadEvent.player1.sendMessage("§5[Imortal]§6Prepare-se pois voce e o proximo a batalhar.");
						ResourcesThreadEvent.player2.sendMessage("§5[Imortal]§6Prepare-se pois voce e o proximo a batalhar.");
						try {
							Thread.sleep(ResourcesThreadEvent.mili);
							if (ResourcesThreadEvent.player1 != null) {
								ResourcesThreadEvent.this.src.teleportPlayer(ResourcesThreadEvent.player1, "pos1");
							}
							if (ResourcesThreadEvent.player2 != null) {
								ResourcesThreadEvent.this.src.teleportPlayer(ResourcesThreadEvent.player2, "pos2");
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ResourcesThreadEvent.this.setCooldownAfterDead();
					}
					try {
						Thread.sleep(5000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	public void addPlayerFila(Player p) {
		if (camarote.contains(p)) {
			camarote.remove(p);
		}
		if ((!fila.contains(p)) && (p != player1) && (p != player2)) {
			fila.add(p);
			this.src.teleportPlayer(p, "espera");
		} else {
			p.sendMessage("§5[Imortal]§cVoce ja esta participando do evento");
		}
	}

	public void PlayerExit(Player p) {
		if (fila.contains(p)) {
			fila.remove(p);
			p.sendMessage("§5[Imortal]§6Voc� desistiu do evento.");
			this.src.teleportPlayer(p, "saida");
		} else if ((p == player1) || (p == player2)) {
			p.sendMessage("§5[Imortal]§cPara desistir do evento use o comando §6/imortal desistir");
		} else {
			p.sendMessage("§5[Imortal]§cVoce nao esta participando do evento no momento");
		}
	}

	public boolean hasLocateInConfig(Player p) {
		this.pos1 = m.assets.getConfig().getString("position_pos1.world");
		this.pos2 = m.assets.getConfig().getString("position_pos2.world");
		this.saida = m.assets.getConfig().getString("position_saida.world");
		this.espera = m.assets.getConfig().getString("position_espera.world");
		this.camaroteExist = m.assets.getConfig().getString("position_camarote.world");
		if ((this.pos1 == null) || (this.pos2 == null) || (this.saida == null) || (this.espera == null) || (this.camaroteExist == null)) {
			p.sendMessage("§5[Imortal]§cA area de evento ainda nao foi definida.");
			return false;
		}
		return true;
	}

	public void addPlayerCamarote(Player p) {
		if (!camarote.contains(p)) {
			if ((!fila.contains(p)) && (player1 != p) && (player2 != p)) {
				camarote.add(p);
				waitSecond(p);
				this.src.teleportPlayer(p, "camarote");
			} else {
				p.sendMessage("§5[Imortal]§cSaia primeiro do evento digitando §6/imortal sair");
			}
		} else {
			p.sendMessage("§5[Imortal]§cVoce ja esta no camarote do evento.");
		}
	}

	public void threadCheckImortal(boolean actived) {
		isActivedThreadCheckPlayerImortal = actived;
		Thread checkImortal = new Thread(new Runnable() {
			public void run() {
				try {
					while (ResourcesThreadEvent.isActivedThreadCheckPlayerImortal) {
						Thread.sleep(4000L);
						if (ResourcesThreadEvent.this.src.getNameTop1() != null) {
							ResourcesThreadEvent.playerTop1 = Bukkit.getPlayer(ResourcesThreadEvent.this.src.getNameTop1());
							if ((ResourcesThreadEvent.playerTop1 != null) && (ResourcesThreadEvent.m.getConfig().getString("player") != ResourcesThreadEvent.playerTop1.getName()) && (ResourcesThreadEvent.playerTop1.isOnline())) {
								ResourcesThreadEvent.this.src.setTagInName(ResourcesThreadEvent.playerTop1);
							}
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		checkImortal.start();
	}

	public void waitSecond(Player p) {
		this.waitSecond = Long.parseLong(m.getConfig().getString("Seconds_to_wait") + "000");
		this.timeConfig = m.getConfig().getString("Seconds_to_wait");
		try {
			Thread.sleep(1000L);
			p.sendMessage("§5[Imortal]§6Teleportando em §c" + this.timeConfig + "§6 segundos");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(this.waitSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
