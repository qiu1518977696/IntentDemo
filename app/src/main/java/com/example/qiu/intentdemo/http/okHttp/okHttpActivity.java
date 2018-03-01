package com.example.qiu.intentdemo.http.okHttp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.example.qiu.intentdemo.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Callback;

/**
 * Created by qiu on 2018/1/30.
 */

public class okHttpActivity extends Activity{
    private WebView wv_show;
    private String url="http://192.168.56.1:8080/Demo/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        wv_show=(WebView) findViewById(R.id.wv_show);
    }
    public void doGet(View view) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url + "login?name=qiubin&pass=668899").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                L.e("onFailure:");
                e.printStackTrace();
            }


            public void onResponse(Call call, Response response) throws IOException {
                L.e("onResponse");

                final String date = response.body().string();
                L.e(date);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        wv_show.loadData(date, "text/html;charset=utf-8", null);
                    }
                });

            }
        });
    }}

