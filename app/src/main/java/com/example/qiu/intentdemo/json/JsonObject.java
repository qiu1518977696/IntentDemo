package com.example.qiu.intentdemo.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by qiu on 2018/1/21.
 */

public class JsonObject {
    public String getJsonstr(){
        String str="";
        JSONObject obj=new JSONObject();
        JSONObject obj2=new JSONObject();
        try {
           // obj.put("name","address");
            obj.put("Banlance",68);
            obj2.put("serverInfo",obj);
            /*JSONArray arr=new JSONArray();
            for(int i=0;i<3;i++)
            {
                JSONObject item=new JSONObject();
                item.put("item","第"+(i+1)+"个元素");
                arr.put(item);
            }
            obj.put("list",arr);
            obj.put("count",arr.length());
            obj.put("desc","测试串");*/
            str=obj2.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    return str;
    }
    public String parserJson(String jsonStr)
    {
        String str="";
        try {
            JSONObject obj=new JSONObject(jsonStr);
            String name=obj.getString("name");
            int count=obj.getInt("count");
            String desc=obj.getString("desc");
            str=String.format("%sname=%s\n",str, name);
            str=String.format("%sdesc=%s\n",str, desc);
            str=String.format("%scount=%s\n",str,count );
            JSONArray jarr=obj.getJSONArray("list");
            for(int i=0;i<jarr.length();i++)
            {
                JSONObject jarr_item=jarr.getJSONObject(i);
                String item=jarr_item.getString("item");
                str=String.format("%s\titem=%s",str,item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    return str;
    }
    public String parserJson2(String jsonStr)
    {
        Log.i("TTT",jsonStr+"666");
        String str="";
        try {
            JSONObject obj=new JSONObject(jsonStr);
            JSONObject serverInfo=obj.getJSONObject("serverInfo");
            int Banlance=serverInfo.getInt("Banlance");
            str+=Banlance;


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

}
