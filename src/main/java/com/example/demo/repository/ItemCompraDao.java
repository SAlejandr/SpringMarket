package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Compra;
import com.example.demo.pojos.ItemCompra;


public interface ItemCompraDao extends DaoGenerico<ItemCompra>{

		
	public List<ItemCompra> findAllByCompra(Compra c);
	
	
}
