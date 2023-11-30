package edu.cibertec.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductoRequest {
	
	@NotBlank(message = "El modelo no debe estar vacio")
	public String modelo;
	@NotBlank(message = "La descripcion no debe estar vacio")
	public String descipcion;
	 @DecimalMin(value = "0.01", message = "El precio debe ser mayor que 0")
	public String precio;
	 @Min(value = 0, message = "El stock no puede ser negativo")
	public int stock;
	@NotBlank(message = "La marca no debe estar vacio")
	public String marca;

}
