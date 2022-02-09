package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.pojos.Imagen;
import com.example.demo.pojos.Producto;
import com.example.demo.repository.ImagenRepository;
import com.example.demo.repository.ProductoRepository;

public class ImagenService implements IImagenService {

	@Autowired
	private ProductoRepository daoProducto;

	@Autowired
	private ImagenRepository dao;

	@Override
	public Imagen encontrarImagen(Long id) {
		// TODO Auto-generated method stub

		Imagen imagen = dao.findById(id).orElse(new Imagen());

		return imagen;
	}

	@Override
	public List<Imagen> listaImagenes() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Imagen guardarImagen(Imagen imagen) {
		// TODO Auto-generated method stub

		try {

			return dao.save(imagen);

		} catch (Exception e) {

			return new Imagen();
		}

	}

	@Override
	public Imagen actualizarImagen(Long id, MultipartFile file) {
		// TODO Auto-generated method stub
		Optional<Producto> producto = daoProducto.findById(id);

		if (!producto.isPresent()) {

			return new Imagen();
		} else {
			try {
				byte[] imagen = file.getBytes();
				
				Producto p = producto.get();
				
				if (p.getImagen() != null) {
					
					Imagen i = p.getImagen();
					
					i.setImagen(imagen);;
					
					p.setImagen(i);
					
					daoProducto.save(p);
					
					return i;
					
				}else {
					Imagen img = new Imagen();
					img.setId(0L);
					img.setNombre("imagen -> "+ p.getTitulo());
					img.setImagen(imagen);
					img.setProducto(p);
					
					p.setImagen(img);
					
					daoProducto.save(p);
					
					return img;
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
				
				return null;
			}
		}

	
	}

}
