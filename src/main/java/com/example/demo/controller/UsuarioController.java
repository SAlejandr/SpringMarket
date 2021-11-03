package com.example.demo.controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.pojos.Usuario;
import com.example.demo.repository.UsuarioDao;
import com.example.demo.service.IUsuarioService;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioDao dao;
	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping(value = "/login")
	public ModelAndView getLogin() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("logIn");
		return mav;
	}

	@PostMapping(value = "/login")
	public String postLogin(@RequestParam String usuario, @RequestParam String contrasenna) {
		// TODO: process POST request
		Usuario u = usuarioService.buscarPorEmail(usuario);
		String redirectCorrecto = "redirect:/usuario/perfil", redirectIncorrecto = "redirect:/usuario/login";
		if (u.getId() != -1) {

			if (contrasenna.equals(u.getContrasenna()))
				return redirectCorrecto+"/"+u.getId();
			else
				return redirectIncorrecto;
		} else {

			return redirectIncorrecto;
		}

	}
	
	@GetMapping(value = "/signup")
	public ModelAndView getRegistrarse() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("signUp");
		
		return mav;
	}

	/*AAAAAAAAAAAAAAAAAA**/
	@PostMapping(value = "/signup")
	public String postRegistrarse(@RequestParam String nombre,@RequestParam String apellido,@RequestParam String contrasenna, 
			@RequestParam String email, @RequestParam String fNacimiento) {
		//TODO: process POST request
		
		Usuario u  = usuarioService.buscarPorEmail(email);
		String redirectCorrecto = "redirect:/indesx", redirectIncorrecto = "redirect:/usuario/signup";
		
		LocalDate fecha = LocalDate.parse(fNacimiento);
		if (u.getId() == -1) {
			
			Usuario usuario = new Usuario(nombre, apellido, contrasenna, email, fecha);
		
			usuarioService.guardar(usuario);
			
			return redirectCorrecto;
		} else {
			return redirectIncorrecto;
		}
	}
	
	@GetMapping(value = "/perfil/{id}" )
	public ModelAndView getPerfil(@PathVariable long id) {
		ModelAndView mav = new ModelAndView();

		Usuario u = usuarioService.buscarPorId(id);
		mav.addObject("usuario", u);
		
		mav.setViewName("perfil");
		return mav;
	}

}
