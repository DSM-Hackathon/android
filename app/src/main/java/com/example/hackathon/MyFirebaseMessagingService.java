package com.example.hackathon;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        Log.d("TEST", token);
        Intent intent = new Intent(MyFirebaseMessagingService.this, LoginActivity.class);
        intent.putExtra("토큰", token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d("TEST", remoteMessage.getNotification().getBody());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Assuming the message contains a notification, prepare a notification with that data
        if (remoteMessage.getNotification() != null) {
            sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
    }

    private void sendNotification(String title, String body) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

        String channelId = "fcm_default_chanel";
        Uri defaultSoundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getPackageName() + R.raw.bell);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.img_logo2)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setAutoCancel(true)
//                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                ;

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel channel = new NotificationChannel(
                channelId,
                "asdf",
                NotificationManager.IMPORTANCE_DEFAULT
        );

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
//                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build();

        channel.enableVibration(true);
        channel.setSound(defaultSoundUri, audioAttributes);

        notificationManager.createNotificationChannel(channel);

        notificationManager.notify(1 /* ID of notification */, notificationBuilder.build());

        PushCallDisplay(); //TODO [화면 강제로 깨우기 실시]
        PushCallVibrator(); //TODO [진동 수행 실시]
        PushCallSound(); //TODO [알림음 발생 실시]


    }

    public void PushCallDisplay() {
        Log.d("---", "---");
        Log.d("//===========//", "================================================");
        Log.d("", "\n" + "[A_NotiPushSetting > PushCallDisplay() 메소드 : 화면 강제 기상 실시]");
        Log.d("//===========//", "================================================");
        Log.d("---", "---");
        try {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, TAG);
            //wakelock.acquire(5000);
            wakelock.acquire(); //TODO [화면 즉시 켜기]
            wakelock.release(); //TODO [WakeLock 해제]
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PushCallVibrator() {
        Log.d("---", "---");
        Log.d("//===========//", "================================================");
        Log.d("", "\n" + "[A_NotiPushSetting > PushCallVibrator() 메소드 : 진동 발생 실시]");
        Log.d("//===========//", "================================================");
        Log.d("---", "---");
        try {
            /**
             * [메시지를 수신받으면 진동 실행]
             * 1. 진동 권한을 획득해야한다. AndroidManifest.xml
             * 2. Vibrator 객체를 얻어서 진동시킨다
             */
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000); //TODO [miliSecond, 지정한 시간동안 진동]
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PushCallSound() {
        Log.d("---", "---");
        Log.d("//===========//", "================================================");
        Log.d("", "\n" + "[A_NotiPushSetting > PushCallSound() 메소드 : 알림음 재싱 실시]");
        Log.d("//===========//", "================================================");
        Log.d("---", "---");
        try {
            MediaPlayer player = MediaPlayer.create(this, R.raw.bell);
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
