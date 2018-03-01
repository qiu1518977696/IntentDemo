package com.example.qiu.intentdemo.http;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by qiu on 2018/2/27.
 */

public class JsonHttpUtil {



    public void doPost(final String url, final String str, final JsonHttpListener listener)
    {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL httpUrl= new URL(url);
                    HttpURLConnection conn=(HttpURLConnection) httpUrl.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(5000);

                    OutputStream out=conn.getOutputStream();
                    out.write(str.getBytes());

                    InputStreamReader is=new InputStreamReader(conn.getInputStream());

                    BufferedReader bf=new BufferedReader(is);
                    StringBuffer sb=new StringBuffer();
                    String str;
                    while((str=bf.readLine())!=null)
                    {
                        sb.append(str);
                    }
                    System.out.print(sb.toString());
                    if (listener!=null)
                    {
                        listener.onFinish(sb.toString());
                    }




                   /* Message message=Message.obtain();
                    Bundle mbundle=new Bundle();
                    mbundle.putString("json",jstr);
                    message.setData(mbundle);
                    handler.sendMessage(message);*/




                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    listener.onError(e);
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onError(e);
                }
            }
        }).start();
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jstr;*/



    }


}
