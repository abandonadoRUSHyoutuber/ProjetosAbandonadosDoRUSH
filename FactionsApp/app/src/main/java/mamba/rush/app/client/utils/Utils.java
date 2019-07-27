package mamba.rush.app.client.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public final class Utils {

    public static String decodeIp(String IP) {
        return IP
                .toUpperCase()
                .replace('X', '0')
                .replace('A', '1')
                .replace('E', '2')
                .replace('H', '3')
                .replace('J', '4')
                .replace('L', '5')
                .replace('N', '6')
                .replace('P', '7')
                .replace('R', '8')
                .replace('T', '9')
                .replace('-', '.')
                ;
    }

    public static boolean validateIp(String IP) {
       return IP.matches("((1?\\d{1,2}|2([0-4]\\d|5[0-5]))\\.){3}(1?\\d{1,2}|2([0-4]\\d|5[0-5]))");
    }

    public static boolean hasInternet(Activity app) {
        ConnectivityManager manager = (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public static void hideKeyboard(Context context, View editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

/*
    NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
            .setContentTitle("Titulo da notificação!")
            .setContentText("Texto da notificação!")
            .setSubText("Subtexto da notificação!")
            .setTicker("Texto que aparece ao receber a notificação.")
            .setBadgeIconType(R.drawable.error)
            .setSmallIcon(R.drawable.help)
            .setAutoCancel(true)
            .setVibrate(new long[]{ 100, 250, 100, 500 })
            .setLights(100, 500, 100)
            .setPriority(-2);

    NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("Titulo do Inbox Style:");
        for (int i=0; i < 10; i++) {
        inboxStyle.addLine("Linha " + (i+1));
    }
        builder.setStyle(inboxStyle);

    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
*/
}