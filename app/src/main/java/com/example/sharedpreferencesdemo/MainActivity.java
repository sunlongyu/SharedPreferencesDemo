package com.example.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonCommit;
    private String accStr;
    private String pwdStr;
    private EditText editTextAccount;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAccount = findViewById(R.id.account_tv);
        editTextPassword = findViewById(R.id.password_tv);
        buttonCommit = findViewById(R.id.commit_bt);

        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        String account = sharedPreferences.getString("account", "");
        String password = sharedPreferences.getString("password", "");
        editTextAccount.setText(account);
        editTextPassword.setText(password);


        //监听输入，改变按钮状态
        watchButtonStatus(editTextAccount, editTextPassword);

        buttonCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    /**
     * 登录方法
     */
    private void login() {
        accStr = editTextAccount.getText().toString();
        pwdStr = editTextPassword.getText().toString();
        if (accStr.equals("admin") && pwdStr.equals("123456")) {
            //（参数一：文件名，参数2：存储方式）
            SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //(参数一：存储的字段名，参数二：要储存的值)
            editor.putString("account", accStr);
            editor.putString("password", pwdStr);
            editor.apply();
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 监听输入框，改变按钮enable
     *
     * @param editTextAccount  账户输入框
     * @param editTextPassword 密码输入框
     */
    private void watchButtonStatus(final EditText editTextAccount, final EditText editTextPassword) {
        editTextAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonStatusListen(editTextAccount, editTextPassword);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonStatusListen(editTextAccount, editTextPassword);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void buttonStatusListen(EditText editTextAccount, EditText editTextPassword) {
        accStr = editTextAccount.getText().toString();
        pwdStr = editTextPassword.getText().toString();
        if (accStr.equals("") || pwdStr.equals("")) {
            buttonCommit.setEnabled(false);
        } else {
            buttonCommit.setEnabled(true);
        }
    }
}
