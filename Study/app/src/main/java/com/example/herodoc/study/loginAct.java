package com.example.herodoc.study;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.models.User;
import com.network.request.NetworkException;
import com.network.request.UniResponse;
import com.network.request.UserWrapper;

public class loginAct extends AppCompatActivity {
    private Toolbar toolbar;
    TextView phoneText;
    TextView passwordText;

    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.user_login);

        phoneText = findViewById(R.id.phoneText);
        passwordText = findViewById(R.id.pwText);

        TextView registerText = findViewById(R.id.registerText);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), registerAct1.class);
                startActivity(intent);
            }
        });

        View loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(phoneText.getText().toString(), passwordText.getText().toString());
            }
        });
    }

    void login(final String phone, final String password){
        if(phone.length() != 11){
            toastInfo(getApplicationContext() ,"手机号必须为11位");
            return;
        }
        if(password.length() == 0){
            toastInfo(getApplicationContext(), "密码不能为空");
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                UniResponse<UserWrapper.loginResp> resp = UserWrapper.login(phone, password);
                if(resp.isExcept()){
                    Exception e = resp.getException();
                    if(e instanceof NetworkException){
                        toastInfo(getApplicationContext(), e.toString());
                    }else{
                        toastInfo(getApplicationContext(), "未知错误: "+e.toString());
                    }
                    return;
                }
                if(resp.isError()){
                    String reason = resp.errReason;
                    if(reason.equals("Need username and password"))
                        toastInfo(getApplicationContext(), "用户名或密码错误");
                    toastInfo(getApplicationContext(), "未知错误: "+reason);
                }else{
                    UserWrapper.loginResp result = resp.getResult();

                    MainApplication app = (MainApplication) getApplication();
                    app.setAuthorization(result.authorization);
                    User user = new User(result.user_id, phone);
                    app.setUser(user);
                    app.setLogin(true);

                    toastInfo(getApplicationContext(), "登陆成功");
                    startActivity(new Intent(getApplicationContext(), indexAct1.class));
                    finish();
                }
            }
        }).start();

    }

    void toastInfo(final Context context, final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}