package com.example.qiu.intentdemo.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;

import android.widget.TextView;

import com.example.qiu.intentdemo.MainActivity;
import com.example.qiu.intentdemo.R;



/**
 * Created by qiu on 2018/2/28.
 */

public class AccelerometerActivity extends Activity implements SensorEventListener {
    private SensorManager mSensor;
    private Vibrator mVibrate;
    private Intent intent;
    private TextView tv_show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        tv_show=(TextView)findViewById(R.id.tv_show);
        mSensor=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mVibrate= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);



    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensor.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensor.registerListener(this,mSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        {
            float[] values=event.values;
            if((Math.abs(values[0])>15)||(Math.abs(values[1])>15)||(Math.abs(values[2])>15))
            {
                intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                tv_show.setText("66666");


            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
