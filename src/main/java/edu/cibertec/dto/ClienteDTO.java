package edu.cibertec.dto;

import lombok.Getter;

@Getter
public class ClienteDTO {
	
	public String nombre;
	public String direccion;
	public String telefono;
	public String email;
	
	public ClienteDTO(String nombre, String direccion, String telefono, String email) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
	}


}
