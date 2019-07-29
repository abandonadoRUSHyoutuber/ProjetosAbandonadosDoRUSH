package br.itensespeciais.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import br.itensespeciais.main.Main;

public class GiveEspecial implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if (c.getName().equalsIgnoreCase("giveespecial")) {
			if(s.hasPermission("despeciais.give")) {
				if(!s.isOp()) {
					s.sendMessage("�cVoc� n�o tem permiss�o para isto.");
					return true;
				}
			}
			if(args.length != 3) {
				s.sendMessage("�cUse '/giveespecial <item> <jogador> <quantidade>");
				return true;
			}

			Player target = Bukkit.getPlayer(args[1]);
			
			if(target == null) {
				s.sendMessage("�cEste jogador est� offline!");
				return true;
			}
			
			/// OBJETOS
			Player enviar = target;
			int qtd = Integer.valueOf(args[2]); 
					
			if(args[0].equalsIgnoreCase("capsula")) {
				ItemStack item = Main.capsula;
				item.setAmount(qtd);
				enviar.getInventory().addItem(item);
				
				s.sendMessage("�aVoc� enviou " + qtd + " capsula de voo para " + enviar.getName() + ".");
				enviar.sendMessage("�aVoc� recebeu " + qtd + " capsula de voo.");
				return true;
			} 
			
			if(args[0].equalsIgnoreCase("lancador")) {
				ItemStack item = Main.lancador;
				item.setAmount(qtd);
				enviar.getInventory().addItem(item);
				
				s.sendMessage("�aVoc� enviou " + qtd + " lancador para " + enviar.getName() + ".");
				enviar.sendMessage("�aVoc� recebeu " + qtd + " lancador.");
				return true;
			} 
			
			if(args[0].equalsIgnoreCase("raiomestre")) {
				ItemStack item = Main.raiomestre;
				item.setAmount(qtd);
				enviar.getInventory().addItem(item);
				
				s.sendMessage("�aVoc� enviou " + qtd + " raio mestre para " + enviar.getName() + ".");
				enviar.sendMessage("�aVoc� recebeu " + qtd + " raio mestre.");
				return true;
			} 
			
			if(args[0].equalsIgnoreCase("podermaximo")) {
				ItemStack item = Main.podermaximo;
				item.setAmount(qtd);
				enviar.getInventory().addItem(item);
				
				s.sendMessage("�aVoc� enviou " + qtd + " poder m�ximo para " + enviar.getName() + ".");
				enviar.sendMessage("�aVoc� recebeu " + qtd + " poder m�ximo.");
				return true;
			} 
			
			if(args[0].equalsIgnoreCase("poderinstantaneo")) {
				ItemStack item = Main.poderinstantaneo;
				item.setAmount(qtd);
				enviar.getInventory().addItem(item);
				
				s.sendMessage("�aVoc� enviou " + qtd + " poder instant�neo para " + enviar.getName() + ".");
				enviar.sendMessage("�aVoc� recebeu " + qtd + " poder instant�neo.");
				return true;
			} 
			
			if(args[0].equalsIgnoreCase("armadilha")) {
				ItemStack item = Main.armadilha;
				item.setAmount(qtd);
				enviar.getInventory().addItem(item);
				
				s.sendMessage("�aVoc� enviou " + qtd + " armadilha para " + enviar.getName() + ".");
				enviar.sendMessage("�aVoc� recebeu " + qtd + " armadilha.");
				return true;
			} 
			
			if(args[0].equalsIgnoreCase("silktouch")) {
				ItemStack item = Main.silktouch;
				item.setAmount(qtd);
				enviar.getInventory().addItem(item);
				
				s.sendMessage("�aVoc� enviou " + qtd + " silk touch 2 para " + enviar.getName() + ".");
				enviar.sendMessage("�aVoc� recebeu " + qtd + " silk touch 2.");
				return true;
			} 
			
			if(args[0].equalsIgnoreCase("fragmentodepoder")) {
				ItemStack item = Main.fragmentodepoder;
				item.setAmount(qtd);
				enviar.getInventory().addItem(item);
				
				s.sendMessage("�aVoc� enviou " + qtd + " fragmento de poder m�ximo para " + enviar.getName() + ".");
				enviar.sendMessage("�aVoc� recebeu " + qtd + " fragmento de poder m�ximo.");
				return true;
			} 
			
			if(args[0].equalsIgnoreCase("creepereletrizado")) {
				ItemStack item = Main.creepereletrico;
				item.setAmount(qtd);
				enviar.getInventory().addItem(item);
				
				s.sendMessage("�aVoc� enviou " + qtd + " ovo de creeper eletrizado para " + enviar.getName() + ".");
				enviar.sendMessage("�aVoc� recebeu " + qtd + " ovo de creeper eletrizado.");
				return true;
			} 
			
			if(args[0].equalsIgnoreCase("purificador")) {
				ItemStack item = Main.purificador;
				item.setAmount(qtd);
				enviar.getInventory().addItem(item);
				
				s.sendMessage("�aVoc� enviou " + qtd + " purificador para " + enviar.getName() + ".");
				enviar.sendMessage("�aVoc� recebeu " + qtd + " purificador.");
				return true;
			} 
			return true;
		} return false;
	}
}
