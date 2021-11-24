package com.example.demo.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.demo.pojos.Usuario;
import com.example.demo.service.IUsuarioService;

public class AuthenticationSuccessHandlerImp implements AuthenticationSuccessHandler {

	@Autowired
	private IUsuarioService usuarioService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		HttpSession session = request.getSession();
		
		Usuario usuario = usuarioService.buscarPorEmail(userDetails.getUsername());
		
		session.setAttribute("username", usuario.getEmail());
		session.setAttribute("nombre", usuario.getNombre());
		session.setAttribute("idUsuario", usuario.getId());
		
		//handle(request, response, authentication);
		
		boolean isCliente = false;
		boolean isAdmin = false;
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("Cliente")) {
				isCliente = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("Admin")) {
				isAdmin = true;
				break;
			}
		}
		
		String targetUrl;
		if (isCliente) {
			targetUrl = "/usuario/perfil";
		} else if (isAdmin) {
			targetUrl = "/index";
		} else {
			throw new IllegalStateException();
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

}
