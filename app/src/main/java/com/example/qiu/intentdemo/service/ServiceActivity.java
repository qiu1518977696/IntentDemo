package com.example.qiu.intentdemo.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.qiu.intentdemo.service.MyService;
import com.example.qiu.intentdemo.R;

/**
 * Created by qiu on 2018/1/21.
 */

public class ServiceActivity extends Activity {

    private MyService.MyBinder binder=null;
    private EditText et_yuwen,et_shuxue,et_yinyu;
    private Button bt_avg,bt_jisuan;
    private TextView tv_show;
    private Intent intent;
    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("TEST","binder");
            binder= (MyService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("TEST","no");
        }
    };
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_service);
        et_shuxue=(EditText)findViewById(R.id.et_shuxue);
        et_yinyu=(EditText)findViewById(R.id.et_yinyu);
        et_yuwen=(EditText)findViewById(R.id.et_yuwen);
        bt_avg=(Button)findViewById(R.id.bt_avg);
        bt_jisuan=(Button)findViewById(R.id.bt_jisuan);
        tv_show=(TextView)findViewById(R.id.tv_show);
        Log.i("TEST","open");
        intent=new Intent(ServiceActivity.this,MyService.class);

        // Intent intent=new Intent("qiu.myservice");
        // intent.setPackage("qiu.myservice");
        //bindService(intent,conn,BIND_AUTO_CREATE);
        Log.i("TEST","ok");
        bt_avg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double yuwen=Double.parseDouble(et_yuwen.getText().toString());
                double shuxue=Double.parseDouble(et_shuxue.getText().toString());
                double yinyu=Double.parseDouble(et_yinyu.getText().toString());

                double result= binder.myAvg(yuwen,shuxue,yinyu);
                tv_show.setText(""+result);
            }
        });
        bt_jisuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double yuwen=Double.parseDouble(et_yuwen.getText().toString());
                double shuxue=Double.parseDouble(et_shuxue.getText().toString());
                double yinyu=Double.parseDouble(et_yinyu.getText().toString());
                intent.putExtra("yuwen",yuwen);
                intent.putExtra("shuxue",shuxue);
                intent.putExtra("yinyu",yinyu);
                startService(intent);

            }
        });

    }

}
