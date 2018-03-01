package com.example.qiu.intentdemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by qiu on 2018/1/24.
 */

public class MyBroadcast extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,".....",Toast.LENGTH_LONG).show();
    }
}
