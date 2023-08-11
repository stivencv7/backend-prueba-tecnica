package com.konex.prueba.tecnica.models.dao;


import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.konex.prueba.tecnica.models.entity.Medicamento;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MedicamentoCrudRepositoryImpl implements ICrudRepository<Medicamento> {
	
	/**
	 * Se utiliza la anotación @PersistenceContext para inyectar un EntityManager
	 * se realizan con sultas HQL;
	 */
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Medicamento> findAll() {
		return em.createQuery("select m from Medicamento m",Medicamento.class).getResultList();
	}

	@Override
	public Medicamento findById(Long id) {
		return em.find(Medicamento.class, id);
	}

	@Override
	public Medicamento save(Medicamento medicamento) {
		if(medicamento.getId()!=null && medicamento.getId()>0) {
			em.merge(medicamento);
		}else {
			
			em.persist(medicamento);
		}
		return medicamento;
	}

	@Override
	public void delete(Long id) {
		em.remove(findById(id));
		
	}
	
	@Override
	public List<Medicamento> findAllByNombre(String nombre) {
		return em.createQuery("select m from Medicamento as m where m.nombre like :nombre",Medicamento.class)
				.setParameter("nombre", "%"+nombre+"%")
				.getResultList();
		
	}

	@Override
	public List<Medicamento> findAllByBetween(LocalDate desde, LocalDate hasta) {
		return null;
	}

}
