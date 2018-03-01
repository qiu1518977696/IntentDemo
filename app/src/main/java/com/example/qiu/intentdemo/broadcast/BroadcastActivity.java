package com.example.qiu.intentdemo.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.qiu.intentdemo.R;

/**
 * Created by qiu on 2018/1/24.
 */

public class BroadcastActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

    }
    public void send(View view)
    {
        Intent intent=new Intent(BroadcastActivity.this,MyBroadcast.class );
        sendBroadcast(intent);

    }
}
