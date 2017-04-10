package com.cgn.reservation.Captcha;

import com.github.cage.Cage;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ThilinaP on 1/12/2016.
 */
public class CaptchaHandler {

    @Autowired
    private Cage cage;
    @Autowired
    private CaptchaValidator captchaValidator;

    public boolean validate(String captchaUserInput){
        return captchaValidator.validate(captchaUserInput);
    }

    public byte[] getCaptchaToken(HttpServletRequest request,HttpServletResponse resp) throws IOException{

        captchaValidator.clearCaptchaSessionToken();            //clean the validator token at the beginning
        HttpSession session = request.getSession();
        String token = cage.getTokenGenerator().next();			//generate token and set token to session (from cage documentation)
        session.setAttribute("captchaToken", token);
        markTokenUsed(session, false);
        captchaValidator.setToken(token);

        /* Extract From CAGE documentation */
        if (token == null || isTokenUsed(session)) {
            return null;
        }
        setResponseHeaders(resp);
        /*-----------------------------------*/
        markTokenUsed(session, true);
        return cage.draw(token);                //return the byte stream
    }

    /* Extract From CAGE documentation */
    public static boolean isTokenUsed(HttpSession session) {
        return !Boolean.FALSE.equals(session.getAttribute("captchaTokenUsed"));
    }

    public static void markTokenUsed(HttpSession session, boolean used) {
        session.setAttribute("captchaTokenUsed", used);
    }
    protected void setResponseHeaders(HttpServletResponse resp) {
        resp.setContentType("image/" + cage.getFormat());
        resp.setHeader("Cache-Control", "no-cache, no-store");
        resp.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        resp.setDateHeader("Last-Modified", time);
        resp.setDateHeader("Date", time);
        resp.setDateHeader("Expires", time);
    }

}
