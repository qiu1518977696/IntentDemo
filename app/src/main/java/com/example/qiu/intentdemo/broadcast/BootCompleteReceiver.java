package com.example.qiu.intentdemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by qiu on 2018/1/26.
 */

public class BootCompleteReceiver extends BroadcastReceiver {

    private static final String TAG="BootCompleteReceiver";
    public void onReceive(Context context, Intent intent) {
        Intent service=new Intent(context,BroadcastActivity.class);
        context.startActivity(service);
        Log.i(TAG,"打开页面");

    }
}
