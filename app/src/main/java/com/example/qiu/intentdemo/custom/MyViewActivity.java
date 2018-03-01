package com.example.qiu.intentdemo.custom;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.qiu.intentdemo.R;

/**
 * Created by qiu on 2018/2/25.
 */

public class MyViewActivity extends Activity {
    private MyView mview;
    private TextView tv_bili;
    private Boolean BUTTON_STATE=false;
    private Button bt_close,bt_go;
    private static final int MSG_UPDATE=0x001;
    private Handler mhandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            int progress=mview.getProgress();
            mview.setProgress(++progress);
            if(progress>=10000)
            {
                mhandler.removeMessages(MSG_UPDATE);
            }
            int a=mview.getProgress();
            tv_bili= (TextView) findViewById(R.id.tv_bili);
            String str=""+a+"/100";
            tv_bili.setText(str);
            if (a==100)
            {
                bt_go.setEnabled(false);
            }
            mhandler.sendEmptyMessageDelayed(MSG_UPDATE,100);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myview);



        mview=(MyView)findViewById(R.id.mview);
        mhandler.sendEmptyMessage(MSG_UPDATE);
        bt_close= (Button) findViewById(R.id.bt_close);
        bt_go= (Button) findViewById(R.id.bt_go);
        bt_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!BUTTON_STATE)
                {
                    bt_close.setText("开启");
                    bt_go.setEnabled(false);
                    BUTTON_STATE=!BUTTON_STATE;
                }
                else
                {
                    bt_close.setText("关闭");
                    bt_go.setEnabled(true);
                    BUTTON_STATE=!BUTTON_STATE;
                }

            }
        });


    }
}
