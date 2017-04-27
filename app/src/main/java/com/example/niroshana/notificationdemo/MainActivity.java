package com.example.niroshana.notificationdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNnotificationManager;
    private int notificationID = 100;
    private int numMessages = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = (Button) findViewById(R.id.start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayNotification();
            }
        });

        Button btnCancel = (Button) findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNotification();
            }
        });
        Button btnUpdate = (Button) findViewById(R.id.update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNotification();
            }
        });
    }
    protected void displayNotification()
    {
        Log.i("Start","notification");

        /*Invoking the default notification service*/

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("New Message");
        mBuilder.setContentText("You've receivec new message.");
        mBuilder.setTicker("New Message Alert.!");
        mBuilder.setSmallIcon(R.drawable.android_test);

        /*Increasing notification number every time a new notification arrives*/

        mBuilder.setNumber(++numMessages);

        /*Creates an explicit intent for an Activity in your app*/
        Intent resultIntent = new Intent(this,NotificationView.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationView.class);

        /*Adds the Intent that starts the Activity to the top of the stack*/

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);

        mNnotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        /*notification ID allows you to update the notification later on*/

        mNnotificationManager.notify(notificationID,mBuilder.build());

    }
    protected void cancelNotification()
    {
        Log.i("Cancel","notification");
        mNnotificationManager.cancel(notificationID);
    }
    protected void updateNotification(){
        Log.i("update","notification");
        /*Invoking the default notification service*/
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("Update Notification");
        mBuilder.setContentText("You've updated Notification");
        mBuilder.setTicker("Update Message Aleart");
        mBuilder.setSmallIcon(R.drawable.android_test);

        /*Incerease notification number every time*/
        mBuilder.setNumber(++numMessages);
        /*Creates an axplicit intent for an Activity in your app*/

        Intent resultIntent = new Intent(this,NotificationView.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        stackBuilder.addParentStack(NotificationView.class);
          /* Adds the Intent that starts the Activity to the top of the stack */

        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        mNnotificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNnotificationManager.notify(notificationID,mBuilder.build());




    }
}
