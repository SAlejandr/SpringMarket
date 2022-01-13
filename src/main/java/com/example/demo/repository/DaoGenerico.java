package com.example.demo.repository;

import java.util.Map;

public interface DaoGenerico<T> {

	
	long contarTodos(Map<String, Object> params);
	
	T crear(T t);
	
	void borrar(Object id);
	
	T buscar (Object id);
	
	T actualizar(T t);
}
