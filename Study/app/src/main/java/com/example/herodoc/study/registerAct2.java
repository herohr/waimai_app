package com.example.herodoc.study;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.network.request.NetworkException;
import com.network.request.UserWrapper;

import java.io.IOException;

public class registerAct2 extends AppCompatActivity {
    String requestID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register2);

        Button button = findViewById(R.id.registerButton);
        Intent intent = getIntent();

        requestID = intent.getStringExtra("requestID");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });
    }

    void commit(){
        TextView codeMessageText = findViewById(R.id.codeMessageText);
        final String codeMessage = codeMessageText.getText().toString();
        new Thread(){
            @Override
            public void run() {
                try {
                    UserWrapper.ErrorReason reason = UserWrapper.verifyMessageCodeSend(codeMessage, requestID);
                    if(reason != null){
                        if(reason.reason.equals("wrong code")){
                            toastError(getApplicationContext(), "验证码错误");
                        }else{
                            toastError(getApplicationContext(), "未知错误,"+reason.reason);
                        }
                    }else{
                        Intent intent = new Intent(getApplicationContext() ,registerAct3.class);
                        intent.putExtra("requestID", requestID);
                        startActivity(intent);
                    }
                } catch (IOException e) {
                    Log.e("Register-Commit", "未知IO错误", e);
                    toastError(getApplicationContext(),"未知IO错误");
                } catch (NetworkException e) {
                    e.printStackTrace();
                    toastError(getApplicationContext(), e.toString());
                }
            }
        }.start();


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
