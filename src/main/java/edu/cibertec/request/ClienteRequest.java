package edu.cibertec.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteRequest {
	
	@NotBlank(message = "El nombre no debe estar vacio")
	public String nombre;
	@NotBlank(message = "El apellido no debe estar vacio")
	public String apellido;
	@NotBlank(message = "El direccion no debe estar vacio")
	public String direccion;
	@NotBlank
	@Pattern(regexp = "^[0-9]{9}$", message = "El telefono debe tener 9 números")
	public String telefono;
	@NotBlank(message = "El email no debe estar vacío")
    @Email(message = "El email debe ser válido")
	public String email;
	@NotBlank(message = "La contraseña no debe estar vacía")
    @Size(min = 4, message = "La contraseña debe tener al menos 4 caracteres")
	public String password;

}
