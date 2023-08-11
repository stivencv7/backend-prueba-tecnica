package com.konex.prueba.tecnica.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.konex.prueba.tecnica.models.entity.Medicamento;
import com.konex.prueba.tecnica.models.service.IMedicamentoService;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MedicamentoController {
	
	@Autowired
	private IMedicamentoService service;
	
	@GetMapping("/medicamentos")
	public List<Medicamento>getMedicamentos(){
		return service.findAll();
	}
	
	@GetMapping("/medicamento/{id}")
	public ResponseEntity<?>getMedicamento(@PathVariable Long id){
		
		Medicamento medicamento=null;
		Map<String,Object> response=new HashMap<>();
		try {
			
			medicamento=service.findById(id);
		}catch(DataAccessException e) {
			response.put("error", "Error no se encontraron datos "+ e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(medicamento==null) {
			response.put("mensaje","no se encontraron datos con el id ".concat(id.toString())+"no exite en la base de Datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Medicamento>(medicamento,HttpStatus.OK);
	}
	
	@PostMapping("/medicamento")
	public ResponseEntity<?> registrarProducto(@RequestBody Medicamento medicamento){
		Medicamento newMedicamento=null;
		Map<String, Object>response=new HashMap<>();
		try{
			newMedicamento=service.save(medicamento);
		}catch(DataAccessException e) {
			response.put("mensaje","No se pudo realizar el insert en la base de datos");
			response.put("error", "Revise bien los datos y inténtelo de nuevo");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("medicamento",newMedicamento);
		response.put("mensaje", "Se inserto correctamente en la base de datos");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@PutMapping("/medicamento/{id}")
	public ResponseEntity<?> actualizarProducto(@PathVariable Long id,@RequestBody Medicamento medicamento){
		Medicamento medicamentoActualizado=service.findById(id);
		
		Map<String,Object>response=new HashMap<>();
		try {
			medicamentoActualizado.setNombre(medicamento.getNombre());
			medicamentoActualizado.setLaboratorioFabrica(medicamento.getLaboratorioFabrica());
			medicamentoActualizado.setFechaFabricacion(medicamento.getFechaFabricacion());
			medicamentoActualizado.setFechaVencimiento(medicamento.getFechaVencimiento());
			medicamentoActualizado.setCantidad(medicamento.getCantidad());
			medicamentoActualizado.setValorUnitario(medicamento.getValorUnitario());
			
		}catch(DataAccessException e) {
			response.put("mensaje","Error al tratar de actualizar los datos de la base de datos");
			response.put("error", e.getMessage());
		}
		
		medicamentoActualizado=service.save(medicamentoActualizado);
		response.put("medicamento", medicamentoActualizado);
		response.put("mensaje", "Se actualizaron correctamente los datos en la base de datos");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	
	}
	
	@DeleteMapping("/medicamento/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String,Object>response=new HashMap<>();
		try {
			service.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "no se logro borrar el dato de la base de datos");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","Se ha borrado exitosa mente");		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/medicamentos/{nombre}")
	public List<Medicamento> buscarPorNombre(@PathVariable String nombre){
		return service.findAllByName(nombre);
	}
	
}
