package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.pojos.Usuario;
import com.example.demo.repository.UsuarioDao;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioDao dao;

	@GetMapping(value = "/login")
	public ModelAndView getLogin() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("logIn");
		return mav;
	}

	@PostMapping(value = "/login")
	public String postLogin(@RequestParam String usuario, @RequestParam String contrasenna) {
		// TODO: process POST request
		Optional<Usuario> persona = dao.findByEmail(usuario);
		String redirectCorrecto = "redirect: /indesx", redirectIncorrecto = "redirect: /usuario/login";

		if (persona.isPresent()) {
			Usuario u = persona.get();

			if (contrasenna.equals(u.getContrasenna()))
				return redirectCorrecto;
			else
				return redirectIncorrecto;
		} else {

			return redirectIncorrecto;
		}

	}

}
