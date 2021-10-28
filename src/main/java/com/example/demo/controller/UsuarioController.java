package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repository.UsuarioDao;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioDao dao;
	
	@GetMapping(value = "/login")
	public ModelAndView getMethodName() {
		ModelAndView mav =  new ModelAndView();
		
		mav.setViewName("logIn");
		return mav;
	}


}
