package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Compra;
import com.example.demo.pojos.IdItemCompra;
import com.example.demo.pojos.ItemCompra;

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompra, IdItemCompra>{

	@Query("Select i from ItemCompra i where i.id.idCompra = :c")
	public List<ItemCompra> findAllByCompra(Compra c);

}
