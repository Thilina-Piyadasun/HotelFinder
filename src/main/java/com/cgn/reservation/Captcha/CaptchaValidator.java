package com.cgn.reservation.Captcha;

/**
 * Created by ThilinaP on 1/13/2016.
 */
public class CaptchaValidator {

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public boolean validate(String captchaUserInput) {

        if(captchaUserInput==null ||token==null){
            return false;
        }
        if(captchaUserInput.equals("") ||token.equals("")) {
            return false;
        }
        if(captchaUserInput.equals(token)){
            return true;
        }
        return false;
    }

    public void clearCaptchaSessionToken(){
        this.token=null;
    }
}
