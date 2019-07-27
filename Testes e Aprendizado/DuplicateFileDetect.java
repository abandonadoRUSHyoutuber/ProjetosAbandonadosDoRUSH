import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DuplicateFileDetect {

	public static void main(String[] args) throws IOException {
		File root = new File ("C:\\Users\\Mior\\Videos\\Audios");
		List<File> files = new ArrayList<>(Arrays.asList(root.listFiles()));
		Map<File, List<File>> duplicatedFiles = new HashMap<>();
		List<File> removes = new ArrayList<>();
		for (File file : root.listFiles()) {
			for (File compare : files) {
				if (equals(file, compare)) {
					removes.add(file);
					if (duplicatedFiles.containsKey(file) || duplicatedFiles.containsKey(compare)) {
						duplicatedFiles.get(file).add(compare);
					} else {
						duplicatedFiles.put(file, new ArrayList<>(Arrays.asList(compare)));
					}
				}
			}
			if (!removes.isEmpty()) {
				files.removeAll(removes);
				removes.clear();
			}
		}
		int i = 0;
		System.out.println("------------------- Arquivos Duplicados -------------------");
		for (Entry<File, List<File>> entry : duplicatedFiles.entrySet()) {
			System.out.print(++i + ") " + entry.getKey().getName());
			for (File duplicatedFile : entry.getValue()) {
				System.out.print(" - " + duplicatedFile.getName());
			}
			System.out.println(" ");
		}
	}
	
    public static boolean equals(File f1, File f2) {
        byte[] f1_buf = new byte[1048576];
        byte[] f2_buf = new byte[1048576];
        int len;
        if (f1.length() == f2.length()) {
            try {
                InputStream isf1 = new FileInputStream(f1);
                InputStream isf2 = new FileInputStream(f2);
                try {
                    while (isf1.read(f1_buf) >= 0) {
                        len=isf2.read(f2_buf);
                        for (int j = 0; j < len; j++) {
                            if (f1_buf[j] != f2_buf[j]) {
                                return false; // tamanho igual e conteudo diferente
                            }
                        }
                    }
                } catch (IOException e) {
                }
            } catch (FileNotFoundException e) {
            }
        } else {
            return false; // tamanho e conteudo diferente
       }
        return true; // arquivos iguais
    }

}
