package com.example.demo.controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.example.demo.dto.ProductoDTO;
import com.example.demo.pojos.Compra;
import com.example.demo.pojos.Tarjeta;
import com.example.demo.pojos.Usuario;
import com.example.demo.repository.UsuarioDao;
import com.example.demo.service.ICompraService;
import com.example.demo.service.IUsuarioService;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ICompraService compraService;

	@GetMapping(value = "/login")
	public ModelAndView getLogin(HttpSession session) {

		Boolean logueado = (Boolean) session.getAttribute("logueado");
		if(logueado == null) {
			
			logueado = false;
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("logueado",logueado);
		mav.setViewName("logIn");
		return mav;
	}

	@PostMapping(value = "/login")
	public String postLogin(HttpSession session,@RequestParam String usuario, @RequestParam String contrasenna) {
		
		Boolean logueado = (Boolean) session.getAttribute("logueado");
		if(logueado == null) {
			logueado = false;
		}
		Long user = (Long )session.getAttribute("user");
		if(user == null) {
			user = -1L;
		}
		
		
		Usuario u = usuarioService.buscarPorEmail(usuario);
		String redirectCorrecto = "redirect:/usuario/perfil", redirectIncorrecto = "redirect:/usuario/login";
		
		if (u.getId() != -1) {

			if (contrasenna.equals(u.getContrasenna())) {
				logueado = true;
				user=u.getId();
				session.setAttribute("logueado", logueado);
				session.setAttribute("user",user);
				return redirectCorrecto+"/"+u.getId();
			
			}else
				session.setAttribute("logueado", logueado);
				session.setAttribute("user",user);
				return redirectIncorrecto;
		} else {
			session.setAttribute("logueado", logueado);
			session.setAttribute("user",user);
			return redirectIncorrecto;
		}

	}
	
	@GetMapping(value = "/signup")
	public ModelAndView getRegistrarse(HttpSession session) {

		Boolean logueado = (Boolean) session.getAttribute("logueado");
		if(logueado == null) {
			
			logueado = false;
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("logueado", logueado);
		mav.setViewName("signUp");
		
		return mav;
	}

	/*AAAAAAAAAAAAAAAAAA**/
	@PostMapping(value = "/signup")
	public String postRegistrarse(@RequestParam String nombre,@RequestParam String apellido,@RequestParam String contrasenna, 
			@RequestParam String email, @RequestParam String fNacimiento) {
		
		
		Usuario u  = usuarioService.buscarPorEmail(email);
		String redirectCorrecto = "redirect:/indesx", redirectIncorrecto = "redirect:/usuario/signup";
		
		LocalDate fecha = LocalDate.parse(fNacimiento);
		if (u.getId() == -1) {
			
			Usuario usuario = new Usuario(nombre, apellido, contrasenna, email, fecha);
		
			usuarioService.guardar(usuario);
			
			usuarioService.asignarRolCliente(email, Byte.parseByte("2"));
			
			return redirectCorrecto;
		} else {
			return redirectIncorrecto;
		}
	}
	
	@GetMapping(value = "/perfil/{id}" )
	public ModelAndView getPerfil(@PathVariable Long id,HttpSession session) {

		Boolean logueado = (Boolean) session.getAttribute("logueado");
		if(logueado == null) {
			
			logueado = false;
		}
		ModelAndView mav = new ModelAndView();
		
		Usuario u = usuarioService.buscarPorId(id);
		mav.addObject("usuario", u);
		mav.addObject("logueado", logueado);
		mav.setViewName("perfil");
		return mav;
	}
	
	@GetMapping(value = "/logout")
	public String desloguear(HttpSession session) {
		
		session.invalidate();
		return "redirect:/indesx";
	}
	
	@GetMapping(value = "/perfil/{id}/tarjeta" )
	public ModelAndView gettarjeta(@PathVariable long id,HttpSession session) {

		Boolean logueado = (Boolean) session.getAttribute("logueado");
		if(logueado == null) {
			
			logueado = false;
		}
		ModelAndView mav = new ModelAndView();

		Usuario u = usuarioService.buscarPorId(id);
		mav.addObject("usuario", u);
		mav.addObject("logueado", logueado);
		mav.setViewName("tarjetaForm");
		return mav;
	}
	
	@PostMapping(value = "/perfil/{id}/tarjeta")
	public String postTarjeta(@PathVariable long id, @RequestParam String tarjeta, @RequestParam String titular,
			@RequestParam int cod, @RequestParam String direccion) {

		String redirect = "redirect:/usuario/perfil/" + id + "/tarjeta";
		
		Tarjeta laTarjeta = new Tarjeta(new BigInteger(tarjeta), titular, cod, direccion);
		
		
		Usuario usuario = usuarioService.buscarPorId(id);
		
		if(usuario.getId() != -1) {
			usuarioService.cambiarTarjeta(id, laTarjeta);
		}

		return redirect;

	}
	@GetMapping(value = "/perfil/{id}/compras" )
	public ModelAndView getcompra(@PathVariable long id,HttpSession session) {

		Boolean logueado = (Boolean) session.getAttribute("logueado");
		if(logueado == null) {
			
			logueado = false;
		}
		ModelAndView mav = new ModelAndView();

		
		Usuario u = usuarioService.buscarPorId(id);
		List<Compra> c=  compraService.listarComprasPorUsuario(id);
		
		mav.addObject("usuario", u);
		mav.addObject("compra", c);
		mav.addObject("logueado", logueado);
		mav.setViewName("listaCompras");
		return mav;
	}

	@PostMapping(value="/perfil/compras")
	public String postcompra(HttpSession session) {
		Long id =(Long) session.getAttribute("user");
		
		if(id == null) {
			id = -1L;
		}
		
		if(id==-1) {
			return "redirect:/usuario/login";
		} 
		ArrayList<ProductoDTO> l = (ArrayList<ProductoDTO>) session.getAttribute("carrito");
		HashSet<ProductoDTO> p = new HashSet<>();
		l.stream().forEach(p::add);
		Usuario u = new Usuario();
		u.setId(id);
		Compra c = new Compra(u, p, LocalDateTime.now());
		compraService.guardarCompra(c);
		session.setAttribute("carrito", null);
		return "redirect:/usuario/perfil/"+id+"/compras";
		
	}
	
	@GetMapping(value = "/perfil/{id}/compras/{compra}/{pro}")
	public String delete(@PathVariable long id, @PathVariable long compra, @PathVariable long pro) {
		
		compraService.borrarUnArticulo(compra, pro);
		
		return "redirect:/usuario/perfil/"+id+"/compras";	
		
	}
}
