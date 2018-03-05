package com.example.herodoc.study;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.network.request.NetworkException;
import com.network.request.UserWrapper;

import java.io.IOException;

public class registerAct3 extends AppCompatActivity {
    static String requestID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register3);
        final TextView t1 = findViewById(R.id.registerPasswordText);
        final TextView t2 = findViewById(R.id.registerPasswordText2);
        Intent intent = getIntent();
        requestID = intent.getStringExtra("requestID");

        Button button = findViewById(R.id.registerButton);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEqual(t1, t2)){
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                UserWrapper.ErrorReason reason = UserWrapper
                                        .registerLast(t1.getText().toString(), requestID);
                                if(reason != null){
                                    if(reason.reason.equals("wrong code"))
                                    toastError(getApplicationContext(), "错误的验证码");
                                    else{
                                        toastError(getApplicationContext(), "注册成功，欢迎登录校园味");
                                        startActivity(new Intent(getApplicationContext(), loginAct.class));
                                        finish();
                                    }
                                }

                            } catch (IOException e) {
                                toastError(getApplicationContext(), "未知网络错误");
                            } catch (NetworkException e) {
                                e.printStackTrace();
                                toastError(getApplicationContext(), e.toString());
                            }
                        }
                    }.start();
                }else{
                    toastError(getApplicationContext(), "两次密码输入不正确");
                }

            }
        });
    }

    boolean checkEqual(TextView t1, TextView t2){
        return t1.getText().toString().equals(t2.getText().toString());
    }

    void toastError(final Context context, final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

