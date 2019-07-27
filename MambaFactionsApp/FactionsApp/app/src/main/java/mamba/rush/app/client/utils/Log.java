package mamba.rush.app.client.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Calendar;

public final class Log {

    private static final File folder;

    static {
        folder = new File(Environment.getExternalStorageDirectory() + File.separator + "factions");
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static void create(Throwable e) {
        try {
            File log = new File(folder + File.separator + fileName() + ".txt");
            PrintWriter writer = new PrintWriter(new FileWriter(log));
            e.printStackTrace(writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void create(String... msg) {
        try {
            File log = new File(folder + File.separator + fileName() + ".txt");
            PrintWriter writer = new PrintWriter(new FileWriter(log));
            writer.print(msg);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String fileName() {
        return DateFormat.getDateTimeInstance(3, 2)
                .format(Calendar.getInstance().getTime())
                .replace('/', '-')
                .replace(':', ';');
    }

}