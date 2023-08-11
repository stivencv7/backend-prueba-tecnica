package com.konex.prueba.tecnica.models.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.konex.prueba.tecnica.models.entity.Venta;
@Service
public interface IVentaService {
	
	/**
	 * Se crea los servicio de tipo Venta
	 * 
	 */
	List<Venta>findAll();
	Venta findById(Long  id);
	Venta save (Venta venta);
	void delete(Long id);
	List<Venta>findAllByNombre(String nombre);
	List<Venta> findAllByBetween(LocalDate desde, LocalDate hasta);
}
