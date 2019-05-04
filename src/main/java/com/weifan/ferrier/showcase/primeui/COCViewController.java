package com.weifan.ferrier.showcase.primeui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public interface COCViewController {
	
	default public String cocView() {
		var req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		var anno = this.getClass().getAnnotation(RequestMapping.class);
		var name = anno.value()[0];
		return name + req.getAttribute("___springMVCMethodName");
	}
	
	public static class CocHandlerInterceptor implements HandlerInterceptor{
		
		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
			// TODO Auto-generated method stub
			HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
		}
		
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			
			if(handler instanceof HandlerMethod) {
				var methodHandler = (HandlerMethod)handler;
				request.setAttribute("___springMVCMethodName", methodHandler.getMethodAnnotation(GetMapping.class).value()[0]);
			}
			return HandlerInterceptor.super.preHandle(request, response, handler);
			
		}
		
	}
	
	
}
