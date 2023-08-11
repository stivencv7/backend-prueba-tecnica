package com.konex.prueba.tecnica.models.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.konex.prueba.tecnica.models.dao.ICrudRepository;
import com.konex.prueba.tecnica.models.entity.Venta;

@Service
public class VentaServiceImpl implements IVentaService {
	
	@Autowired(required = false)
	private ICrudRepository<Venta> service;

	@Override
	@Transactional(readOnly = true)
	public List<Venta> findAll() {
		return service.findAll();
	}

	@Override
	@Transactional
	public Venta findById(Long id) {
		return service.findById(id);
	}

	@Override
	@Transactional
	public Venta save(Venta venta) {
		return service.save(venta);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		service.delete(id);
		
	}

	@Override
	public List<Venta> findAllByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<Venta> findAllByBetween(LocalDate desde, LocalDate hasta) {
		// TODO Auto-generated method stub
		return service.findAllByBetween(desde, hasta);
	}

}
