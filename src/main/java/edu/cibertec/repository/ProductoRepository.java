package edu.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.cibertec.dto.ProductoDTO;
import edu.cibertec.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	@Query("SELECT new edu.cibertec.dto.ProductoDTO(p.modelo, p.descipcion, p.precio, p.marca) FROM Producto p ")
    List<ProductoDTO> obtenerProductoDTO();

}
