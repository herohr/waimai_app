package com.example.herodoc.study;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.network.request.NetworkException;
import com.network.request.UserWrapper;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class registerAct1 extends AppCompatActivity {
    ImageView verifyCodeImage;
    String rdm_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register1);

       verifyCodeImage = findViewById(R.id.verifyCodeImage);

       verifyCodeImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               updateVerifyCodeImage();
           }
       });
       Button button = findViewById(R.id.commitButton);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               commit();
           }
       });
    }

    void commit(){
        TextView phoneText = findViewById(R.id.phoneText);
        TextView codeText = findViewById(R.id.verifyCodeText);
        final String phone = phoneText.getText().toString();
        final String code = codeText.getText().toString();
        Thread thread = new Thread(new Runnable() {
        String requestID;
            @Override
            public void run() {
                try {
                    requestID = UserWrapper.verifyCodeSend(phone, code, rdm_code).request_id;
                    Intent intent = new Intent(getApplicationContext(), registerAct2.class);
                    intent.putExtra("phone", phone);
                    intent.putExtra("requestID", requestID);
                    startActivity(intent);
                } catch (IOException e) {
                    Log.e("Register-Commit", "未知IO错误", e);
                    toastError(getApplicationContext(),"未知IO错误");
                } catch (NetworkException e) {
                    if(e.toString().equals("提交次数过多，请稍候一分钟"))
                    Log.e("Register-Commit", "NetworkEXP", e);
                    toastError(getApplicationContext() ,e.toString());
                }
            }
        });
        thread.start();

    }

    void updateVerifyCodeImage(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final UserWrapper.verifyCodeInfoResp codeInfo = UserWrapper.getVerifyCodeInfo();
                    rdm_code = codeInfo.rdm_code;
                    runOnUiThread(new Thread(){
                        public void run(){
                            Picasso.with(getApplicationContext()).load(codeInfo.img_url).into(verifyCodeImage);
                        }
                    });
                } catch (IOException e) {
                    toastError(getApplicationContext() ,"网络连接错误");
                } catch (NetworkException e) {
                    toastError(getApplicationContext() ,"服务器异常");
                }
            }
        });
        thread.start();
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