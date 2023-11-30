package edu.cibertec.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	public Long id;
	public String modelo;
	public String descipcion;
	public String precio;
	public int stock;
	public String marca;
}
