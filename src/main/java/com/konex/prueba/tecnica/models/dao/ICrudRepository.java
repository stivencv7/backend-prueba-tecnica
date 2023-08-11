package com.konex.prueba.tecnica.models.dao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ICrudRepository<T>{
	
	/**
	 * Para un Crud se prodria ralizar con la Interface
	 * CrudRepository<T,ID>,pero para este ejemplo se manejo 
	 * manual utilizando genéricos 
	 */
	
	List<T>findAll();
	T findById(Long  id);
	T save (T t);
	void delete(Long id);
	List<T>findAllByNombre(String nombre);
	List<T> findAllByBetween(LocalDate desde, LocalDate hasta);
}
