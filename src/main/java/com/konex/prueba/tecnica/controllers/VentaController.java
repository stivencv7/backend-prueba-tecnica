package com.konex.prueba.tecnica.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.konex.prueba.tecnica.models.entity.Venta;
import com.konex.prueba.tecnica.models.service.IVentaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class VentaController {
	
	/**Se utiliza el ResponseEntity<?>
	 * para personalizar las respuestas http 
	 */
	
	//Inyectamos el servicio
	@Autowired
	private IVentaService service;
	
	
	// http://localhost:8080/api/ventas
	@GetMapping("/ventas")
	public List<Venta> getVentas(){
		return service.findAll();
	}
	
	//http://localhost:8080/api/venta/{venta}
	@PostMapping("/venta")
	public ResponseEntity<?> registraVenta(@RequestBody Venta venta){
		Map<String,Object>response=new HashMap<>();
		Venta newVenta=null;
		try {
			venta.setFecha(LocalDate.now());
			venta.setHora(LocalTime.now());
			newVenta=service.save(venta);
			
		}catch(DataAccessException e) {
			response.put("mensaje","No se pudo realizar el insert en la base de datos");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("venta",newVenta);
		response.put("mensaje", "Se inserto correctamente en la base de datos");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	//http://localhost:8080/api/venta/id
	@PutMapping("/venta/{id}")
	public ResponseEntity<?> actualizarVenta(@PathVariable Long id,@RequestBody Venta venta){
		System.out.println();
		Venta ventaActualizado=service.findById(id);
		
		Map<String,Object>response=new HashMap<>();
		try {
			ventaActualizado.setNombreMedicamento(venta.getNombreMedicamento());
			ventaActualizado.setCantidad(venta.getCantidad());
			ventaActualizado.setValorTotal(venta.getValorTotal());
			ventaActualizado.setValorUnitario(venta.getValorUnitario());
			ventaActualizado.setFecha(LocalDate.now());
			ventaActualizado.setHora(LocalTime.now());		
			
		}catch(DataAccessException e) {
			response.put("mensaje","Error al tratar de actualizar los datos de la base de datos");
			response.put("error", e.getMessage());
		}
		
		ventaActualizado=service.save(ventaActualizado);
		response.put("venta",ventaActualizado);
		response.put("mensaje", "Se actualizaron correctamente los datos en la base de datos");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	
	}
	//http://localhost:8080/api/venta/id
	@GetMapping("/venta/{id}")
	public ResponseEntity<?>getMedicamento(@PathVariable Long id){
		
		Venta venta=null;
		Map<String,Object> response=new HashMap<>();
		try {
			venta=service.findById(id);
		}catch(DataAccessException e) {
			response.put("error", "Error no se encontraron datos "+ e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(venta==null) {
			response.put("mensaje","no se encontraron datos con el id ".concat(id.toString())+"no exite en la base de Datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Venta>(venta,HttpStatus.OK);
	}
	//http://localhost:8080/api/ventas/ventasfiltradas?desde=fecha1&hasta=fecha2
	@GetMapping({"/ventas/ventasfiltradas"})
	public List<Venta> getVentasBetween(@RequestParam("desde") LocalDate desde,@RequestParam("hasta") LocalDate hasta){
		return service.findAllByBetween(desde, hasta);
	}
}
