
package com.cgn.reservation.config;

import com.cgn.reservation.Captcha.CaptchaHandler;
import com.cgn.reservation.Captcha.CaptchaValidator;
import com.cgn.reservation.Captcha.CaptchaValidator;
import com.github.cage.Cage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
		* Created by ThilinaP on 1/12/2016.
 */
@Configuration
@ComponentScan(basePackages="CaptchaSpring")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public CaptchaHandler getCaptchaHandler(){
		return new CaptchaHandler();
	}
	@Bean
	public Cage getCage(){
		return new Cage();
	}

	@Bean
	public CaptchaValidator captchaValidator(){
		return new CaptchaValidator();
	}
	
}
