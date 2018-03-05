package com.network.request;

/**
 * Created by HeroDoc on 2018/2/25.
 */

public class Consts {
    public static String Host = "172.20.10.2";
    public static String Port = "8000";
    public static String protocol = "http";

    public static String root = protocol+"://"+Host+":"+Port;

    public static String getVerifyCodeImage = "/verify_code";
    public static String sendVerifyCode = "/verify_code";
    public static String sendVerifyMessage = "/verify_message";
    public static String registerLast = "/users";

    public static String login = "/users/login";

}
