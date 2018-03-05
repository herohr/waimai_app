//package com.network.request;
//
//import java.util.Map;
//
//import okhttp3.FormBody;
//import okhttp3.Response;
//
//
//public class Request{
//    String method;
//    String URL=null;
//    Map<String, String> params;
//    Map<String, String> forms;
//
//
//}
//
//class RequestBuilder{
//    private Request req;
//    private String[] allowedMethod = {"GET", "POST", "PUT", "DELETE"};
//
//    RequestBuilder(){
//        req = new Request();
//
//    }
//    RequestBuilder url(String url){
//        req.URL = url;
//        return this;
//    }
//
//    RequestBuilder method(String method){
//        req.method = method.toUpperCase();
//        return this;
//    }
//
//    RequestBuilder formAdd(String key, String val){
//        req.forms.put(key, val);
//        return this;
//    }
//
//    RequestBuilder paramsAdd(String key, String val){
//        req.params.put(key, val);
//        return this;
//    }
//
//    Request build() throws BuildError {
//        for(String i: allowedMethod){
//            if(!i.equals(req.method))
//                throw new BuildError("Method: "+ req.method + "not allowed");
//        }
//        if(req.URL == null) throw new BuildError("URL not set");
//        okhttp3.Request.Builder req = new okhttp3.Request.Builder();
//        FormBody.Builder formBody = new FormBody.Builder();
//        for(Map.Entry<String, String> entry: this.req.forms.entrySet()){
//            formBody.add(entry.getKey(), entry.getValue());
//        }
//
//    }
//}
//
//class BuildError extends Exception {
//    BuildError(String s){
//        super(s);
//    }
//}
//
//abstract class CallBack{
//    abstract void onResponse(Response response);
//    abstract void onFailed(Exception e);
//
//    void done(Exception e, Response response) throws Exception {
//        if(e==null){
//            onResponse(response);
//        }else {
//            onFailed(e);
//        }
//    }
//}
//
