package com.example.note.mynote;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.*;
import android.view.View;
import android.widget.*;

public class LoginActivity extends AppCompatActivity {
    private Button login,back;
    private EditText account,password;
    private String accounts,passwords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        login = (Button) findViewById(R.id.login_button);
        back = (Button) findViewById(R.id.login_back);
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accounts = account.getText().toString();
                passwords = password.getText().toString();
                if(accounts.equals("admin")&&passwords.equals("123456")){
                    Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MyActivity.class);
                    setResult(4,intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"账号或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
