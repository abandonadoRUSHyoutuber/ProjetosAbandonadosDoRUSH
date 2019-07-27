package rush.mamba.app.teste.command;

import java.net.Socket;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import rush.filha.da.puta.mamba.app.stream.InfoInputStream;
import rush.filha.da.puta.mamba.app.stream.InfoOutputStream;
import rush.filha.da.puta.org.json.JSONObject;

public class TestCommand implements CommandExecutor {

	public InfoOutputStream output;

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		if (args[0].equals("iniciar")) {
			new Thread() {
				@Override
				public void run() {
					try {
						Socket cliente = new Socket("localhost", 56789);

						output = new InfoOutputStream(cliente.getOutputStream());
						InfoInputStream input = new InfoInputStream(cliente.getInputStream());

						for (;;) {
							JSONObject answer = new JSONObject(input.readObject().toString());
							switch (answer.getInt("type")) {
								case 0:
									Bukkit.broadcastMessage("Resposta de sucesso recebida do servidor!");
									break;
	
								case 1:
									if (answer.getBoolean("sucess")) {
										Bukkit.broadcastMessage("Resposta de sucesso recebida do servidor! §aFacção EXISTE!");
									} else {
										Bukkit.broadcastMessage("Resposta de sucesso recebida do servidor! §cFacção NÃO EXISTE!");
									}
									break;
	
								case 2:
									if (answer.getBoolean("sucess")) {
										Bukkit.broadcastMessage("Resposta de sucesso recebida do servidor! §aFacção EXISTE!");
									} else {
										Bukkit.broadcastMessage("Resposta de sucesso recebida do servidor! §cFacção NÃO EXISTE!");
									}
									break;
									
								case 3:
									switch (answer.getInt("type-notification")) {
										case 0:
											Bukkit.broadcastMessage("A facção: '" + answer.getString("faction") + "' entrou em ataque!");
											break;
											
										case 1:
											Bukkit.broadcastMessage("A facção: '" + answer.getString("faction") + "' saiu em ataque!");
											break;
											
										case 2:
											Bukkit.broadcastMessage("A facção: '" + answer.getString("faction") + "' foi desfeita por: '" + answer.getString("player") + "' !");
											break;
									}
							}
						}
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}.start();
		} else {

			try {

				int message = Integer.parseInt(args[0]);
				JSONObject request = new JSONObject();
				request.put("type", message);

				if (message == 1 || message == 2) {
					request.put("faction", "teste");
				}

				output.writeObject(request.toString());
				System.out.println("[CLIENTE]: Enviou as informações! Info: " + request);				
			} catch (Throwable e) {
				s.sendMessage("Número invalido! Argumentos Invalidos!");
				e.printStackTrace();
			}
		}

		return true;
	}
}
