package com.example.qiu.intentdemo;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiu.intentdemo.http.HttpLoginThred;
import com.example.qiu.intentdemo.http.JsonHttpListener;
import com.example.qiu.intentdemo.http.JsonHttpUtil;
import com.example.qiu.intentdemo.json.JsonObject;

public class MainActivity extends Activity {
private EditText et_name,et_pass;
    private TextView tv_show1;
    private Button bt_login;
    private String jstr,jstr2;
    private Handler handler= new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            jstr2=msg.obj.toString();
            JsonObject jo=new JsonObject();

            tv_show1.setText(jo.parserJson2(jstr2));

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name=(EditText)findViewById(R.id.et_name);
        et_pass=(EditText)findViewById(R.id.et_pass);
        bt_login=(Button)findViewById(R.id.bt_login);
        tv_show1=(TextView)findViewById(R.id.tv_show1);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://192.168.56.1:8080/intentDemo/servlet/JsonDemo";
                String name=et_name.getText().toString();
                String pass=et_pass.getText().toString();
                //new HttpLoginThred(name,pass,url).start();
                String str="name="+name+"&pass="+pass;
                JsonHttpUtil jhu=new JsonHttpUtil();
               // jstr=jhu.doPost();
                jhu.doPost(url, str, new JsonHttpListener() {
                    @Override
                    public void onFinish(String response) {
                        Message message=new Message();
                        message.obj=response;
                        handler.sendMessage(message);
                        //JsonObject jo=new JsonObject();

                        //tv_show1.setText(jo.parserJson2(response));
                    }

                    @Override
                    public void onError(Exception e) {
                        Message message=new Message();
                        message.obj=e;
                        handler.sendMessage(message);

                    }
                });

                /*Log.i("TTT",jstr);
                JsonObject jo=new JsonObject();
               jstr2=jo.parserJson2(jstr);
                Log.i("TTT",jstr+"11");


                tv_show1.setText(jstr2);*/

               // Toast.makeText(getApplication(),js,Toast.LENGTH_LONG);
            }
        });





    }
}
