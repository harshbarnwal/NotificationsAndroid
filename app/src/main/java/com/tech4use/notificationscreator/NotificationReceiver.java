package com.tech4use.notificationscreator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //getting the message and toasting it
        String message = intent.getStringExtra("toastmessage");
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
