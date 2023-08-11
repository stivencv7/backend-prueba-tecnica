package com.konex.prueba.tecnica.models.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="medicamentos")
public class Medicamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nombre;
	
	@Column(name="nombre_laboratorio",nullable = false)
	private String laboratorioFabrica;
	
	@Column(name="fecha_fabricacion",nullable = false)
	private LocalDate fechaFabricacion;
	
	@Column(name="fecha_vencimiento",nullable = false)
	private LocalDate fechaVencimiento;
	
	@Column(nullable = false)
	private int cantidad;
	
	@Column(name="precio",nullable = false)
	private int valorUnitario;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLaboratorioFabrica() {
		return laboratorioFabrica;
	}
	public void setLaboratorioFabrica(String laboratorioFabrica) {
		this.laboratorioFabrica = laboratorioFabrica;
	}
	
	public LocalDate getFechaFabricacion() {
		return fechaFabricacion;
	}
	public void setFechaFabricacion(LocalDate fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(int valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	
	
}
