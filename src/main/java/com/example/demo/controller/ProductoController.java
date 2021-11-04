package com.example.demo.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/producto")
public class ProductoController {

	@GetMapping(value = "/carrito")
	public ModelAndView getLogin(HttpSession session) {

		Boolean logueado = (Boolean) session.getAttribute("logueado");
		if(logueado == null) {
			
			logueado = false;
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("carrito");
		mav.addObject("logueado",logueado);
		return mav;
	}
}
