package com.weifan.ferrier.springboot.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

public interface COCViewController {
	
	default public String cocView() {
		var req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		var anno = this.getClass().getAnnotation(RequestMapping.class);
		var name = anno.value()[0];
		return name + req.getAttribute("___springMVCMethodName");
	}
	
	@Slf4j
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
			
			log.info(handler.getClass().toString());
			
			if(handler instanceof HandlerMethod) {
				var methodHandler = (HandlerMethod)handler;
				var getMapping = methodHandler.getMethodAnnotation(GetMapping.class);
				String value = null;
				if(getMapping == null) {
					var postMapping = methodHandler.getMethodAnnotation(PostMapping.class);
					if(postMapping != null) {
						value = postMapping.value()[0];
					}
				}else {
					value = getMapping.value()[0];
				}
				request.setAttribute("___springMVCMethodName", value);
			}
			return HandlerInterceptor.super.preHandle(request, response, handler);
			
		}
		
	}
	
	
}
