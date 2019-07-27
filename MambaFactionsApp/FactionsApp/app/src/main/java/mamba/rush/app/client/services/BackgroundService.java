package mamba.rush.app.client.services;

import java.io.FileInputStream;
import java.util.Properties;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import org.json.JSONObject;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import mamba.rush.app.client.R;
import mamba.rush.app.client.utils.Log;

public class BackgroundService extends Service {

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket socket;
    private String fac;
    private String ip;

    private static final int port = 56789;
    private static final int delay = 5000;
    private static final File folder;

    static {
        folder = new File(Environment.getExternalStorageDirectory() + File.separator + "factions");
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

   @Override
   public void onCreate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    // Pegando o arquivo com as informações
                    Properties props = new Properties();
                    File file = new File(folder + File.separator + "info.properties");

                    // Verificando se o arquivo existe, se o usuario ja salvou o IP
                    if (!file.exists()) return;

                    // Carregando as informações do arquivo
                    FileInputStream fileInput = new FileInputStream(file);
                    props.load(fileInput);

                    // Verificando se o usuario já salvou o IP e o nome da facção
                    if (props.containsKey("ip") && props.containsKey("fac")) {

                        // Pegando as informações salvas
                        fac = props.getProperty("fac");
                        ip = props.getProperty("ip");

                        // Iniciando a conexão
                        connection: while (true) {
                            try {
                                Thread.sleep(delay);
                                socket = new Socket(ip, port);
                                input = new ObjectInputStream(socket.getInputStream());
                                output = new ObjectOutputStream(socket.getOutputStream());
                                break connection;
                            } catch (Throwable $) {}
                        }

                        // Recebendo a notificação e informando
                        notification: while (true) {
                            try {
                                JSONObject answer = new JSONObject(input.readObject().toString());
                                if (answer.getInt("type") == 3) {
                                    if (answer.getString("faction").equals(fac)) {
                                        switch (answer.getInt("type-notification")) {
                                            case 0:
                                                sendNotificaiton("Facção em ataque!", "Sua facção entrou em ataque!", "Sua facção entrou em ataque.");
                                                break;

                                            case 1:
                                                sendNotificaiton("Acabou o ataque!", "Sua facção saiu de ataque!", "Sua facção saiu de ataque.");
                                                break;

                                            case 2:
                                                sendNotificaiton("Facção desfeita!", "Sua facção saiu de ataque!", "Sua facção saiu de ataque.");
                                                System.out.println("A facção: '" + answer.getString("faction") + "' foi desfeita por: '" + answer.getString("player") + "' !");
                                                break;

                                            case 3:
                                                System.out.print("Reinciando servidor...");
                                                break;
                                        }
                                    }
                                }
                            } catch (Throwable e) {

                                // Reconectando caso a conexão for perdida
                                connection: while (true) {
                                    try {
                                        Thread.sleep(delay);
                                        socket = new Socket(ip, port);
                                        input = new ObjectInputStream(socket.getInputStream());
                                        output = new ObjectOutputStream(socket.getOutputStream());
                                        break connection;
                                    } catch (Throwable $) {}
                                }
                            }
                        }
                    }
                } catch (Throwable e) {
                    Log.create(e);
                }
            }
        }).start();
   }

   private void sendNotificaiton(String title, String text, String ticker) {
       NotificationCompat.Builder builder = new NotificationCompat.Builder(BackgroundService.this)
               .setContentTitle(title)
               .setContentText(text)
               .setTicker(ticker)
               .setSmallIcon(R.drawable.help)
               .setAutoCancel(true)
               .setVibrate(new long[]{100, 250, 100, 500})
               .setLights(100, 500, 100);

       NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
       notificationManager.notify(1, builder.build());
   }

}