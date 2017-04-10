package com.cgn.reservation.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cgn.reservation.Captcha.CaptchaHandler;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class);
	@Autowired
	private CaptchaHandler captchaHandler;

	@RequestMapping(value="/home",method = RequestMethod.GET)
	public ModelMap SendCaptchaImage(@ModelAttribute("model") ModelMap model,HttpServletRequest request,HttpServletResponse resp) throws IOException{

		byte []cageCaptchaGenerate=captchaHandler.getCaptchaToken(request,resp);
		if(cageCaptchaGenerate==null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Cannot Generate the Captcha");
			return null;
		}
        logger.info("ingenerated ");
		model.put("image", Base64.encode(cageCaptchaGenerate));
		return model;
	}

	@RequestMapping(value="/home" ,method = RequestMethod.POST)
	@ResponseBody
	public String validateCaptchaUserInput(@RequestParam("captchaUserInput") String captchaUserInput){

		boolean access=captchaHandler.validate(captchaUserInput);

		if(!access)
			return "fail";

		return "success";
	}

	public CaptchaHandler getCaptchaHandler() {
		return captchaHandler;
	}

	public void setCaptchaHandler(CaptchaHandler captchaHandler) {
		this.captchaHandler = captchaHandler;
	}


}
