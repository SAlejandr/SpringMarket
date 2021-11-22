package com.example.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;



@Component
public class AccessDeniedHandlerImp implements AccessDeniedHandler {

	private static Logger log = LoggerFactory.getLogger(AccessDeniedHandlerImpl.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			
			log.info(auth.getName()+"Otra vez, Maelo Ruiz"+ request.getRequestURI());
		}
		response.sendRedirect(request.getContextPath()+"/acceso-prohibido");
		
	}

}
