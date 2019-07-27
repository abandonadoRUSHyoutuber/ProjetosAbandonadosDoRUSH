package mamba.rush.app.client.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import mamba.rush.app.client.services.BackgroundService;

public class BootUpReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent i) {
        //context.startService(new Intent(context, BackgroundService.class));
    }

}