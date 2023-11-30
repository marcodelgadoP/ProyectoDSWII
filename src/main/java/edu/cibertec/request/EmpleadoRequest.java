package edu.cibertec.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmpleadoRequest {
	
	@NotBlank(message = "El dni no debe estar vacio")
	public String dni;
	@NotBlank(message = "El nombre no debe estar vacio")
	public String nombre;
	@NotBlank(message = "El apellido no debe estar vacio")
	public String apellido;
	@NotBlank
	@Pattern(regexp = "^[0-9]{9}$", message = "El telefono debe tener 9 números")
	public String telefono;
	@NotBlank(message = "El usuario no debe estar vacio")
	public String user;
	@NotBlank(message = "La contraseña no debe estar vacía")
    @Size(min = 4, message = "La contraseña debe tener al menos 4 caracteres")
	public String clave;
	@NotBlank(message = "El tipo de empleado no debe estar vacio")
	public String idtipo;

}
