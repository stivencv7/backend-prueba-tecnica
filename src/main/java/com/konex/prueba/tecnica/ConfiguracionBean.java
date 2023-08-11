package com.konex.prueba.tecnica;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.konex.prueba.tecnica.models.dao.ICrudRepository;
import com.konex.prueba.tecnica.models.dao.MedicamentoCrudRepositoryImpl;
import com.konex.prueba.tecnica.models.entity.Medicamento;

@Configuration
public class ConfiguracionBean {
	
	@Bean("medicamento_dao")
	public ICrudRepository<Medicamento> medicamentoDao() {
		return new MedicamentoCrudRepositoryImpl();
	}

}
