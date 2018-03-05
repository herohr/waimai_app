package com.example.herodoc.study;

import android.app.Application;

import com.models.User;

/**
 * Created by herohr on 2018/3/3.
 */

public class MainApplication extends Application{
    private boolean login;
    private String authorization;
    private com.models.User user;

    @Override
    public void onCreate() {
        super.onCreate();
        setLogin(false);
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
