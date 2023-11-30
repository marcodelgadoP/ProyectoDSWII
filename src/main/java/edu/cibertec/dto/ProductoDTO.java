package edu.cibertec.dto;

import lombok.Getter;

@Getter
public class ProductoDTO {
	
	public String modelo;
	public String descipcion;
	public String precio;
	public String marca;
	
	public ProductoDTO(String modelo, String descipcion, String precio, String marca) {
		super();
		this.modelo = modelo;
		this.descipcion = descipcion;
		this.precio = precio;
		this.marca = marca;
	}
	
	

}
