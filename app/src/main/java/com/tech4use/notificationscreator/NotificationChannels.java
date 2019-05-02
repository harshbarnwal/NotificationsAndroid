package com.tech4use.notificationscreator;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

//creating this class to create different Notification Channels
public class NotificationChannels extends Application {

    //creating different channel ids
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    @Override
    public void onCreate() {
        super.onCreate();

        //creating different notification channels
        createnotificationcannel();
    }

    public void createnotificationcannel() {
        //adding condition for the android version
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //creating channels no. 1
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("This is Channel no. 1");

            //creating channel no. 2
            NotificationChannel channel2 = new NotificationChannel(CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW);
            channel2.setDescription("This is channel no. 2");

            //creating the Notification manager to manage and set the notification
            NotificationManager manager  = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}
