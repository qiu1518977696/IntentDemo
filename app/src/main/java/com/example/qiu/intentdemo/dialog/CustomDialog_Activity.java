package com.example.qiu.intentdemo.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiu.intentdemo.R;

/**
 * Created by qiu on 2018/2/26.
 */

public class CustomDialog_Activity extends Activity{

    final Context context=this;
    private TextView nopass;
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        nopass= (TextView) findViewById(R.id.nopass);
        nopass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li=getLayoutInflater().from(context);
                View dialogView=li.inflate(R.layout.activity_dialog,null);

                AlertDialog.Builder alertDiaLog=new AlertDialog.Builder(context);

                alertDiaLog.setView(dialogView);

                final EditText et_phone= (EditText) dialogView.findViewById(R.id.et_phone);
                final EditText et_yanzhen= (EditText) dialogView.findViewById(R.id.et_yanzhen);
                final Button bt_fasong= (Button) dialogView.findViewById(R.id.bt_fasong);
                final Button bt_ok= (Button) dialogView.findViewById(R.id.bt_ok);

                alertDiaLog.setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"66666666",Toast.LENGTH_SHORT);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog1=alertDiaLog.create();
                alertDialog1.show();

            }
        });

    }
}
