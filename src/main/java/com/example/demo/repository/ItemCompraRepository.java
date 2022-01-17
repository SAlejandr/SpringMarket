package com.example.demo.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Compra;
import com.example.demo.pojos.ItemCompra;

@Repository
public class ItemCompraRepository extends DaoRepository<ItemCompra> implements ItemCompraDao{

	@Override
	public List<ItemCompra> findAllByCompra(Compra c) {
		
		Query query = this.em.createQuery("from ItemCompra i where i.id.idCompra = :compra");
		query.setParameter("compra", c);
		
		return query.getResultList();
	}

}
