package leandro.salustriano.bydefault;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Manager {
	
	private Imortal m;
	private File file = null;
	private YamlConfiguration yaml = new YamlConfiguration();

	public Manager(Imortal main) {
		this.m = main;
	}

	public void mkdir(String nameConfig, File path) {
		this.file = new File(path, nameConfig);
		if (!this.file.exists()) {
			this.m.saveResource(nameConfig, false);
		}
	}

	public void loadMessageConfig() {
		try {
			this.yaml.load(this.file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public YamlConfiguration getConfig() {
		return this.yaml;
	}

	public void saveConfig() {
		try {
			this.yaml.save(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
