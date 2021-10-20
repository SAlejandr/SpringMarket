package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.pojos.Producto;

@Controller
public class MainController {
	
	@GetMapping(value = "/indesx")
	public String index(Model modelo) {
		
		return "index";
	}
	
	@GetMapping(value = "/formula")
	public String guardarProd(Model modelo) {
		
		return "fomulario";
	}

	

}
