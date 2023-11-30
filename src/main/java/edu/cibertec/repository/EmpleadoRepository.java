package edu.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.cibertec.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
	
	@Query("select e from Empleado as e where nombre like CONCAT('%', :nombre, '%')")
	List<Empleado> buscarEmpleadoNombre(String nombre);

}
