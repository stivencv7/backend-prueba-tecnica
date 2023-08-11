package com.konex.prueba.tecnica.models.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.konex.prueba.tecnica.models.entity.Venta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class VentaCrudRepositoryImpl implements ICrudRepository<Venta> {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Venta> findAll() {
		return em.createQuery("select v from Venta v",Venta.class).getResultList();
	}

	@Override
	public Venta findById(Long id) {
		 Query nativeQuery=em.createNativeQuery("Select * from ventas where id=:id",Venta.class);
				 nativeQuery.setParameter("id", id);
		 return (Venta) nativeQuery.getSingleResult();
				
	}

	@Override
	public Venta save(Venta venta) {
		if(venta.getId()!=null && venta.getId()>0) {
			em.merge(venta);
		}else {
			em.persist(venta);
		}
		return venta;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(id);
	}

	@Override
	public List<Venta> findAllByNombre(String nombre) {
		return null;
	}
	
	//@Override
	public List<Venta> findAllByBetween(LocalDate desde,LocalDate hasta) {
		return em.createQuery("select v from Venta v where fecha  between :dede and :hasta",Venta.class)
				.setParameter("dede",desde ).setParameter("hasta", hasta)
				.getResultList();
	}

}
