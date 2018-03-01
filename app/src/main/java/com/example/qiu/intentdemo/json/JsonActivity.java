package com.example.qiu.intentdemo.json;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.qiu.intentdemo.R;

/**
 * Created by qiu on 2018/1/21.
 */

public class JsonActivity extends Activity {
    private TextView tv_json;
    private Button bt_getjson,bt_setjson;
    private JsonObject json1;
    private String str;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        tv_json=(TextView)findViewById(R.id.tv_json);
        bt_getjson=(Button)findViewById(R.id.bt_getjson);
        bt_setjson=(Button)findViewById(R.id.bt_setjson);
        json1=new JsonObject();
        final String getstr=json1.getJsonstr();
        String jsonstr=json1.getJsonstr();
         str=json1.parserJson2(jsonstr);
        bt_getjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv_json.setText(getstr);
            }
        });
        bt_setjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tv_json.setText(str);
            }
        });

    }
}
