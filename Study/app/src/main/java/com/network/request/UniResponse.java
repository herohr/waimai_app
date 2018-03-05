package com.network.request;


abstract class Response{
    Integer code;
}

public class UniResponse<R>{
    private Exception exception;
    private R result;
    public String errReason;
    private Integer code;

    public UniResponse(R result, int code){
        this.result = result;
        this.exception = null;
        this.code = code;
        this.errReason = null;
    }

    public UniResponse(UserWrapper.ErrorReason errorReason, int code){
        this.errReason = errorReason.reason;
        this.code = code;
        this.result = null;
        this.exception = null;
    }

    public UniResponse(Exception exception){
        this.result = null;
        this.exception = exception;
        this.code = null;
        this.errReason = null;
    }

    public boolean isExcept(){
        return exception != null;
    }

    public Exception getException() { return exception ;}

    public boolean isError() { return errReason != null ; }


    public R getResult(){
        return result;
    }

    public Integer getCode(){
        return code;
    }
}
