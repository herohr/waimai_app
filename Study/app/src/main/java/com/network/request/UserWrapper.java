package com.network.request;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utils.Security;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class UserWrapper {
    public static verifyCodeInfoResp getVerifyCodeInfo() throws IOException, NetworkException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Consts.root + Consts.getVerifyCodeImage)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        if(response.code() != 200){
            throw new NetworkException("Error at getVerifyCodeInfo, status:"+response.code());
        }
        Gson gson = new Gson();
        Log.d("Network", json);
        return gson.fromJson(json, verifyCodeInfoResp.class);
    }

    public static verifyCodeSendResp verifyCodeSend(String phoneNumber, String code, String rdm_code) throws IOException, NetworkException {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("phone", phoneNumber)
                .add("code", code)
                .add("rdm_code", rdm_code)
                .build();


        Request request = new Request.Builder()
                .url(Consts.root + Consts.sendVerifyCode)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        Gson gson = new Gson();
        if(response.code() == 200){
            return gson.fromJson(response.body().charStream(), verifyCodeSendResp.class);
        }else{
            if(response.code() == 403){
                ErrorReason reason = gson.fromJson(response.body().charStream(), ErrorReason.class);
                throw new NetworkException("错误的验证码--"+reason.reason);
            }
            if(response.code() == 400){
                ErrorReason reason = gson.fromJson(response.body().charStream(), ErrorReason.class);
                if(reason.reason.equals("isv.BUSINESS_LIMIT_CONTROL"))
                throw new NetworkException("提交次数过多，请稍候一分钟");
            }
        }
        throw new NetworkException("未知网络错误");
    }

    public static ErrorReason verifyMessageCodeSend(String code, String requestID) throws IOException, NetworkException {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("code", code)
                .add("request_id", requestID)
                .build();

        Request request = new Request.Builder()
                .put(formBody)
                .url(Consts.root + Consts.sendVerifyMessage)
                .build();

        Response response = client.newCall(request).execute();
        Gson gson = new Gson();

        if(response.code() == 200){
            return null;
        }else{
            if(response.code() != 500){
                return gson.fromJson(response.body().charStream(), ErrorReason.class);
            }else{
                throw new NetworkException("未知服务器错误");
            }
        }
    }

    public static ErrorReason registerLast(String pw, String requestID) throws IOException, NetworkException {
        OkHttpClient client = new OkHttpClient();
        String pwHashed = Security.passwordMD5.hash(pw);
        FormBody formBody = new FormBody.Builder()
                .add("password", pwHashed)
                .add("request_id", requestID)
                .build();

        Request request = new Request.Builder()
                .post(formBody)
                .url(Consts.root + Consts.registerLast)
                .build();

        Response response = client.newCall(request).execute();
        if(response.code() == 200){
            return null;
        }else{
            if(response.code() != 500){
                Gson gson = new Gson();
                return gson.fromJson(response.body().charStream(), ErrorReason.class);
            }else{
                throw new NetworkException("未知服务器异常");
            }
        }
    }

    public static UniResponse<loginResp> login(String phone, String password){
        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("phone", phone)
                .add("password", Security.passwordMD5.hash(password))
                .build();

        Request request = new Request.Builder()
                .url(Consts.root + Consts.login)
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return new UniResponse<>(e);
        }
        Gson gson = new Gson();
        if(response.code() == 200){
            loginResp resp = gson.fromJson(response.body().charStream(), loginResp.class);
            return new UniResponse<>(resp, 200);
        }
        if(response.code() == 500){
            return new UniResponse<>(ErrorReason.err500(), 200);
        }
        ErrorReason errorReason = gson.fromJson(response.body().charStream(), ErrorReason.class);
        return new UniResponse<>(errorReason, response.code());
    }

    public class loginResp extends com.network.request.Response{
        public String authorization;
        public String user_id;
    }


    public class verifyCodeSendResp{
        public String request_id;
    }

    public class verifyCodeInfoResp {
        public String img_url;
        public String rdm_code;
    }

    public static class ErrorReason{
        public String reason;

        static ErrorReason err500(){
            ErrorReason reason = new ErrorReason();
            reason.reason = "SERVER ERROR";
            return reason;
        }
    }
}
