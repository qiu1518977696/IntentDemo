package com.example.qiu.intentdemo.http;

import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by qiu on 2018/1/18.
 */

public class HttpLoginThred extends Thread {

    private String name,pass,url;
    public HttpLoginThred(String name,String pass,String url)
    {

        this.name=name;
        this.pass=pass;
        this.url=url;

    }
    public void run() {

//doGet();
        JSONObject js=doPost();
        super.run();
    }
    private void doGet()
    {
        url=url+"?name="+name+"&pass="+pass;
        try {
            URL httpUrl= new URL(url);
            try {
                HttpURLConnection conn=(HttpURLConnection) httpUrl.openConnection();
                conn.setRequestMethod("GET");
                conn.setReadTimeout(1000);
                InputStreamReader sr=new InputStreamReader(conn.getInputStream());
                BufferedReader reader=new BufferedReader(sr);
                String str;
                StringBuffer sb=new StringBuffer();
                while ((str=reader.readLine())!=null)
                {
                    sb.append(str);
                }

                System.out.print(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    private JSONObject doPost()
    {
        try {
            URL httpUrl=new URL(url);

            try {
                HttpURLConnection conn=(HttpURLConnection) httpUrl.openConnection();
                conn.setRequestMethod("POST");
                conn.setReadTimeout(5000);
                OutputStream out=conn.getOutputStream();
                String content="name="+name+"&pass="+pass;
                out.write(content.getBytes());
                BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb=new StringBuffer();
                String str;
                while((str=reader.readLine())!=null)
                {
                    sb.append(str);
                }
                System.out.print(sb.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
return null;
    }
}
