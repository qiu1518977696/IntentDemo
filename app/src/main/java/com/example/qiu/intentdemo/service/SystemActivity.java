package com.example.qiu.intentdemo.service;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.qiu.intentdemo.R;

/**
 * Created by qiu on 2018/1/23.
 */

public class SystemActivity extends Activity {
    private AudioManager audio;
    private AlarmManager alarm;
    private NotificationManager notificationManager;
    private Context mContext;
    public Button bt_audio,bt_nz;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
        bt_audio=(Button)findViewById(R.id.bt_audio);
        bt_nz=(Button)findViewById(R.id.bt_nz);

        super.getIntent();
        bt_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audio=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
                audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE,AudioManager.FLAG_SHOW_UI);
            }
        });
        bt_nz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notif = new Notification.Builder(SystemActivity.this)
                        .setContentTitle("New mail from " )
                        .setContentText("222222222")

                        .setStyle(new Notification.BigTextStyle()
                                .bigText("aVeryLongString"))
                        .build();
            }
        });
    }


}
