package com.konex.prueba.tecnica.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.konex.prueba.tecnica.models.dao.ICrudRepository;
import com.konex.prueba.tecnica.models.entity.Medicamento;

@Service
public class MedicamentoServiceImpl implements IMedicamentoService {

	/**
	 * Se implementa los servicios, 
	 * en esta parte se manejo las transacciones
	 */
	
	@Autowired(required = false)
	@Qualifier("medicamento_dao")
	private ICrudRepository<Medicamento> medicamentoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Medicamento> findAll() {
		return medicamentoDao.findAll();
	}

	@Override
	@Transactional
	public Medicamento findById(Long id) {
		return medicamentoDao.findById(id);
	}

	@Override
	@Transactional
	public Medicamento save(Medicamento medicamento) {
		return medicamentoDao.save(medicamento);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		medicamentoDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Medicamento> findAllByName(String nombre) {
		return medicamentoDao.findAllByNombre(nombre);
	}

	

}
