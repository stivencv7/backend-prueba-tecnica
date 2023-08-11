package com.konex.prueba.tecnica.models.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@CrossOrigin(origins ="http://localhost:4200" )
@Entity
@Table(name="ventas")
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate fecha;
	private LocalTime hora;
	
	@Column(name = "medicamento")
	private String nombreMedicamento;
	
	private int cantidad;
	
	@Column(name="valor_unitario")
	private int valorUnitario;
	
	@Column(name="valor_total")
	private int valorTotal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
		
	public int getCantidad() {
		return cantidad;
	}
	public String getNombreMedicamento() {
		return nombreMedicamento;
	}
	public void setNombreMedicamento(String nombreMedicamento) {
		this.nombreMedicamento = nombreMedicamento;
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
	public int getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
