package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojos.Usuario;
import com.example.demo.repository.UsuarioDao;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioDao dao;

	@Override
	public int guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		return dao.save(usuario);
	}

	@Override
	public List<Usuario> lsitarTodos() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Usuario buscarPorId(long id) {
		// TODO Auto-generated method stub
		Optional<Usuario> optional = dao.findById(id);

		return optional.orElse(new Usuario(-1, "", "", "", "", LocalDate.now()));

	}

	@Override
	public Usuario buscarPorEmail(String email) {
		// TODO Auto-generated method stub

		Optional<Usuario> optional = dao.findByEmail(email);

		return optional.orElse(new Usuario(-1, "", "", "", "", LocalDate.now()));
	}

	@Override
	public Usuario borrarPorId(long id) {
		// TODO Auto-generated method stub
		
		Optional<Usuario> optional = dao.findById(id);
		
		if(optional.isPresent()) {
			
			dao.delete(optional.get());
		}

		return optional.orElse(new Usuario(-1, "", "", "", "", LocalDate.now()));
	}

}
