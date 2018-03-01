package com.example.qiu.intentdemo.storage;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qiu.intentdemo.R;

/**
 * Created by qiu on 2018/1/30.
 */

public class SharedPreferencesActivity extends Activity implements View.OnClickListener
{
    private Button login, cancel;
    private CheckBox checkBox, checkBox2;
    private EditText editName, editPassword;
    //声明一个SharedPreferences对象和一个Editor对象
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreferences);
        login = (Button) findViewById(R.id.login);
        cancel = (Button) findViewById(R.id.cancel);
        editName = (EditText) findViewById(R.id.editName);
        editPassword = (EditText) findViewById(R.id.editPassword);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        //获取preferences和editor对象
        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor = preferences.edit();


        String name = preferences.getString("userName",null);
        if (name == null) {
            checkBox.setChecked(false);
        } else {
            editName.setText(name);
            checkBox.setChecked(true);
        }
        String password = preferences.getString("userPassword", null);
        if (password == null) {
            checkBox2.setChecked(false);
        } else {
            editPassword.setText(password);
            checkBox2.setChecked(true);
        }

        //为login和cancel设置监听事件
        login.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }


    public void onClick(View v) {
        //判断用户是进行的是登陆操作还是取消操作
        switch (v.getId()) {
            case R.id.login:
                String name = editName.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                if (name.equals("admin") && password.equals("123456")) {
                    if (checkBox.isChecked()) {

                        editor.putString("userName", name);
                        editor.commit();
                    } else {

                        editor.remove("userName");
                        editor.commit();
                    }
                    if (checkBox2.isChecked()) {
                        editor.putString("userPassword", password);
                        editor.commit();
                    } else {

                        editor.remove("userPassword");
                        editor.commit();
                    }

                    Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
                } else {

                    editor.remove("userName");
                    editor.remove("userPassword");
                    editor.commit();
                    Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();
                }
                break;
            //若用户选择了取消，则直接退出登录
            case R.id.cancel:
                finish();
        }
    }


    }

