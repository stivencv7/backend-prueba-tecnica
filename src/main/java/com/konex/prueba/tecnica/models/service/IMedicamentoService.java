package com.konex.prueba.tecnica.models.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.konex.prueba.tecnica.models.entity.Medicamento;

@Service
public interface IMedicamentoService {
	
	/**
	 * Se crea los servicio de tipo Medicamento
	 * 
	 */
	
	List<Medicamento>findAll();
	Medicamento findById(Long  id);
	Medicamento save (Medicamento medicamento);
	void delete(Long id);
	List<Medicamento>findAllByName(String nombre);
}
