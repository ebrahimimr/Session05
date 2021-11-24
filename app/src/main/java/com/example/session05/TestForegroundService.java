package com.example.session05;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.security.Provider;

public class TestForegroundService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("SportChannel",
                    "Sport Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "SportChannel")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle("Test Foreground Service") // title for notification
                .setContentText("This is Test Session 05")// message for notification
                .setOngoing(true)
                .setAutoCancel(false); // clear notification after click

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        startForeground(65,mBuilder.build());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showToast();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        stopSelf();
        super.onDestroy();
    }

    void showToast(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TestForegroundService.this,"Service is runnig",Toast.LENGTH_SHORT).show();
            }
        }, 3000);

    }
}
