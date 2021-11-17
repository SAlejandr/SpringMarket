package com.example.demo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.ProductoDTO;

@Controller
@RequestMapping(value = "/producto")
public class ProductoController {

	@GetMapping(value = "/carrito")
	public ModelAndView getLogin(HttpSession session) {

		Boolean logueado = (Boolean) session.getAttribute("logueado");
		if (logueado == null) {

			logueado = false;
		}

		@SuppressWarnings("unchecked")
		ArrayList<ProductoDTO> carrito = (ArrayList<ProductoDTO>) session.getAttribute("carrito");
		if (carrito == null) {
			carrito = new ArrayList<>();
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("carrito");
		mav.addObject("logueado", logueado);
		mav.addObject("carro", carrito);
		return mav;
	}

	@PostMapping(value = "/carrito/add")
	public String postMethodName(@RequestParam long id, @RequestParam String nombre, @RequestParam int nProducto, @RequestParam float precio,
			HttpSession session) {
		
		@SuppressWarnings("unchecked")
		ArrayList<ProductoDTO> carrito = (ArrayList<ProductoDTO>) session.getAttribute("carrito");
		if (carrito == null) {
			carrito = new ArrayList<>();
		}
		
		ProductoDTO dto = new ProductoDTO(id, nombre, nProducto, precio);
		
		if(carrito.contains(dto)) {
			int cantidad = carrito.get(carrito.lastIndexOf(dto)).getCantidad() + dto.getCantidad();
			dto = carrito.get(carrito.lastIndexOf(dto));
			dto.setCantidad(cantidad);
		}else {
			carrito.add(dto);
		}
		
		session.setAttribute("carrito", carrito);
		
		return "redirect:/producto/carrito";
	}

}
