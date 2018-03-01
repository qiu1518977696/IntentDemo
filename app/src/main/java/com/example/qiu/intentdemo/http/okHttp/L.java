package com.example.qiu.intentdemo.http.okHttp;

/**
 * Created by qiu on 2018/1/30.
 */
import android.util.Log;

/**
 * Created by qiu on 2018/1/19.
 */

public class L {
    private static final String TAG="qiu";
    private static boolean debug=true;
    public static void e(String msg)
    {
        if(debug)
            Log.e(TAG,msg);

    }
}
