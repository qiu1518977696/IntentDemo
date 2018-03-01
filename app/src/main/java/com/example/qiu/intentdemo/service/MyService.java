package com.example.qiu.intentdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by qiu on 2018/1/21.
 */

public class MyService extends Service {

    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent!=null){
        double yuwen=intent.getDoubleExtra("yuwen",0);
        double shuxue=intent.getDoubleExtra("shuxue",0);
        double yinyu=intent.getDoubleExtra("yinyu",0);

        double avg=inAvg(yuwen,shuxue,yinyu);
        }
        Log.i("TEST","6666666");

        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {
        Log.i("TEST","bangding");
        return new MyBinder();
    }
    public double inAvg(double...score)
    {
        int count = score.length;
        if (count == 0) {
            return 0;
        }
        double sum = 0;
        for (double s : score) {
            sum += s;
        }
        return sum / count;
    }

    public class MyBinder extends Binder {
        public double myAvg(double...scores) {
            int count = scores.length;
            if (count == 0) {
                return 0;
            }
            double sum = 0;
            for (double s : scores ) {
                sum += s;
            }
            return sum / count;
        }
    }
}

