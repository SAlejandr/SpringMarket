package com.example.demo.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.pojos.Rol;
import com.example.demo.pojos.Tarjeta;
import com.example.demo.pojos.Usuario;
import com.example.demo.repository.TarjetaDao;
import com.example.demo.repository.UsuarioDao;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;




@Transactional
@Service
public class UsuarioService implements IUsuarioService,UserDetailsService {

	@Autowired
	private UsuarioDao dao;
	@Autowired
	private TarjetaDao tarjetaDao;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public int guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		
		usuario.setContrasenna(bCryptPasswordEncoder.encode(usuario.getContrasenna()));
		
		return dao.save(usuario);
	}

	@Override
	public List<Usuario> listarTodos() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Usuario buscarPorId(long id) {
		// TODO Auto-generated method stub
		Optional<Usuario> optional = dao.findById(id);

		return optional.orElse(new Usuario());

	}

	@Override
	public Usuario buscarPorEmail(String email) {
		// TODO Auto-generated method stub

		Optional<Usuario> optional = dao.findByEmail(email);

		return optional.orElse(new Usuario());
	}

	@Override
	public Usuario borrarPorId(long id) {
		// TODO Auto-generated method stub
		
		Optional<Usuario> optional = dao.findById(id);
		
		if(optional.isPresent()) {
			
			dao.delete(optional.get());
		}

		return optional.orElse(new Usuario());
	}

	@Override
	public int cambiarTarjeta(long id, Tarjeta tarjeta) {
		// TODO Auto-generated method stub

		Optional<Usuario> persona = dao.findById(id);
		
		if (persona.isPresent()) {
			Optional<Tarjeta> tarjetica;
			try {
				 tarjetica = tarjetaDao.findById(tarjeta.getNumero());

				
			} catch (EmptyResultDataAccessException e) {
				// TODO: handle exception
				tarjetica = Optional.empty();
			}
				if (tarjetica.isPresent() && persona.get().getNumeroTarjeta() == null) {

					dao.updateTarjeta(id, tarjeta.getNumero());
				} else if (tarjetica.isPresent() && persona.get().getNumeroTarjeta().equals(tarjeta.getNumero())) {

					tarjetaDao.update(tarjeta);
				} else if (tarjetica.isPresent() && !persona.get().getNumeroTarjeta().equals(tarjeta.getNumero())) {
					dao.updateTarjeta(id, tarjeta.getNumero());
				} else {

					tarjetaDao.save(tarjeta);
					dao.updateTarjeta(id, tarjeta.getNumero());
				}
		}

		return 0;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Usuario u = dao.findByEmail(username).get();
		List<Rol> l = dao.findAllRolByUser(u.getId());
		HashSet<Rol> roles = new HashSet<>();
		
		l.stream().forEach(roles::add);
		
		u.setRoles(roles);
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		for (Rol rol : u.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		
		
		return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getContrasenna(),
				grantedAuthorities);
	}

}
