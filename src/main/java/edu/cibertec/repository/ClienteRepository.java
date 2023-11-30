package edu.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.cibertec.dto.ClienteDTO;
import edu.cibertec.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Query("SELECT new edu.cibertec.dto.ClienteDTO(c.nombre, c.direccion, c.telefono, c.email) FROM Cliente c ")
    List<ClienteDTO> obtenerClienteDTO();
	
	@Query("select c from Cliente as c where nombre like CONCAT('%', :nombre, '%')")
	List<Cliente> buscarClienteNombre(String nombre);
}
