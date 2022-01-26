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
import com.example.demo.pojos.IdItemCompra;
import com.example.demo.pojos.ItemCompra;
import com.example.demo.pojos.Producto;
import com.example.demo.pojos.Tarjeta;
import com.example.demo.pojos.Usuario;
import com.example.demo.repository.UsuarioDao;
import com.example.demo.service.ICompraService;
import com.example.demo.service.IProductoService;
import com.example.demo.service.IUsuarioService;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ICompraService compraService;
	
	@Autowired
	private IProductoService productoService;
	

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
		if (u.getId() == -1L) {
			
			Usuario usuario = new Usuario(nombre, apellido, contrasenna, email, fecha);
		
			usuarioService.guardar(usuario);
			
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
		Long id =(Long) session.getAttribute("idUsuario");
		
		if(id == null) {
			id = -1L;
		}
		
		if(id==-1) {
			return "redirect:/usuario/login";
		} 
		@SuppressWarnings("unchecked")
		ArrayList<ProductoDTO> l = (ArrayList<ProductoDTO>) session.getAttribute("carrito");
		if(l==null) {
			return "redirect:/";
		}
		HashSet<ProductoDTO> p = new HashSet<>();
		l.stream().forEach(p::add);
		HashSet<ItemCompra> lista = new HashSet<>();
		l.stream().forEach(it -> {
			
			Producto pro = productoService.buscarPorId(it.getId());
			
			IdItemCompra idIt = new IdItemCompra();
			idIt.setIdProducto(pro);
			
			ItemCompra item = new ItemCompra(idIt, it.getCantidad());
			
			lista.add(item);
			
		});
		Usuario u = usuarioService.buscarPorId(id);
		Compra c = new Compra(u, LocalDateTime.now());
		compraService.guardarCompra(c, lista);
		session.setAttribute("carrito", null);
		
		return "redirect:/usuario/perfil/"+id.longValue()+"/compras";
		
	}
	
	@GetMapping(value = "/perfil/{id}/compras/{compra}")
	public String delete(@PathVariable long id, @PathVariable long compra) {
		
		Compra c = compraService.compraPorId((Long)compra);
		
		
		List<ItemCompra> articulos = compraService.listarArticulos(c);
		
		articulos.stream().forEach(it -> compraService.borrarUnArticulo(it) );
		
		c.setBorrado(true);
		
		compraService.actualizar(c);
		
		return "redirect:/usuario/perfil/"+id+"/compras";	
		
	}
	@GetMapping(value = "/reinicio")
	public String todosVisibles() {
		
		compraService.todosVisibles();
		
		return "redirect:/index";	
	}
}
