package com.weifan.ferrier.springboot.security.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String index(@RequestParam(value = "error", required = false) String error,
			HttpServletRequest request,ModelMap model) {
		var s = WebAttributes.AUTHENTICATION_EXCEPTION;
		var exception = (Exception)request.getSession().getAttribute(s);
		if("true".equals(error) && exception != null && exception instanceof AuthenticationException) {
			log.info(exception.getMessage());
			model.addAttribute("errorMsg", "用户名或密码错误");
		}
		return "login/form-1/index";
	}
	
	 /**  
     * 用于处理异常的  
     * @return  
     */  
    @ExceptionHandler({BadCredentialsException.class})   
    public String exception(BadCredentialsException e) {
    	//log.info(e.getMessage());
        //System.out.println(e.getMessage());   
        //e.printStackTrace();   
        return "exception";   
    }  

}
