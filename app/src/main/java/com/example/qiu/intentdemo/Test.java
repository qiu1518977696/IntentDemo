package com.example.qiu.intentdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by qiu on 2018/1/23.
 */

public class Test extends Activity{
    private EditText et_jg;
    private TextView tv_15,tv_17,tv_2,tv_27;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_jisuanqi);
        et_jg=(EditText)findViewById(R.id.et_jg);
        tv_15=(TextView)findViewById(R.id.tv_15);
        tv_17=(TextView)findViewById(R.id.tv_17);
        tv_2=(TextView)findViewById(R.id.tv_2);
        tv_27=(TextView)findViewById(R.id.tv_27);
        et_jg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    String str=et_jg.getText().toString();
                    if(str==null||str=="")
                    {
                        str="0";
                        tv_15.setText("1.5倍：0元");
                        tv_17.setText("最终价格：0元");


                        tv_2.setText("2倍：0元");
                        tv_27.setText("最终价格：0元");

                    }
                    else
                    {
                        double x = Double.parseDouble(str);
                        double y = 1.5 * x;
                        double sum = y / 0.7;

                        tv_15.setText("1.5倍：" + String.format("%.3f", y) + "元");
                        tv_17.setText("最终价格：" + String.format("%.3f", sum) + "元");

                        double i = 2 * x;
                        double j = i / 0.7;
                        tv_2.setText("2倍：" + String.format("%.3f", i) + "元");
                        tv_27.setText("最终价格：" + String.format("%.3f", j) + "元");
                    }
                }catch (Exception e)
                {

                }



            }
        });

    }


}
