package com.tech4use.notificationscreator;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static com.tech4use.notificationscreator.NotificationChannels.CHANNEL_1_ID;
import static com.tech4use.notificationscreator.NotificationChannels.CHANNEL_2_ID;

public class MainActivity extends AppCompatActivity {

    public NotificationManagerCompat manager;
    EditText eTitle;
    EditText eMessage;
    String sTitle;
    String sMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //finding ids to set title and message
        eTitle = findViewById(R.id.edtxt_title);
        eMessage = findViewById(R.id.edtxt_message);
        //setting the notification manager
        manager = NotificationManagerCompat.from(this);
    }

    //creating methods to send notification on different channels

    //creating method to send notification on channel 2
    public void shortNotification(View v) {
        sTitle = eTitle.getText().toString();
        sMessage = eMessage.getText().toString();
        //creating notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                CHANNEL_2_ID)
                .setSmallIcon(R.drawable.short_icon)
                .setContentTitle(sTitle)
                .setContentText(sMessage)
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_LOW);
        manager.notify(1, builder.build());
    }

    //creating method to send notification on channel 1
    public void actionNotification(View v) {
        sTitle = eTitle.getText().toString();
        sMessage = eMessage.getText().toString();
        //creating intent and pending intent to opening activity
        Intent intent = new Intent(this, Activity2.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, 0);

        //creating intent and pending intent to open broadcast message
        Intent broadcastintent = new Intent(this, NotificationReceiver.class);
        intent.putExtra("toastmessage", sMessage);
        PendingIntent broadcastpendingintent = PendingIntent.getBroadcast(
                this, 0, broadcastintent, PendingIntent.FLAG_UPDATE_CURRENT);
        //creating notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                CHANNEL_1_ID)
                .setSmallIcon(R.drawable.long_icon)
                .setContentText(sTitle)
                .setContentText(sMessage)
                .setAutoCancel(true)
                .addAction(R.drawable.long_icon, "Harsh", broadcastpendingintent)
                .setColor(Color.RED)
                .setContentIntent(pendingIntent)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        manager.notify(2, builder.build());
    }

    public void longNotification(View view) {
        sTitle = eTitle.getText().toString();
        sMessage = eMessage.getText().toString();
        //creating notification
        Intent intent = new Intent(this, Activity2.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, 0);
        //creating intent and pending intent to open broadcast message
        Intent broadcastintent = new Intent(this, Activity2.class);
        PendingIntent broadcastpendingintent = PendingIntent.getBroadcast(
                this, 0, broadcastintent, PendingIntent.FLAG_UPDATE_CURRENT);
        //creating bitmap large icon for the large icon notification
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.long_icon);
        //creating notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.long_icon)
                .setContentTitle(sTitle)
                .setContentText(sMessage)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setLargeIcon(bitmap)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.short_icon,
                        "expandable", broadcastpendingintent)
                .setAutoCancel(true)
                .setColor(Color.MAGENTA)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.big_text))
                        .setBigContentTitle("Big Content Title")
                        .setSummaryText("Hello"))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        manager.notify(3, builder.build());
    }

    public void longListNotification(View view) {
        sTitle = eTitle.getText().toString();
        sMessage = eMessage.getText().toString();

        //creating intent and pending intent to open an activity
        Intent intent = new Intent(this, Activity2.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, intent, 0);

        //creating the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this
                , CHANNEL_1_ID)
                .setSmallIcon(R.drawable.short_icon)
                .setContentTitle(sTitle)
                .setContentText(sMessage)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("This is 1st line by harsh")
                        .addLine("This is 2nd line by harsh")
                        .addLine("This is 3rd line by harsh")
                        .addLine("This is 4th line by harsh")
                        .addLine("This is 5th line by harsh")
                        .addLine("This is 6th line by harsh")
                        .addLine("This is 7th line by harsh")
                        .setBigContentTitle("Big Content")
                        .setSummaryText("By Harsh"));
        manager.notify(4, builder.build());
    }


    public void bigPictureNotification(View view) {
        sTitle = eTitle.getText().toString();
        sMessage = eMessage.getText().toString();
        //creating the large icon for big picture using the bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.long_icon);
        //creating notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                this, CHANNEL_1_ID)
                .setContentText(sMessage)
                .setContentTitle(sTitle)
                .setSmallIcon(R.drawable.short_icon)
                .setLargeIcon(bitmap)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap)
                        .bigLargeIcon(null))
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        manager.notify(5, builder.build());
    }

    public void mediaNotification(View view) {
        sTitle = eTitle.getText().toString();
        sMessage = eMessage.getText().toString();

        //creating bitmap image to use
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.long_icon);
        //creating notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                this, CHANNEL_1_ID)
                .setContentTitle(sTitle)
                .setContentText(sMessage)
                .setLargeIcon(bitmap)
                .setSmallIcon(R.drawable.short_icon)
                .addAction(R.drawable.dislike, "Dislike", null)
                .addAction(R.drawable.previous, "Previous", null)
                .addAction(R.drawable.pause, "Pause", null)
                .addAction(R.drawable.next, "Next", null)
                .addAction(R.drawable.like, "Like", null)
                .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1, 2, 3))
                .setSubText("Harsh")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);
        manager.notify(6, builder.build());
    }
}