package com.Exception;

/**
 * Created by DEV001 on 2017-01-18.
 */
public class CustomException extends Exception{

    private String errorMsg = "";
    private String errorCode = "";

    public CustomException(String errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return  "{\"ERROR_CODE\":\"" + this.errorMsg 	+ "\"" + ", \"ERROR_MESSAGE\": " + this.errorMsg + "}";
    }
}
