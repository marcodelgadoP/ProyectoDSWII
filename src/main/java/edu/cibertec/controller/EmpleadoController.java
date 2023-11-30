package edu.cibertec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.cibertec.model.Empleado;
import edu.cibertec.repository.EmpleadoRepository;
import edu.cibertec.request.EmpleadoRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("empleados")
public class EmpleadoController {

	EmpleadoRepository empleadoRepository;
	
	@GetMapping
	public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }
	
	@GetMapping("name")
	public ResponseEntity<?>findName(String nombre){
		if(nombre != null) {
			List<Empleado> empleados = empleadoRepository.buscarEmpleadoNombre(nombre);
			
			if(!empleados.isEmpty()) {
				return ResponseEntity.ok(empleados);
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empelado no registrado");
			}
		}
		return ResponseEntity.ok(empleadoRepository.findAll());
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        return empleado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmpleado(@PathVariable Long id) {
        if (empleadoRepository.existsById(id)) {
        	empleadoRepository.deleteById(id);
            return ResponseEntity.ok("Empleado eliminado Correctamente");
        }else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrado");
        }
    }
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
    public Empleado register(@RequestBody @Valid EmpleadoRequest empleadoRequest) {
        Empleado emp = new Empleado();
        emp.dni= empleadoRequest.dni;
        emp.nombre=empleadoRequest.nombre;
        emp.apellido=empleadoRequest.apellido;
        emp.telefono=empleadoRequest.telefono;
        emp.user=empleadoRequest.user;
        emp.clave=empleadoRequest.clave;
        emp.idtipo=empleadoRequest.idtipo;
        Empleado empleadoSave=empleadoRepository.save(emp);
        return empleadoSave;
    }
	
	@PutMapping("{id}")
	public ResponseEntity<Empleado> update (@PathVariable Long id,@RequestBody @Valid EmpleadoRequest empleadoRequest){
		return empleadoRepository.findById(id).map(emp ->{
			emp.dni= empleadoRequest.dni;
	        emp.nombre=empleadoRequest.nombre;
	        emp.apellido=empleadoRequest.apellido;
	        emp.telefono=empleadoRequest.telefono;
	        emp.user=empleadoRequest.user;
	        emp.clave=empleadoRequest.clave;
	        emp.idtipo=empleadoRequest.idtipo;
	        empleadoRepository.save(emp);
	        return ResponseEntity.ok(emp);
		}).orElseGet(()-> ResponseEntity.notFound().build());
	}
	
}
